import java.util.LinkedList;

public class Pracownik 
    implements Comparable<Pracownik> {
    private static LinkedList<Pracownik>  employeesList = new LinkedList<>();
    private String name;
    private String surname;
    private String birthDate;
    private DzialPracownikow dzial;
    private boolean czyZdrowy = true;

    public Pracownik(String name, String surname, String birtDate, DzialPracownikow dzial) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birtDate;
        this.dzial = dzial;
        employeesList.add(this);
    }   

    public int compareTo(Pracownik employee) {
        return 0;
    }


}
