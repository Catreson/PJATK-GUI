public interface IDobryPracownik {
    public void pracuj();
    
    default public String ileZarabia(){
        return "Poufne";
    };

    public DzialPracownikow gdziePracuje();
}
