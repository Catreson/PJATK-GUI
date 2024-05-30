public class Scoreboard {
        private static int score = 0;
        @SuppressWarnings("unused")
        private static Scoreboard sboard;
        public Scoreboard(){
            sboard = this;
        }

        public static void incrementScore(){
            score++;
        }

        public static String getScore(){
            return String.valueOf(score);
        }

        public static void reset(){
            score = 0;
        }
}
