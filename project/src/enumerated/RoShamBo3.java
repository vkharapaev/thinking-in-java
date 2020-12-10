package enumerated;

public enum RoShamBo3 implements Competitor<RoShamBo3> {
    PAPER {
        public Outcome compete(RoShamBo3 it) {
            switch (it) {
                default:
                case PAPER:
                    return Outcome.DRAW;
                case SCISSORS:
                    return Outcome.LOSE;
                case ROCK:
                    return Outcome.WIN;
            }
        }
    },
    SCISSORS {
        public Outcome compete(RoShamBo3 it) {
            switch (it) {
                default:
                case PAPER:
                    return Outcome.WIN;
                case SCISSORS:
                    return Outcome.DRAW;
                case ROCK:
                    return Outcome.LOSE;
            }
        }
    },
    ROCK {
        public Outcome compete(RoShamBo3 it) {
            switch (it) {
                default:
                case PAPER:
                    return Outcome.LOSE;
                case SCISSORS:
                    return Outcome.WIN;
                case ROCK:
                    return Outcome.DRAW;
            }
        }
    };

    @Override
    public abstract Outcome compete(RoShamBo3 competitor);

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo3.class, 20);
    }
}
