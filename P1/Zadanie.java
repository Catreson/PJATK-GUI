import java.time.LocalDateTime;

public class Zadanie 
    extends Thread {
    enum Stan{
        Utworzone,
        Rozpoczete,
        Zakonczone,
    }
    private static int incr = 0;
    private int ID = 0;
    private String nazwa;
    private String opis;
    private boolean zatwierdzenie;
    private Stan stan;
    private LocalDateTime dataUtworzenia;
    private LocalDateTime dataZakonczenia;
    private float czasWykonania;
    public Zadanie(String nazwa, String opis, boolean zatwierdzenie)
    {
        this.nazwa = nazwa;
        this.opis = opis;
        this.zatwierdzenie = zatwierdzenie;
        this.dataUtworzenia = LocalDateTime.now();
        this.ID = incr++;
    }
    public Zadanie(String nazwa) {
        this(nazwa, "Tutaj wpisz nazwe zadania.", false);
    }

    @Override
    public void run() {
        if(!zatwierdzenie)
            return;
        
    }
}
