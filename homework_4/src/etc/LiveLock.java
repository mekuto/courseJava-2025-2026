/*
 * File: LiveLock.java
 * Path: ./etc/
 * Build: javac ./Main.java
 * Date: 2026-01-16
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: Demo of thread LiveLock
 */

package etc;

public class LiveLock {
    private final static String MODULE_INFO =
        "This module not for production use. Only for studying purpose.";

    public static void main(String[] args) {
        final Diner firstPerson = new Diner("Alex");
        final Diner secondPerson = new Diner("Alisa");
        final Spoon spoon = new Spoon(firstPerson);

        final ThreadObserver observer = new ThreadObserver(firstPerson, secondPerson);

        firstPerson.setTheSpoonShare(spoon);
        firstPerson.setOtherDiner(secondPerson);
        secondPerson.setTheSpoonShare(spoon);
        secondPerson.setOtherDiner(firstPerson);

        observer.start();
    }

    static class Spoon {
        Diner owner;

        public Spoon(Diner human) {
            owner = human;
        }

        public synchronized Diner getOwner() {
            return owner;
        }

        public synchronized void setOwner(Diner human) {
            owner = human;
        }

        public synchronized void use() {
            System.out.printf("%s has eaten\n", owner.getDinerName());
        }
    }

    static class Diner extends Thread {
        private final String name;
        private boolean isHungry;
        private Spoon spoonShare;
        private Diner dinerShare;

        public Diner(String name) {
            this.name = name;
            isHungry = true;
        }

        public String getDinerName() {
            return name;
        }

        public synchronized boolean isHungry() {
            return isHungry;
        }

        private synchronized boolean spoonIsOurs(Diner owner, Spoon spoon) {
            return spoon.owner != owner;
        }

        public void setTheSpoonShare(Spoon spoon) {
            spoonShare = spoon;
        }

        public void setOtherDiner(Diner other) {
            dinerShare = other;
        }

        public void eatWith(Spoon spoon, Diner other) {

            while (isHungry && !Thread.currentThread().isInterrupted()) {
                if (spoonIsOurs(this, spoon)) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                    continue;
                }

                if (other.isHungry()) {
                    System.out.printf("%s: I giving to you this spoon, %s\n", name, other.getDinerName());
                    spoon.setOwner(other);
                    continue;
                }

                spoon.use();
                isHungry = false;
                System.out.printf("%s: I am not hungry, and are you, %s\n", name, other.getDinerName());
                spoon.setOwner(other);
            }
        }

        @Override
        public void run() {
            eatWith(spoonShare, dinerShare);
        }
    }


}
