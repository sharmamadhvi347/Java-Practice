import java.util.Scanner;

public class BMICalculator {

    
    public static String[] computeBMI(double weight, double heightCm) {
        double heightM = heightCm / 100.0; 
        double bmi = weight / (heightM * heightM);
        bmi = Math.round(bmi * 100.0) / 100.0; 

        String status;
        if (bmi < 18.5) status = "Underweight";
        else if (bmi < 25) status = "Normal";
        else if (bmi < 30) status = "Overweight";
        else status = "Obese";

        return new String[]{String.valueOf(bmi), status};
    }

   
    public static String[][] prepareBMIReport(double[][] hwData) {
        String[][] report = new String[hwData.length][4]; 
        for (int i = 0; i < hwData.length; i++) {
            String[] bmiData = computeBMI(hwData[i][0], hwData[i][1]);
            report[i][0] = String.valueOf(hwData[i][1]); 
            report[i][1] = String.valueOf(hwData[i][0]);
            report[i][2] = bmiData[0]; 
            report[i][3] = bmiData[1]; 
        }
        return report;
    }

    
    public static void displayTable(String[][] report) {
        System.out.println("Height(cm)\tWeight(kg)\tBMI\t\tStatus");
        System.out.println("-------------------------------------------------");
        for (String[] row : report) {
            System.out.println(row[0] + "\t\t" + row[1] + "\t\t" + row[2] + "\t\t" + row[3]);
        }
    }

   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] hwData = new double[10][2]; 

        System.out.println("Enter weight (kg) and height (cm) for 10 people:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Person " + (i + 1) + " - Weight (kg): ");
            hwData[i][0] = sc.nextDouble();
            System.out.print("Person " + (i + 1) + " - Height (cm): ");
            hwData[i][1] = sc.nextDouble();
        }

        String[][] report = prepareBMIReport(hwData);
        displayTable(report);

        sc.close();
    }
}

