

public class Trener 
    extends Pracownik {
        private static int incr = 0;
        private int ID = 0;
        private String specjalizacja;
        public Trener(String name, String surname, String birtDate, DzialPracownikow dzial, String specjalizacja) {
            super(name, surname, birtDate, dzial);
            this.specjalizacja = specjalizacja;
    }
}
