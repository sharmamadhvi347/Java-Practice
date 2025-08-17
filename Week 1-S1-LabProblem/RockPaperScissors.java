import java.util.Scanner;

public class RockPaperScissors {

    
    public static String getComputerChoice() {
        int choice = (int) (Math.random() * 3); 
        switch (choice) {
            case 0: return "rock";
            case 1: return "paper";
            default: return "scissors";
        }
    }

   
    public static String findWinner(String userChoice, String compChoice) {
        if (userChoice.equals(compChoice)) {
            return "draw";
        }
        switch (userChoice) {
            case "rock":
                return (compChoice.equals("scissors")) ? "player" : "computer";
            case "paper":
                return (compChoice.equals("rock")) ? "player" : "computer";
            case "scissors":
                return (compChoice.equals("paper")) ? "player" : "computer";
            default:
                return "invalid";
        }
    }

   
    public static String[][] calculateStats(int playerWins, int compWins, int totalGames) {
        String[][] stats = new String[2][3]; 

        double playerAvg = (double) playerWins / totalGames;
        double compAvg = (double) compWins / totalGames;
        double playerPercent = ((double) playerWins / totalGames) * 100;
        double compPercent = ((double) compWins / totalGames) * 100;

        stats[0][0] = String.valueOf(playerWins);
        stats[0][1] = String.format("%.2f", playerAvg);
        stats[0][2] = String.format("%.2f%%", playerPercent);

        stats[1][0] = String.valueOf(compWins);
        stats[1][1] = String.format("%.2f", compAvg);
        stats[1][2] = String.format("%.2f%%", compPercent);

        return stats;
    }

    
    public static void displayResults(String[][] gameResults, String[][] stats) {
        System.out.println("\nGame Results:");
        System.out.println("Game\tPlayer Choice\tComputer Choice\tWinner");
        System.out.println("------------------------------------------------------");
        for (String[] row : gameResults) {
            System.out.println(row[0] + "\t" + row[1] + "\t\t" + row[2] + "\t\t" + row[3]);
        }

        System.out.println("\nStats Table:");
        System.out.println("Player/Computer\tWins\tAverage\t\tWinning %");
        System.out.println("------------------------------------------------------");
        System.out.println("Player\t\t" + stats[0][0] + "\t" + stats[0][1] + "\t\t" + stats[0][2]);
        System.out.println("Computer\t" + stats[1][0] + "\t" + stats[1][1] + "\t\t" + stats[1][2]);
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of games to play: ");
        int totalGames = sc.nextInt();
        sc.nextLine(); 

        String[][] gameResults = new String[totalGames][4];
        int playerWins = 0, compWins = 0;

        for (int i = 0; i < totalGames; i++) {
            System.out.print("\nGame " + (i + 1) + " - Enter your choice (rock/paper/scissors): ");
            String playerChoice = sc.nextLine().toLowerCase();
            String compChoice = getComputerChoice();

            String winner = findWinner(playerChoice, compChoice);
            if (winner.equals("player")) playerWins++;
            else if (winner.equals("computer")) compWins++;

            
            gameResults[i][0] = String.valueOf(i + 1);
            gameResults[i][1] = playerChoice;
            gameResults[i][2] = compChoice;
            gameResults[i][3] = winner;
        }

        String[][] stats = calculateStats(playerWins, compWins, totalGames);
        displayResults(gameResults, stats);

        sc.close();
    }
}

