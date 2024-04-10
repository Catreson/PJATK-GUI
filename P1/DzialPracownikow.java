import java.util.ArrayList;

public class DzialPracownikow {
    private static int incr = 0;
    private int ID;
    private static ArrayList<String> namesList = new ArrayList<>();
    private String name;

    private DzialPracownikow(String name) {
        this.name = name;
        this.ID = ++incr;
        namesList.add(name);
    }
    public DzialPracownikow createDzial(String name)
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
 }

 class NotUniqueNameException
    extends Exception {
        public NotUniqueNameException(String message) {
            super(message);
        }
    }