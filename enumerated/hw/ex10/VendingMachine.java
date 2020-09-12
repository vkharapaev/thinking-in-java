package enumerated.hw.ex10;
//: enumerated/VendingMachine.java
// {Args: VendingMachineInput.txt}

import enumerated.Generator;

import java.util.EnumMap;

import static enumerated.hw.ex10.Input.*;

enum Category {

    MONEY(NICKEL, DIME, QUARTER, DOLLAR),

    ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),

    QUIT_TRANSACTION(ABORT_TRANSACTION),

    SHUT_DOWN(STOP);

    private Input[] values;

    Category(Input... types) {
        values = types;
    }

    private static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(Input.class);

    static {
        for (Category c : Category.class.getEnumConstants()) {
            for (Input type : c.values) {
                categories.put(type, c);
            }
        }
    }

    public static Category categorize(Input input) {
        return categories.get(input);
    }
}

public class VendingMachine {

    private State state = State.RESTING;
    private int amount = 0;
    private Input selection = null;

    enum StateDuration {TRANSIENT} // Tagging enum

    class Command {
        void next(Input input) {
            throw new RuntimeException("Only call next(Input input) for non-transient states");
        }

        void next() {
            throw new RuntimeException("Only call next() for StateDuration.TRANSIENT states");
        }

        void output() {
            print(amount);
        }
    }

    private EnumMap<State, Command> commands = new EnumMap<State, Command>(State.class);

    public VendingMachine() {

        commands.put(State.RESTING, new Command() {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = State.ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = State.TERMINAL;
                    default:
                }
            }
        });

        commands.put(State.ADDING_MONEY, new Command() {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount()) {
                            print("Insufficient money for " + selection);
                        } else {
                            state = State.DISPENSING;
                        }
                        break;
                    case QUIT_TRANSACTION:
                        state = State.GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = State.TERMINAL;
                    default:
                }
            }
        });

        commands.put(State.DISPENSING, new Command() {
            @Override
            void next() {
                print("here is your " + selection);
                amount -= selection.amount();
                state = State.GIVING_CHANGE;
            }
        });

        commands.put(State.GIVING_CHANGE, new Command() {
            @Override
            void next() {
                if (amount > 0) {
                    print("Your change: " + amount);
                    amount = 0;
                }
                state = State.RESTING;
            }
        });

        commands.put(State.TERMINAL, new Command() {
            @Override
            void output() {
                print("Halted");
            }
        });
    }

    Command getCommand() {
        return commands.get(state);
    }

    enum State {
        RESTING, ADDING_MONEY, DISPENSING(StateDuration.TRANSIENT), GIVING_CHANGE(StateDuration.TRANSIENT), TERMINAL;

        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }
    }

    static void run(Generator<Input> gen) {
        VendingMachine machine = new VendingMachine();

        while (machine.state != State.TERMINAL) {
            machine.getCommand().next(gen.next());
            while (machine.state.isTransient) {
                machine.getCommand().next();
            }
            machine.getCommand().output();
        }
    }

    public static void main(String[] args) {
        Generator<Input> gen = new RandomInputGenerator();
//        if (args.length == 1) {
//            gen = new FileInputGenerator(args[0]);
//        }
        run(gen);
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}

// For a basic sanity check:
class RandomInputGenerator implements Generator<Input> {
    public Input next() {
        return Input.randomSelection();
    }
}

//// Create Inputs from a file of ‘;’-separated strings:
//class FileInputGenerator implements Generator<Input> {
//    private Iterator<String> input;
//
//    public FileInputGenerator(String fileName) {
//        input = new TextFile(fileName, ";").iterator();
//    }
//
//    public Input next() {
//        if (!input.hasNext()) {
//            return null;
//        }
//        return Enum.valueOf(Input.class, input.next().trim());
//    }
//}

/* Output:
25
50
75
here is your CHIPS
0
100
200
here is your TOOTHPASTE
0
25
Enumerated Types 749
35
Your change: 35
0
25
35
Insufficient money for SODA
35
60
70
75
Insufficient money for SODA
75
Your change: 75
0
Halted
*///:~
