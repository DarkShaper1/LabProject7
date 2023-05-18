import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class SchoolClass implements Serializable {
    int number_of_class;
    double avg_marks;
    ArrayList<Schoolboy> pupils = new ArrayList<Schoolboy>();

    public SchoolClass(int number_of_class) {
        this.number_of_class = number_of_class;
    }

    public SchoolClass() {
        this.number_of_class = 0;
        this.pupils = new ArrayList<Schoolboy>();
    }

    public SchoolClass(SchoolClass clone) {
        this.number_of_class = clone.number_of_class;
        this.pupils = clone.pupils;
        this.avg_marks = clone.avg_marks;
    }

    public void setNumber_of_class(int number_of_class) {
        this.number_of_class = number_of_class;
    }

    public void setPupils(ArrayList<Schoolboy> pupils) {
        this.pupils = pupils;
    }

    public int getNumber_of_class() {
        return number_of_class;
    }

    public ArrayList<Schoolboy> getPupils() {
        return pupils;
    }

    public String toString(){
        return "Ученики " + this.number_of_class + " класса";
    }

    public void addSchoolboy(Schoolboy pupil) {
        this.pupils.add(pupil);
    }

    public double getAvg_marks() {
        int count = 0;
        double avg = 0;
        for (Schoolboy pupil: pupils) {
            count++;
            avg = avg + pupil.getMark();
        }
        return avg/count;
    }

    public static final Comparator<SchoolClass> COMPARE_BY_NUMBER_OF_CLASS = new Comparator<SchoolClass>() {
        @Override
        public int compare(SchoolClass lhs, SchoolClass rhs) {
            return lhs.getNumber_of_class() - rhs.getNumber_of_class();
        }
    };

    public static final Comparator<SchoolClass> COMPARE_BY_AVG_MARK = new Comparator<SchoolClass>() {
        @Override
        public int compare(SchoolClass lhs, SchoolClass rhs) {
            return Double.compare(rhs.getAvg_marks(), lhs.getAvg_marks());
        }
    };
}
