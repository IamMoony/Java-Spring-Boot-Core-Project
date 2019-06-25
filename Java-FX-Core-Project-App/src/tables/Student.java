package tables;

public class Student {

    private int id;
    private String name;
    private String surname;
    private String address;
    private String contactPerson;
    private int fk_class_id;


    public Student(int id, String name, String surname, String address, String contactPerson, int fk_class_id) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.contactPerson = contactPerson;
        this.fk_class_id = fk_class_id;
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

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID " + id + ":" +
                name + ", " + surname;

    }
}
