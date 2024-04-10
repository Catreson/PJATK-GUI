import java.util.LinkedList;
import java.time.LocalDate;

public abstract class Pracownik 
    implements Comparable<Pracownik> {
    private static LinkedList<Pracownik>  employeesList = new LinkedList<>();
    private String name;
    private String surname;
    private LocalDate birthDate;
    private DzialPracownikow dzial;

    private boolean czyZdrowy = true;

    private static int incr = 0;
    private int ID;

    public Pracownik(String name, String surname, LocalDate birtDate, DzialPracownikow dzial) {
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

    public DzialPracownikow getDzial() {
        return dzial;
    }

    public void setDzial(DzialPracownikow dzial) {
        this.dzial = dzial;
    }

    public boolean getCzyZdrowy() {
        return czyZdrowy;
    }
    public void setCzyZdrowy(boolean czyZdrowy) {
        this.czyZdrowy = czyZdrowy;
    }

    public int compareTo(Pracownik p) {
        if(this.birthDate.compareTo(p.birthDate) != 0) 
            return this.birthDate.compareTo(p.birthDate);
        return this.ID - p.ID;
    }


}
