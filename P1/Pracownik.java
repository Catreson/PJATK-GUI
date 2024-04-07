import java.util.LinkedList;

public class Pracownik 
    implements Comparable<Pracownik> {
    private static LinkedList<Pracownik>  employeesList = new LinkedList<>();
    private String name;
    private String surname;
    private String birthDate;
    private DzialPracownikow dzial;
    private boolean czyZdrowy = true;
    private static int incr = 0;
    private int ID;

    public Pracownik(String name, String surname, String birtDate, DzialPracownikow dzial) {
        this.setName(name);
        this.setSurname(surname);
        this.birthDate = birtDate;
        this.dzial = dzial;
        this.ID = ++incr;
        employeesList.add(this);
    }   

    public String getSurname() {
        return surname;
        
    }

    public void setSurname(String surname) {
        this.surname = surname;
        
    }

    public String getName() {
        return name;
        
    }

    public void setName(String name) {
        this.name = name;
        
    }

    public int compareTo(Pracownik employee) {
        return 0;
    }


}
