package tables;

public class Teacher {

    private int id;
    private String name;
    private String surname;
    private String address;
    private String email;

    public Teacher(int id, String name, String surname, String address, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name + " " + surname;

    }
}
