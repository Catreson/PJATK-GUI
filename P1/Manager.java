public class Manager
    extends Recepcjonista 
    implements IDobryPracownik{
        private static int incr = 0;
        private int ID;
        public Manager(String name, String surname, String birtDate, DzialPracownikow dzial, String login, String haslo) {
            super(name, surname, birtDate, dzial, login, haslo);
            this.ID = ++incr;
        }
        @Override
        public void pracuj() {
            System.out.println("Nie rozkazuj mi");
        }
        @Override
        public DzialPracownikow gdziePracuje() {
            return (DzialPracownikow) new Object();
        }
    
}
