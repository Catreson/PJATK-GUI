import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class Manager
    extends Recepcjonista {
        private static AtomicInteger incr = new AtomicInteger(1);
        private int ID;
        private HashMap<Zespol, LinkedList<Zadanie>> hmZadan = new HashMap<>();
        public Manager(String name, String surname, LocalDate birtDate, DzialPracownikow dzial, String login, String haslo) {
            super(name, surname, birtDate, dzial, login, haslo);
            this.ID = incr.getAndIncrement();
            if (this.getClass() == Manager.class) {
                OutputManager.printToFileAndConsole(createMessage());
            }
        }

        public void addZadanie(Zespol ze, Zadanie za) {
            if (hmZadan.get(ze) == null)
                hmZadan.put(ze, new LinkedList<>(){{add(za);}});
            else
                hmZadan.get(ze).add(za);
        }

        public void addZespol(Zespol z) {
            if (hmZadan.get(z) == null)
                hmZadan.put(z, new LinkedList<>());
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

        public ArrayList<Zespol> getlZespolow() {
            return new ArrayList<Zespol>(hmZadan.keySet());
        }

        public ArrayList<Zadanie> getlZadanByZespol(Zespol z) {
            return new ArrayList<>(hmZadan.get(z));
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(super.toString());
            sb.append("\nID managera: ").append(this.ID);
            return sb.toString();
        }
    
}
