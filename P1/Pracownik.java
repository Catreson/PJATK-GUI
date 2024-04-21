import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.time.LocalDate;

public abstract class Pracownik 
    implements Comparable<Pracownik>,
                IDobryPracownik {
    private static LinkedList<Pracownik>  lPracownikow = new LinkedList<>();
    protected String name;
    protected String surname;
    private LocalDate birthDate;
    private DzialPracownikow dzial;
    private boolean czyZdrowy = true;
    private static AtomicInteger incr = new AtomicInteger(1);
    private int ID;

    public Pracownik(String name, String surname, LocalDate birtDate, DzialPracownikow dzial) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birtDate;
        this.dzial = dzial;
        this.ID = incr.getAndIncrement();
        lPracownikow.add(this);
    }   

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return this.name;
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

    public static ArrayList<Pracownik> getLPracownikow(){
        return new ArrayList<Pracownik>(lPracownikow);
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Zadanie> getZadania() {
        ArrayList<Zadanie> lZadania = new ArrayList<>();
        for (Entry<Integer, Zadanie> e : Zadanie.getEntries()) {
            if(e.getValue().getZespol().getPracownicy().contains(this) || e.getValue().getZespol().getByliPracownicy().contains(this))
                lZadania.add(e.getValue());
        }
        return lZadania;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof Pracownik))
            return false;
        Pracownik p = (Pracownik)o;
        return this.getID() == p.getID();
    }

    @Override
    public int compareTo(Pracownik p) {
        if(this.birthDate.compareTo(p.birthDate) != 0) 
            return this.birthDate.compareTo(p.birthDate);
        return this.ID - p.ID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Stanowisko: ").append(this.getClass().getName())
            .append("\nImię: ").append(name)
            .append("\nNazwisko: ").append(surname)
            .append("\nData urodzenia: ").append(birthDate)
            .append("\nDział: ").append(dzial)
            .append("\nID pracownika: ").append(ID);
        return sb.toString();
    }

}
