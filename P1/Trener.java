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
        if (this.getClass() == Trener.class) {
            OutputManager.printToFileAndConsole(createMessage());
        }
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


    @Override
    public String ileZarabia() {
        return "Zbyt mało";
    }

    @Override
    public String createMessage() {
        return "Utworzono pracownika: " + this.toString();
    }

    @Override
    public DzialPracownikow gdziePracuje() {
        return this.getDzial();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\nID trenera: ").append(ID)
        .append("\nSpecjalizacja: ").append(specjalizacja);

        return sb.toString();
    }
}
