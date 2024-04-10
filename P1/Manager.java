import java.time.LocalDate;

public class Manager
    extends Recepcjonista 
    implements IDobryPracownik{
        private static int incr = 0;
        private int ID;
        public Manager(String name, String surname, LocalDate birtDate, DzialPracownikow dzial, String login, String haslo) {
            super(name, surname, birtDate, dzial, login, haslo);
            this.ID = ++incr;
        }
        @Override
        public void pracuj() {
            System.out.println("Nie rozkazuj mi");
        }
        @Override
        public DzialPracownikow gdziePracuje() {
            return this.getDzial();
        }
        @Override
        public String ileZarabia() {
            return "Poufne";
        }
        
        public int getID() {
            return this.ID;
        }
    
}
