/*
 * File: Proxy.java
 * Path: ./etc/
 *
 * Date: 2026-01-12
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: Proxy template
 */

package etc;

import java.util.ArrayList;
import java.util.Random;
import java.util.zip.CRC32;

interface InterfaceNode {
    void receiveMessage(String message);
    boolean getStatus();
    String dispatchMessage();
}

class RealObject implements InterfaceNode {
    private boolean isBusy;
    private final Random rndGenerator;
    private byte[] acceptedMessageInByte;
    private String acceptedMessage;
    private boolean acceptedMessageFlag;

    public RealObject() {
        rndGenerator = new Random();
        isBusy = rndGenerator.nextBoolean();
        acceptedMessageInByte = null;
        acceptedMessageFlag = false;
    }

    @Override
    public void receiveMessage(String message) {
        if (!isBusy) {
            acceptedMessage = message;
            acceptedMessageInByte = message.getBytes();
            acceptedMessageFlag = true;
        }
        isBusy = rndGenerator.nextBoolean();
    }

    @Override
    public boolean getStatus() {
        boolean currentStatus = acceptedMessageFlag;
        acceptedMessageFlag = false;
        return currentStatus;
    }

    @Override
    public String dispatchMessage() {
        if (acceptedMessageInByte == null || acceptedMessageInByte.length == 0) return "";

        CRC32 crc32 = new CRC32();
        crc32.update(acceptedMessageInByte);

        String crc32String = Long.toString(crc32.getValue());
        String echo = acceptedMessage + " " + crc32String;
        System.out.println(echo);

        acceptedMessageInByte = null;
        acceptedMessage = null;

        return echo;
    }
}

class ProxyObject implements InterfaceNode {

    static class oneCommand {
        final String command;
        final String crc32String;

        public oneCommand(String in) throws NullPointerException {
            if (in == null) throw new NullPointerException("class oneCommand: oneCommand(null)");
            command = in;

            byte[] byteChain = in.getBytes();

            CRC32 crc32 = new CRC32();
            crc32.update(byteChain);

            long crc32Long = crc32.getValue();
            crc32String = Long.toString(crc32Long);
        }

        public String getCommand() {
            return command;
        }

        public String getCRC32String() {
            return crc32String;
        }
    }

    ArrayList<oneCommand> cacheCommand = new ArrayList<oneCommand>();
    private int lastPositionInCommandList = 0;

    private final RealObject objectOfInterest;

    public ProxyObject() {
        objectOfInterest = new RealObject();
    }

    public ProxyObject(RealObject interest) throws NullPointerException {
        if (interest == null) throw new NullPointerException("class ProxyObject: ProxyObject(null)");
        objectOfInterest = interest;
    }

    @Override
    public void receiveMessage(String message) throws NullPointerException {
        if (message == null) throw new NullPointerException("class ProxyObject: ProxyObject(null)");
        String[] listCommands = message.strip().split(" ");
        for (String s : listCommands) {
            cacheCommand.add(new oneCommand(s));
        }

        int countInList = cacheCommand.size();
        int i = lastPositionInCommandList;
        do {
            String command = cacheCommand.get(i).getCommand();
            System.out.println("Sending a message: " + command);
            int countOfCycle = 0;
            do {
                countOfCycle++;
                System.out.println("Number of probes to dispatch command: " + countOfCycle);
                objectOfInterest.receiveMessage(command);
            } while (!objectOfInterest.getStatus());
            String receiveMsg = objectOfInterest.dispatchMessage();
            String correctMsg = command + " " + cacheCommand.get(i).getCRC32String();
            boolean statusOfCheck = correctMsg.equals(receiveMsg);
            if (statusOfCheck) {
                System.out.println("Message \"" + command + "\" received successful.");
                i++;
            }
        } while (i < countInList);
        lastPositionInCommandList = i;
    }

    @Override
    public boolean getStatus() {
        return true;
    }

    @Override
    public String dispatchMessage() {
        StringBuffer answer = new StringBuffer();

        for (oneCommand c: cacheCommand) {
            answer.append(c.getCommand())
                    .append(" ")
                    .append(c.getCRC32String())
                    .append("; ");
        }

        return answer.toString();
    }
}

public class Proxy {
    private final static String MODULE_INFO =
            "This module not for production use. Only for studying purpose.";


    public static void main(String[] args) {
        ProxyObject proxy = new ProxyObject();
        String command = "Up Down Left Right";
        proxy.receiveMessage(command);
        command = "Push Pull";
        proxy.receiveMessage(command);
    }
}
