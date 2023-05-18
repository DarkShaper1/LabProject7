import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Schoolboy> all_pupil = new ArrayList<Schoolboy>();
        ArrayList<SchoolClass> all_class = new ArrayList<SchoolClass>();
        try {
            File file = new File("data_school.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    String[] schoolboy = line.split(" ");
                    all_pupil.add(new Schoolboy(schoolboy[0], schoolboy[1], Integer.parseInt(schoolboy[2]), schoolboy[3], Integer.parseInt(schoolboy[4])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Schoolboy pupil : all_pupil) {
            int number_of_class = pupil.getNumber_of_class();
            boolean flag = true;
            for (SchoolClass c : all_class) {
                if (number_of_class == c.getNumber_of_class()) {
                    c.addSchoolboy(pupil);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                all_class.add(0, new SchoolClass(number_of_class));
                all_class.get(0).addSchoolboy(pupil);
            }
        }
        all_class.sort(SchoolClass.COMPARE_BY_NUMBER_OF_CLASS);
        for (SchoolClass c: all_class) {
            File file = new File("Ученики " + c.getNumber_of_class() + " класса.txt");
            System.out.println("Ученики " + c.getNumber_of_class() + " класса:");
            for (Schoolboy pupil : c.getPupils()) {
                String text = pupil.getSurname() + " " + pupil.getName() + " " + pupil.getSubject() + " " + pupil.getMark();
                System.out.println(text);
                try
                {
                    FileWriter writer = new FileWriter(file, true);
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(text + "\n");
                    bufferWriter.close();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
            System.out.println();
        }
        Scanner input = new Scanner(System.in);
        System.out.print("Введите оценку: ");
        int number_of_mark = input.nextInt();
        for (SchoolClass c : all_class) {
            System.out.println("Ученики " + c.getNumber_of_class() + " класса с оценкой " + number_of_mark + ":");
            for (Schoolboy pupil : c.getPupils()) {
                if (pupil.getMark() == number_of_mark) {
                    String text = pupil.getSurname() + " " + pupil.getName() + " " + pupil.getSubject() + " " + pupil.getMark();
                    System.out.println(text);
                }
            }
            System.out.println();
        }
        all_class.sort(SchoolClass.COMPARE_BY_AVG_MARK);
        for (SchoolClass c: all_class) {
            System.out.println("Класс " + c.getNumber_of_class() + " со средней успеваемостью: " + c.getAvg_marks());
        }
        System.out.println();
        System.out.print("Введите предмет: ");
        String name_of_subect = input.next();
        ArrayList<Schoolboy> pupils_of_subject = new ArrayList<Schoolboy>();
        for (SchoolClass c : all_class) {
            for (Schoolboy pupil : c.getPupils()) {
                if (Objects.equals(pupil.getSubject(), name_of_subect)) {
                    pupils_of_subject.add(pupil);
                }
            }
        }
        pupils_of_subject.sort(Schoolboy.COMPARE_BY_SURNAME);
        for (Schoolboy pupil : pupils_of_subject) {
            String text = pupil.getSurname() + " " + pupil.getName() + " " + pupil.getSubject() + " " + pupil.getMark();
            System.out.println(text);
        }
        System.out.println();
        System.out.print("Введите номер класса для которого нужно создать ведомость: ");
        int number_of_class_2 = input.nextInt();
        SchoolClass chosen_class = new SchoolClass();
        ArrayList<String> subjects = new ArrayList<String>();
        for (SchoolClass c: all_class) {
            if (c.getNumber_of_class() == number_of_class_2) {
                chosen_class = c;
                for (Schoolboy pupil: c.getPupils()){
                    if (!subjects.contains(pupil.getSubject())) {
                        subjects.add(pupil.getSubject());
                    }
                }
                break;
            }
        }
        for (String subject: subjects){
            File file2 = new File("Ведомость " + chosen_class + " класса");
            String text = subject + ":";
            try
            {
                FileWriter writer = new FileWriter(file2, true);
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                bufferWriter.write(text + "\n");
                bufferWriter.close();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            for (Schoolboy pupil: chosen_class.getPupils()){
                if (Objects.equals(pupil.getSubject(), subject)) {
                    text = "    " + pupil.getSurname() + " " + pupil.getName() + " " + pupil.getMark();
                    try
                    {
                        FileWriter writer = new FileWriter(file2, true);
                        BufferedWriter bufferWriter = new BufferedWriter(writer);
                        bufferWriter.write(text + "\n");
                        bufferWriter.close();
                    }
                    catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
        System.out.print("Введите фамилию и имя через пробел: ");
        String surname = input.next();
        String name = input.next();
        for (SchoolClass c : all_class) {
            for (Schoolboy pupil : c.getPupils()) {
                if (Objects.equals(pupil.getName(), name) & Objects.equals(pupil.getSurname(), surname)) {
                    System.out.println(surname + " " + name + " учится в " + pupil.getNumber_of_class() + " классе");
                    break;
                }
            }
        }
        System.out.println();
        ArrayList<String> subjects2 = new ArrayList<String>();
        for (SchoolClass c: all_class) {
            for (Schoolboy pupil: c.getPupils()){
                if (!subjects2.contains(pupil.getSubject())) {
                    subjects2.add(pupil.getSubject());
                }
            }
            break;
        }

        double avg = 0;
        int count = 0;
        double max_avg = 0;
        String max_subj = "";
        for (String i: subjects2) {
            for (SchoolClass c : all_class) {
                for (Schoolboy pupil : c.getPupils()) {
                    if (Objects.equals(pupil.getSubject(), i)) {
                        avg = avg + pupil.getMark();
                        count++;
                    }
                }
            }
            avg = avg / count;
            if (avg > max_avg) {
                max_avg = avg;
                max_subj = i;
            }
        }
        System.out.println("Самая высокая средняя успеваемость у " + max_subj);
    }
}