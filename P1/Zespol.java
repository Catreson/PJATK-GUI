import java.util.LinkedList;

public class Zespol {
    private String nazwa;
    private Manager manager;
    private LinkedList<Pracownik> pracownicy = new LinkedList<>();
    private static int incr = 0;
    private int ID;

    public Zespol(String nazwa, Manager manager, LinkedList<Pracownik> lPracowniks) {
        this.nazwa = nazwa;
        this.manager = manager;
        this.pracownicy.addAll(lPracowniks);
        this.ID = ++incr;
    }
    public void addPracownik(LinkedList<Pracownik> lPracowniks) throws AddPracownikException {
        for (Pracownik p : lPracowniks)
            this.addPracownik(p);
    }

    public void addPracownik(Pracownik p) throws AddPracownikException {
        if (p instanceof Manager)
            throw new AddPracownikException("Nie mozna dodac managera.");
        if(pracownicy.contains(p))
            throw new AddPracownikException("Duplikat pracownika.");
        pracownicy.add(p);
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}

class AddPracownikException
    extends Exception {
        public AddPracownikException(String message) {
            super(message);
        }
    }