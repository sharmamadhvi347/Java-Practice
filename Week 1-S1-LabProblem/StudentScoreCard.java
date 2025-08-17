import java.util.Random;

public class StudentScoreCard {

    
    public static int[][] generateScores(int n) {
        Random rand = new Random();
        int[][] scores = new int[n][3]; 
        for (int i = 0; i < n; i++) {
            scores[i][0] = 10 + rand.nextInt(90); 
            scores[i][1] = 10 + rand.nextInt(90); 
            scores[i][2] = 10 + rand.nextInt(90); 
        }
        return scores;
    }

   
    public static double[][] calculateResults(int[][] scores) {
        double[][] results = new double[scores.length][3]; 
        for (int i = 0; i < scores.length; i++) {
            int total = scores[i][0] + scores[i][1] + scores[i][2];
            double avg = total / 3.0;
            double percentage = (total / 300.0) * 100;

            
            avg = Math.round(avg * 100.0) / 100.0;
            percentage = Math.round(percentage * 100.0) / 100.0;

            results[i][0] = total;
            results[i][1] = avg;
            results[i][2] = percentage;
        }
        return results;
    }

    
    public static String[] calculateGrades(double[][] results) {
        String[] grades = new String[results.length];
        for (int i = 0; i < results.length; i++) {
            double perc = results[i][2];
            if (perc >= 90) grades[i] = "A+";
            else if (perc >= 80) grades[i] = "A";
            else if (perc >= 70) grades[i] = "B";
            else if (perc >= 60) grades[i] = "C";
            else if (perc >= 50) grades[i] = "D";
            else grades[i] = "F";
        }
        return grades;
    }

    
    public static void displayScoreCard(int[][] scores, double[][] results, String[] grades) {
        System.out.println("ID\tPhysics\tChemistry\tMaths\tTotal\tAverage\t\t%age\tGrade");
        for (int i = 0; i < scores.length; i++) {
            System.out.println((i + 1) + "\t" +
                               scores[i][0] + "\t" +
                               scores[i][1] + "\t\t" +
                               scores[i][2] + "\t" +
                               (int)results[i][0] + "\t" +
                               results[i][1] + "\t\t" +
                               results[i][2] + "\t" +
                               grades[i]);
        }
    }

    
    public static void main(String[] args) {
        int students = 5; 
        int[][] scores = generateScores(students);
        double[][] results = calculateResults(scores);
        String[] grades = calculateGrades(results);
        displayScoreCard(scores, results, grades);
    }
}

