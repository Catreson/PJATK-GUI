public class Recepcjonista 
    extends Pracownik {
    
    private String login;
    private String haslo;
    private String initial;
    private static int incr = 0;
    private int ID = 0;
    
    
        public Recepcjonista(String name, String surname, String birtDate, DzialPracownikow dzial, String login, String haslo) {
        super(name, surname, birtDate, dzial);
        this.login = login;
        this.haslo = haslo;
        this.ID = incr++;
        this.setInitial();
    }
    



    private void setInitial() {
        this.initial = String.format("%c%c",getName().charAt(0),getSurname().charAt(0));
    }
}
