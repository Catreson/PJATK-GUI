import java.time.LocalDate;

public class Recepcjonista 
    extends Pracownik {
    
    private String login;
    @SuppressWarnings("unused")
    private String haslo;
    private String initial;
    private static int incr = 0;
    private final int ID;
    
    
    public Recepcjonista(String name, String surname, LocalDate birtDate, DzialPracownikow dzial, String login, String haslo) {
    super(name, surname, birtDate, dzial);
    this.login = login;
    this.haslo = haslo;
    this.ID = ++incr;
    this.setInitial();
    }

    private void setInitial() {
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
}
