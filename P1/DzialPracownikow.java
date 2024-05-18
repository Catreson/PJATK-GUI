import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class DzialPracownikow {
    private static AtomicInteger incr = new AtomicInteger(1);
    private int ID;
    private static ArrayList<String> namesList = new ArrayList<>();
    private String name;

    private DzialPracownikow(String name) {
        this.name = name;
        this.ID = incr.getAndIncrement();
        namesList.add(name);
    }
    public static DzialPracownikow createDzial(String name)
        throws NotUniqueNameException {
            if (namesList.contains(name)) {
                throw new NotUniqueNameException("Duplikat nazwy");
            }
            else 
                return new DzialPracownikow(name);
    }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public  ArrayList<Pracownik> getPracownicy() {
        return (ArrayList<Pracownik>)Pracownik.getLPracownikow().stream()
            .filter(p -> p.getDzial() == this)
            .toList();
    }

    @Override
    public String toString() {
        return this.name;
    }
 }

 class NotUniqueNameException
    extends Exception {
        public NotUniqueNameException(String message) {
            super(message);
        }
    }