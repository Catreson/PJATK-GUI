import java.time.LocalDate;

public class Recepcjonista 
    extends Pracownik {
    
    private String login;
    @SuppressWarnings("unused")
    private String haslo;
    private String initial;
    private static int incr = 0;
    private int ID;
    
    
    public Recepcjonista(String name, String surname, LocalDate birtDate, DzialPracownikow dzial, String login, String haslo) {
        super(name, surname, birtDate, dzial);
        this.login = login;
        this.haslo = haslo;
        this.ID = ++incr;
        this.setInitial();
        if (this.getClass() == Recepcjonista.class) {
            OutputManager.printToFileAndConsole(createMessage());
        }
    }

    protected void setInitial() {
        this.initial = String.format("%c%c",getName().charAt(0),getSurname().charAt(0));
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
