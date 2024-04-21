import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        OutputManager.setFile();
        HashMap<String, DzialPracownikow> dzialy = new HashMap<>();
        try {
            dzialy.put("Programisci", DzialPracownikow.createDzial("Programiści"));
            dzialy.put("Analitycy", DzialPracownikow.createDzial("Analitycy"));
            dzialy.put("HR", DzialPracownikow.createDzial("HR"));
        }
        catch(NotUniqueNameException e)
        {
            OutputManager.printToFileAndConsole(e);
            OutputManager.printToFileAndConsole(e.getStackTrace());
        }
        LinkedList<Pracownik> lPracowniks = new LinkedList<>();
        Manager manager = new Manager("Andrzej", "Mandrzej", LocalDate.of(1999, 1, 1), null, "Pawel", "123");
        Recepcjonista recepcjonista = new Recepcjonista("Anna", "Banna", LocalDate.of(1990, 1, 1), dzialy.get("HR"), "Ania", "Jablko");
        Recepcjonista recepcjonista1 = new Recepcjonista("Kasia", "Wanna", LocalDate.of(1940, 1, 2), dzialy.get("HR"), "Kasia", "Gruszka");
        Trener trener = new Trener("Jakub", "Nowal", LocalDate.of(1979, 12, 11), dzialy.get("Analitycy"), "Wyciskanie Lezac");
        lPracowniks.add(recepcjonista);
        lPracowniks.add(recepcjonista1);
        lPracowniks.add(trener);
        lPracowniks.add(manager);

        System.out.println(recepcjonista.getInitial());
        recepcjonista.setName("Paweł");
        System.out.println(recepcjonista.getInitial());

        Zespol zespol = new Zespol("Alpha", manager, lPracowniks);

        Zadanie  zadanie = new Zadanie("Zmywanie", "Zadanie polegające na zmywaniu", zespol, true);
        Zadanie  zadanie1 = new Zadanie("Sprzątanie", "Zadanie polegające na sprzątaniu", zespol, true);
        Zadanie  zadanie2 = new Zadanie("Gotowanie", "Zadanie polegające na gotowaniu", zespol, true);

        Praca praca = new Praca("Praca testowa", zespol, new ArrayList<Zadanie>() {{ add(zadanie);}});
        Thread t = new Thread(praca);
        t.start();
        zadanie.start();

        OutputManager.printToFileAndConsole(dzialy.get("HR").getPracownicy());
        OutputManager.printToFileAndConsole(praca.getZadanie(1));
    }
    
}
