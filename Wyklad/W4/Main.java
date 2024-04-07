import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Suit suit = Suit.CLUBS;
        System.out.println(suit);
        Cards ace = Cards.ACE;
        Cards two = Cards.TWO;
        Cards jack = Cards.JACK;
        System.out.println(two.equals(jack)); 
        System.out.println(ace.compareTo(jack));
        //-----------------------------------------
        Student[] liStudents = new Student[]{
            new Student(1, "Andrzej"),
            new Student(2, "Pawel"),
            new Student(3, "Ania"),
        };

        HashSet<Student> hashSet = new HashSet<>();
        hashSet.add(new Student(1, "Andrzej"));
        hashSet.add(new Student(2, "Pawel"));
        hashSet.add(new Student(3, "Ania"));
        for(Student s : hashSet)
            System.out.println("hs: " + s);

        HashMap<Integer, Student> hashMap = new HashMap<>();
        hashMap.put(liStudents[0].getID(), liStudents[0]);
        hashMap.put(liStudents[1].getID(), liStudents[1]);
        hashMap.put(liStudents[2].getID(), liStudents[2]);
        for(Integer ID : hashMap.keySet())
            System.out.println("hm: " + hashMap.get(ID));
    }
}