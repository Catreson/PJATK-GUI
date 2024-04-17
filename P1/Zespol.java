import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class Zespol {
    private String nazwa;
    private Manager manager;
    private LinkedList<Pracownik> pracownicy = new LinkedList<>();
    private LinkedList<Pracownik> byliPracownicy =  new LinkedList<>();
    private static AtomicInteger incr = new AtomicInteger(1);
    private int ID;

    public Zespol(String nazwa, Manager manager, LinkedList<Pracownik> lPracowniks) {
        this.nazwa = nazwa;
        this.setManager(manager);
        try {
        this.addPracownik(lPracowniks);
        }
        catch(AddPracownikException e) {
            StringBuilder sb = new StringBuilder(e.toString());
            for (StackTraceElement el : e.getStackTrace()) {
                sb.append("\n").append(el);
            }
            OutputManager.printToFileAndConsole(sb);
        }
        this.ID = incr.getAndIncrement();
    }
    public void addPracownik(LinkedList<Pracownik> lPracowniks) throws AddPracownikException {
        for (Pracownik p : lPracowniks)
            this.addPracownik(p);
    }

    public void addPracownik(Pracownik p) throws AddPracownikException {
        if (p instanceof Manager)
            throw new AddPracownikException("Nie mozna dodac managera do zespołu.");
        else if(pracownicy.contains(p))
            throw new AddPracownikException("Duplikat pracownika.");
        else{
            this.pracownicy.add(p);
            OutputManager.printToFileAndConsole(String.format("Do zespolu %s dodano %s %s %s ID %d", this.nazwa, p.getClass().getName(),  p.getName(), p.getSurname(), p.getID()));
        }
    }

    public void removePracownik(Pracownik p) {
        if (pracownicy.contains(p))
            this.byliPracownicy.add(p);
        this.pracownicy.remove(p);
    }
    
    public ArrayList<Pracownik> getPracownicy() { 
        return new ArrayList<>(this.pracownicy);
    }

    public ArrayList<Pracownik> getByliPracownicy() { 
        return new ArrayList<>(this.byliPracownicy);
    }

    public int getID() {
        return this.ID;
    }

    public String getNazwa() {
        return this.nazwa;
    }
    
    public void setManager(Manager manager) {
        manager.addZespol(this);
        OutputManager.printToFileAndConsole(String.format("Do zespolu %s przypisano menagera  %s %s ID %d", this.nazwa,  manager.getName(), manager.getSurname(), manager.getID()));
        this.manager = manager;
    }
    public Manager getManager() {
        return this.manager;
    }

    public void addZadanieToManager(Zadanie z) {
        manager.addZadanie(this, z);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nZespol: ").append(this.nazwa)
            .append("\nID zespolu: ").append(this.ID)
            .append("\nManager zespołu: ").append(manager.getName()).append(" ").append(manager.getSurname())
            .append("\nLiczba pracowników: ").append(pracownicy.size());

        return sb.toString();
    }

}

class AddPracownikException
    extends Exception {
        public AddPracownikException(String message) {
            super(message);
        }
    }