package enumerated;

//: enumerated/EnumClass.java
// Capabilities of the Enum class
enum Shrubbery {GROUND, CRAWLING, HANGING}

public class EnumClass {
    public static void main(String[] args) {

        for (Shrubbery s : Shrubbery.values()) {
            print(s + " ordinal: " + s.ordinal());
            printnb(s.compareTo(Shrubbery.CRAWLING) + " ");
            printnb(s.equals(Shrubbery.CRAWLING) + " ");
            print(s == Shrubbery.CRAWLING);
            print(s.getDeclaringClass());
            print(s.name());
            print("----------------------");
        }

        // Produce an enum value from a string name:
        for (String s : "HANGING CRAWLING GROUND".split(" ")) {
            Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
            print(shrub);
        }
    }

    private static void print(Shrubbery shrub) {
        System.out.println(shrub);
    }

    private static void print(Class<?> declaringClass) {
        System.out.println(declaringClass);
    }

    private static void print(boolean b) {
        System.out.println("boolean "+b);
    }

    private static void printnb(String s) {
        System.out.print(s);
    }

    private static void print(String s) {
        System.out.println(s);
    }
} /* Output:
GROUND ordinal: 0
-1 false false
class Shrubbery
1 Joshua Bloch was extremely helpful in developing this chapter.
GROUND
----------------------
CRAWLING ordinal: 1
0 true true
class Shrubbery
CRAWLING
----------------------
HANGING ordinal: 2
1 false false
class Shrubbery
HANGING
----------------------
HANGING
CRAWLING
GROUND
*///:~