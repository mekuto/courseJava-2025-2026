package etc;

import java.util.Random;

interface InterfaceNode {
    void receiveMessage(String message);
    boolean getStatus();
    byte[] dispatchMessage();
}

class RealObject implements InterfaceNode {
    private boolean isBusy;
    private Random rndGenerator;
    private byte[] acceptedMessage;
    private boolean acceptedMessageFlag;
    private String textToSend;

    public RealObject() {
        rndGenerator = new Random();
        isBusy = rndGenerator.nextBoolean();
        acceptedMessage = null;
        textToSend = null;
        acceptedMessageFlag = false;
    }

    @Override
    public void receiveMessage(String message) {
        if (!isBusy) {
            acceptedMessage = message.getBytes();
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
    public byte[] dispatchMessage() throws NullPointerException {
        if (acceptedMessage == null)
            throw new NullPointerException("class RealObject: dispatchMessage: acceptedMessage == null");
//        TODO
        return new byte[0];
    }
}

public class Proxy {
}
