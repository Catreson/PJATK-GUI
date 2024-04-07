public 
    enum Cards {
        ACE(15),
        TWO(2),
        JACK(11),
        KING(14);

        private final int value;

        private Cards(final int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

}
