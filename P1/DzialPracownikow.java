import java.util.ArrayList;

public class DzialPracownikow {
    private static ArrayList<String> namesList = new ArrayList<>();
    private String name;
    private DzialPracownikow(){

    }
    private DzialPracownikow(String name) {
        this.name = name;
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
 }

 class NotUniqueNameException
    extends Exception {
        public NotUniqueNameException(String message) {
            super(message);
        }
    }