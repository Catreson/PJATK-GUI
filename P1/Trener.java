import java.time.LocalDate;

public class Trener 
    extends Pracownik {
        private static int incr = 0;
        private int ID = 0;
        private String specjalizacja;
        public Trener(String name, String surname, LocalDate birtDate, DzialPracownikow dzial, String specjalizacja) {
            super(name, surname, birtDate, dzial);
            this.specjalizacja = specjalizacja;
            this.ID = ++incr;
    }
    
    

    public String getSpecjalizacja() {
        return specjalizacja;
    }
    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }

    public int getID() {
        return ID;
    }
}
