import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Recepcjonista 
    extends Pracownik {
    
    private String login;
    @SuppressWarnings("unused")
    private String haslo;
    private String initial;
    private static AtomicInteger incr = new AtomicInteger(1);
    private int ID;
    
    
    public Recepcjonista(String name, String surname, LocalDate birtDate, DzialPracownikow dzial, String login, String haslo) {
        super(name, surname, birtDate, dzial);
        this.login = login;
        this.haslo = haslo;
        this.ID = incr.getAndIncrement();
        this.setInitial();
        if (this.getClass() == Recepcjonista.class) {
            OutputManager.printToFileAndConsole(createMessage());
        }
    }

    protected void setInitial() {
        this.initial = String.format("%c%c", this.getName().charAt(0), this.getSurname().charAt(0));
    }

    @Override
    public void setName(String name) {
        this.name = name;
        this.setInitial();
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
        this.setInitial();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getInitial() {
        return initial;
    }

    public int getID() {
        return ID;
    }


    @Override
    public String ileZarabia() {
        return "Mało";
    }

    @Override
    public DzialPracownikow gdziePracuje() {
        return this.getDzial();
    }

    @Override
    public String createMessage() {
        return "Utworzono pracownika: " + this.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\nID recepcjonisty: ").append(ID)
            .append("\nLogin:").append(login)
            .append("\nInicjały: ").append(initial);

        return sb.toString();
    }
}
