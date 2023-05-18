import java.util.Comparator;

public class Schoolboy {
    String name;
    String surname;
    int number_of_class;
    String subject;
    int mark;

    public Schoolboy(String surname, String name, int number_of_class, String subject, int mark) {
        this.surname = surname;
        this.name = name;
        this.number_of_class = number_of_class;
        this.subject = subject;
        this.mark = mark;
    }

    public Schoolboy() {
        this.surname = "";
        this.name = "";
        this.number_of_class = 0;
        this.subject = "";
        this.mark = 0;
    }

    public Schoolboy(Schoolboy clone) {
        this.name = clone.name;
        this.surname = clone.surname;
        this.number_of_class = clone.number_of_class;
        this.subject = clone.subject;
        this.mark = clone.mark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber_of_class(int number_of_class) {
        this.number_of_class = number_of_class;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNumber_of_class() {
        return number_of_class;
    }

    public int getMark() {
        return mark;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getSurname() {
        return surname;
    }

    public String toString() {
        return this.surname;
    }

    public static final Comparator<Schoolboy> COMPARE_BY_SURNAME = new Comparator<Schoolboy>() {
        @Override
        public int compare(Schoolboy o1, Schoolboy o2) {
            return o1.getSurname().compareTo(o2.getSurname());
        }
    };
}
