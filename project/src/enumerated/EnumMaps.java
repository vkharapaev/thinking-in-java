package enumerated;

//: enumerated/EnumMaps.java
// Basics of EnumMaps.

import java.util.*;

import static enumerated.AlarmPoints.*;

interface Command {
    void action();
}

public class EnumMaps {
    public static void main(String[] args) {

        EnumMap<AlarmPoints, Command> em = new EnumMap<>(AlarmPoints.class);

        em.put(KITCHEN, () -> print("Kitchen fire!"));

        em.put(BATHROOM, () -> print("Bathroom alert!"));

        for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
            printnb(e.getKey() + ": ");
            e.getValue().action();
        }

        try { // If there’s no value for a particular key:
            em.get(UTILITY).action();
        } catch (Exception e) {
            print(e);
        }
    }

    private static void print(Exception e) {
        System.out.println(e);
    }

    private static void printnb(String s) {
        System.out.print(s);
    }

    private static void print(String s) {
        System.out.println(s);
    }
} /* Output:
BATHROOM: Bathroom alert!
KITCHEN: Kitchen fire!
java.lang.NullPointerException
*///:~