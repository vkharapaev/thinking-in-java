package enumerated.hw;

enum TestEnum {
    ONE {
        String test() {
            return "Test One";
        }
    },

    TWO {
        String test() {
            return "Test Two";
        }
    };

    abstract String test();
}

class Nvl {

    static <T> T nvl(T... vals) {
        for (T v : vals) {
            if (v != null) {
                return v;
            }
        }
        return null;
    }

}

public class Test {
    public static void main(String[] args) {
//        TestEnum[] values = TestEnum.values();
//        TestEnum[] values2 = TestEnum.values();
//        System.out.println("test");

        System.out.println(Nvl.<Integer>nvl(null, 10, 5));
    }
}
