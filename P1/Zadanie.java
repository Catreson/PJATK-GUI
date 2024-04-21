import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Zadanie 
    extends Thread {
    private static HashMap<Integer, Zadanie> hmZadan = new HashMap<>();
    private static AtomicInteger incr = new AtomicInteger(1);
    private int ID;
    private String nazwa;
    private String opis;
    private boolean zatwierdzenie;
    private StanZadania stan;
    private LocalDateTime dataUtworzenia;
    private LocalDateTime dataZakonczenia;
    private double czasWykonania;
    private Zespol zespol;
    private CountDownLatch latch;

    public Zadanie(String nazwa, String opis, Zespol zespol, boolean zatwierdzenie)
    {
        this(nazwa);
        this.opis = opis;
        this.zatwierdzenie = zatwierdzenie;
        this.zespol = zespol;
        zespol.addZadanieToManager(this);
    }
    public Zadanie(String nazwa) {
        this.nazwa = nazwa;
        this.dataUtworzenia = LocalDateTime.now();
        this.czasWykonania = (Math.random() * 5) + 3;
        this.stan = StanZadania.Utworzone;
        this.ID = incr.getAndIncrement();
        hmZadan.put(this.ID, this);
    }

    @Override
    public void run() throws NieMoznaRunException {
        int timer = 0;
        czyMoznaZaczac();
        OutputManager.printToFileAndConsole(String.format("Rozpoczęto zadanie: %s o ID: %d", this.nazwa,  this.ID));
        this.stan = StanZadania.Rozpoczete;
        while(timer++ < czasWykonania) {
            try {
                sleep(1000);
            }
            catch(InterruptedException e) {
                throw new NieMoznaRunException(e.getMessage());
            }
            OutputManager.printToFileAndConsole(String.format("Zadanie: %s o ID: %d, pracuje już: %d sekund", this.nazwa,  this.ID, timer));
        }
        try {
            sleep((long)(1000 * (czasWykonania - (timer-2))));
        }
        catch(InterruptedException e) {
            throw new NieMoznaRunException(e.getMessage());
        }
        this.dataZakonczenia = LocalDateTime.now();
        this.stan = StanZadania.Zakonczone;
        OutputManager.printToFileAndConsole(String.format("Zakończono zadanie: %s ID: %d", this.nazwa,  this.ID));
        if (latch != null) 
            latch.countDown();
    }

    private void czyMoznaZaczac() throws NieMoznaRunException{
        if (!zatwierdzenie)
            throw new NieMoznaRunException("Nie zatwierdzone");
        for (Pracownik p : this.zespol.getPracownicy()) {
            if (p.getCzyZdrowy() == false) {
                throw new NieMoznaRunException(String.format("Pracownik %s %s ID pracownika: %d jest chory", p.getName(), p.getSurname(), p.getID()));
            }
        }
        if (stan != StanZadania.Utworzone)
        {
            throw new NieMoznaRunException(String.format("\nZadanie zostało już: %s", this.stan));
        }
    }


    public String getNazwa() {
        return nazwa;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return this.opis;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }

    public boolean getZatwierdzenie() {
        return this.zatwierdzenie;
    }

    public LocalDateTime getDataUtworzenia() {
        return this.dataUtworzenia;
    }

    public LocalDateTime getDataZakonczenia() {
        return this.dataZakonczenia;
    }

    public StanZadania getStanZadania() {
        return stan;
    }

    public Zespol getZespol() {
        return this.zespol;
    }
    public void setZespol(Zespol zespol) {
        this.zespol = zespol;
    }

    public int getID() {
        return this.ID;
    }

    public static Set<Entry<Integer, Zadanie>> getEntries() {
        return hmZadan.entrySet();
    }

    public static Zadanie getZadanieByID(Integer ID) {
        return hmZadan.get(ID);
    }

    public void setLatch(CountDownLatch cdl) {
        this.latch = cdl;
        if (this.stan == StanZadania.Zakonczone)
            this.latch.countDown();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nZadanie: ").append(nazwa)
            .append("\n").append(opis)
            .append("\nZespół: ").append(zespol.getNazwa())
            .append("\nCzas utworzenia: ").append(this.dataUtworzenia)
            .append("\nCzas zakończenia: ").append(dataZakonczenia != null ? dataZakonczenia :  "nieukończone");
        return sb.toString();
    }

}

class NieMoznaRunException
    extends RuntimeException {
        public NieMoznaRunException(String message) {
            super(message);
        }
    }