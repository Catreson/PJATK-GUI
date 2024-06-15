package W11.ex02;
public class Student {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private String surname;
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    private int number;
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Student(String name, String surname, int number){
        this.name = name;
        this.surname = surname;
        this.number = number;
    }
    public String toString(){
        return name + " " + surname + " " + number;
    }
}
