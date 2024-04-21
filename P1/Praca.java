import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map.Entry;

public class Praca 
    implements Runnable {
    
        private ArrayList<Zadanie> waitForZadaniaList = new ArrayList<>();
        private Zespol zespol;
        private String opis;
        private AtomicInteger incr = new AtomicInteger(1);
        private int ID;

        public Praca(String opis, Zespol zespol) {
            this.zespol = zespol;
            this.ID = incr.getAndIncrement();
            this.opis = opis;
        }
        public Praca(String opis, Zespol zespol, ArrayList<Zadanie> waitForZadaniaList) {
            this(opis, zespol);
            this.waitForZadaniaList = waitForZadaniaList;
        }

        
        //Użyłem countdown latch, aby czekać na zadania które się jeszcze nie zaczęły (join nie czeka na thread, który się aktualnie nie wykonuje)
        @Override
        public void run() {
            OutputManager.printToFileAndConsole(String.format("Rozpoczęcie pracy o opisie: %s ID %d", this.opis, this.ID));
            int noZadan = 0;
            for (Zadanie z : waitForZadaniaList) {
                if (z.getStanZadania() != StanZadania.Zakonczone) {
                    noZadan++;
                }
            }
            CountDownLatch latch = new CountDownLatch(noZadan);
            for (Zadanie z : waitForZadaniaList) {
                if (z.getStanZadania() != StanZadania.Zakonczone) {
                    z.setLatch(latch);
                }
            }
            try {
                latch.await();
            } 
            catch(InterruptedException e) {
                StringBuilder sb = new StringBuilder(e.toString());
                for (StackTraceElement el : e.getStackTrace()) {
                    sb.append("\n").append(el);
                }
                OutputManager.printToFileAndConsole(sb);
            }
            ExecutorService es = Executors.newCachedThreadPool();
            for (Entry<Integer, Zadanie> e : Zadanie.getEntries()) {
                if (e.getValue().getZatwierdzenie() == true && e.getValue().getStanZadania() == StanZadania.Utworzone) {
                    try {
                        es.execute(e.getValue());
                    }
                    catch(NieMoznaRunException ee) {
                        StringBuilder sb = new StringBuilder(ee.toString());
                        for (StackTraceElement el : ee.getStackTrace()) {
                            sb.append("\n").append(el);
                            }
                        OutputManager.printToFileAndConsole(sb);
                    }
                }
                else 
                {
                    if(e.getValue().getZatwierdzenie() != true)
                        OutputManager.printToFileAndConsole(String.format("Zadanie: %s nie zatwierdzone", e.getValue().getNazwa()));
                    else
                        OutputManager.printToFileAndConsole(String.format("Zadanie: %s jest już %s", e.getValue().getNazwa(), e.getValue().getStanZadania()));
                }
            }
            es.shutdown();
            try {
                if(!es.awaitTermination(1, TimeUnit.MINUTES)) {
                    es.shutdownNow();
                    OutputManager.printToFileAndConsole("Wątki nie chcą współpracować");
                }
                else {
                    OutputManager.printToFileAndConsole("Threads terminated peacefully");
                }
            }
            catch(InterruptedException ee) {
                StringBuilder sb = new StringBuilder(ee.toString());
                for (StackTraceElement el : ee.getStackTrace()) {
                    sb.append("\n").append(el);
                    }
                OutputManager.printToFileAndConsole(sb);
            }
            OutputManager.printToFileAndConsole(String.format("Zakończenie pracy o opisie: %s ID %d", this.opis, this.ID));
        }

        public String getOpis() {
            return opis;
        }
        public void setOpis(String opis) {
            this.opis = opis;
        }

        public Zespol getZespol() {
            return zespol;
        }
        public void setZespol(Zespol zespol) {
            this.zespol = zespol;
        }

        public int getID() {
            return ID;
        }
        
        public Zadanie getZadanie(int ID) {
            return Zadanie.getZadanieByID(ID);
        }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nPraca: ").append(opis)
        .append("\nZespol: ").append(zespol.getNazwa())
        .append("\nZadania wymagane do uruchomienia:");
        for (Zadanie z : waitForZadaniaList) {
            sb.append("\n").append(z.getNazwa());
        }
        sb.append("\nZadania własne:");
        for (Entry<Integer, Zadanie> e : Zadanie.getEntries()) {
            sb.append("\n").append(e.getValue().getNazwa());
        }

        return sb.toString();
    }
}
