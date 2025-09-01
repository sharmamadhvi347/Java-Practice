import java.util.*;

class Subject {
    String subjectCode;
    String subjectName;
    int credits;
    String instructor;

    Subject(String subjectCode, String subjectName, int credits, String instructor) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.instructor = instructor;
    }
}

class Student {
    String studentId;
    String studentName;
    String className;
    String[] subjects;
    double[][] marks;
    double gpa;

    static int totalStudents = 0;
    static String schoolName = "Springfield High";
    static String[] gradingScale = {"A", "B", "C", "D", "F"};
    static double passPercentage = 40.0;

    Student(String studentId, String studentName, String className, String[] subjects) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.subjects = subjects;
        this.marks = new double[subjects.length][];
        for (int i = 0; i < subjects.length; i++) {
            this.marks[i] = new double[5];
        }
        this.gpa = 0.0;
        totalStudents++;
    }

    void addMarks(String subject, double mark) {
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].equalsIgnoreCase(subject)) {
                for (int j = 0; j < marks[i].length; j++) {
                    if (marks[i][j] == 0.0) {
                        marks[i][j] = mark;
                        return;
                    }
                }
            }
        }
    }

    void calculateGPA() {
        double total = 0;
        int count = 0;
        for (double[] subjectMarks : marks) {
            double sum = 0;
            int exams = 0;
            for (double m : subjectMarks) {
                if (m > 0) {
                    sum += m;
                    exams++;
                }
            }
            if (exams > 0) {
                total += (sum / exams);
                count++;
            }
        }
        double avg = count > 0 ? total / count : 0;
        gpa = avg / 20;
    }

    void generateReportCard() {
        System.out.println("Report Card - " + studentName + " (" + studentId + ")");
        for (int i = 0; i < subjects.length; i++) {
            double sum = 0;
            int exams = 0;
            for (double m : marks[i]) {
                if (m > 0) {
                    sum += m;
                    exams++;
                }
            }
            double avg = exams > 0 ? sum / exams : 0;
            String grade = getGrade(avg);
            System.out.println(subjects[i] + ": " + avg + " Grade: " + grade);
        }
        System.out.println("GPA: " + gpa);
        System.out.println("Promotion Eligible: " + checkPromotionEligibility());
    }

    boolean checkPromotionEligibility() {
        double total = 0;
        int count = 0;
        for (double[] subjectMarks : marks) {
            double sum = 0;
            int exams = 0;
            for (double m : subjectMarks) {
                if (m > 0) {
                    sum += m;
                    exams++;
                }
            }
            if (exams > 0) {
                total += (sum / exams);
                count++;
            }
        }
        double avg = count > 0 ? total / count : 0;
        return avg >= passPercentage;
    }

    static void setGradingScale(String[] scale) {
        gradingScale = scale;
    }

    static double calculateClassAverage(Student[] students) {
        double total = 0;
        int count = 0;
        for (Student s : students) {
            total += s.gpa;
            count++;
        }
        return count > 0 ? total / count : 0;
    }

    static List<Student> getTopPerformers(Student[] students, int count) {
        Arrays.sort(students, (a, b) -> Double.compare(b.gpa, a.gpa));
        List<Student> top = new ArrayList<>();
        for (int i = 0; i < count && i < students.length; i++) {
            top.add(students[i]);
        }
        return top;
    }

    static void generateSchoolReport(Student[] students) {
        System.out.println("School: " + schoolName);
        System.out.println("Total Students: " + totalStudents);
        System.out.println("Average GPA: " + calculateClassAverage(students));
        List<Student> top = getTopPerformers(students, 3);
        System.out.println("Top Performers:");
        for (Student s : top) {
            System.out.println(s.studentName + " GPA: " + s.gpa);
        }
    }

    static String getGrade(double percentage) {
        if (percentage >= 85) return gradingScale[0];
        else if (percentage >= 70) return gradingScale[1];
        else if (percentage >= 55) return gradingScale[2];
        else if (percentage >= 40) return gradingScale[3];
        else return gradingScale[4];
    }
}

public class Main_Q4 {
    public static void main(String[] args) {
        String[] subs = {"Math", "Science", "English"};

        Student s1 = new Student("S1", "Alice", "10A", subs);
        Student s2 = new Student("S2", "Bob", "10A", subs);
        Student s3 = new Student("S3", "Charlie", "10A", subs);

        s1.addMarks("Math", 90);
        s1.addMarks("Science", 85);
        s1.addMarks("English", 78);
        s1.calculateGPA();

        s2.addMarks("Math", 60);
        s2.addMarks("Science", 72);
        s2.addMarks("English", 65);
        s2.calculateGPA();

        s3.addMarks("Math", 40);
        s3.addMarks("Science", 55);
        s3.addMarks("English", 50);
        s3.calculateGPA();

        s1.generateReportCard();
        s2.generateReportCard();
        s3.generateReportCard();

        Student[] all = {s1, s2, s3};
        Student.generateSchoolReport(all);
    }
}


