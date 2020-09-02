import java.util.ArrayList;

public class Person {

    private int id;
    private String name;
    private String email;
    private ArrayList<String> dates;

    public Person(int id, String name, String email, ArrayList<String> dates){
        setId(id);
        setName(name);
        setEmail(email);
        setDates(dates);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getDates() {
        return dates;
    }
}
