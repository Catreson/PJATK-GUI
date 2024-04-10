import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Zadanie 
    extends Thread {
    private static AtomicInteger incr = new AtomicInteger();
    private int ID = 0;
    private String nazwa;
    private String opis;
    private boolean zatwierdzenie;
    private StanZadania stan;
    private LocalDateTime dataUtworzenia;
    private LocalDateTime dataZakonczenia;
    private double czasWykonania;
    private Zespol zespol;

    public Zadanie(String nazwa, String opis, Zespol zespol, boolean zatwierdzenie)
    {
        this.nazwa = nazwa;
        this.opis = opis;
        this.zatwierdzenie = zatwierdzenie;
        this.dataUtworzenia = LocalDateTime.now();
        this.czasWykonania = (Math.random() * 5) + 3;
        this.zespol = zespol;
        this.ID = incr.incrementAndGet();
    }
    public Zadanie(String nazwa) {
        this(nazwa, "Tutaj wpisz opis zadania.", new Zespol("Generic Zespol", null, null), false);
    }

    @Override
    public void run() throws NieMoznaRunException {
        int timer = 0;
        czyMoznaZaczac();
        System.out.printf("Rozpoczęto zadanie: %s o ID: %d\n", this.nazwa,  this.ID);
        while(timer++ < czasWykonania) {
            System.out.printf("Zadanie: %s o ID: %d, pracuje wlasnie: %d sekunde\n", this.nazwa,  this.ID, timer);
            try {
                sleep(1000);
            }
            catch(InterruptedException e) {
                throw new NieMoznaRunException(e.getMessage());
            }
        }
        System.out.printf("Zakończono zadanie: %s o ID: %d\n", this.nazwa,  this.ID);
    }

    public StanZadania geStanZadania() {
        return  stan;
    }

    private void czyMoznaZaczac() throws NieMoznaRunException{
        if (!zatwierdzenie)
            throw new NieMoznaRunException("Nie zatwierdzone");
        
    }

    public String getOpis() {
        return this.opis;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDataUtworzenia() {
        return this.dataUtworzenia;
    }

    public LocalDateTime getDataZakonczenia() {
        return this.dataZakonczenia;
    }

    public Zespol getZespol() {
        return this.zespol;
    }
    public void setZespol(Zespol zespol) {
        this.zespol = zespol;
    }

}

class NieMoznaRunException
    extends RuntimeException {
        public NieMoznaRunException(String message) {
            super(message);
        }
    }