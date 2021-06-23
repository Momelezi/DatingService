import java.util.*;

public class DatingService{

    private int n;
    Scanner input = new Scanner(System.in);

    int[][] compatibilityVals;
    int[] compatible = new int[10];
    int[] male;
    int[] female;
    int count = 0;

    public void run() {
        generate();
        displayArray();
        match();
        display();

    }

    public void generate() {
        
        System.out.println("Please enter the number of Male/Female: ");
        n = input.nextInt();
        compatibilityVals = new int[n][n];
        male = new int[n];
        female = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                compatibilityVals[i][j] = (int) (Math.random() * 100);
            }
        }
    }

    public void displayArray() {
        for (int i = 0; i < compatibilityVals.length; i++) {
            for (int j = 0; j < compatibilityVals[0].length; j++) { 
                System.out.print(String.format("%-3d", compatibilityVals[i][j]));
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void match() {
        int highest = compatibilityVals[0][0];
        int row = 0, col = 0;
        for (int i = 0; i < compatibilityVals.length; i++) {
            for (int j = 0; j < compatibilityVals[0].length; j++) {
                if (highest < compatibilityVals[i][j]) {
                    highest = compatibilityVals[i][j];
                    row = i;
                    col = j;
                }
            }
        }
        compatible[count] = highest;
        male[count] = row;
        female[count] = col;
        highest = -1;
        eliminate(compatibilityVals, male[count], female[count]);
        count++;
        if (notNegative()) {	
            match();
        }
    }

    public boolean notNegative() {

        for (int i = 0; i < compatibilityVals.length; i++) {
            for (int j = 0; j < compatibilityVals[0].length; j++) {
                if (compatibilityVals[i][j] != -1) {
                    return true;
                }
            }
        }
        return false;			
    }

    public void eliminate(int[][] array, int row, int col) {
        for (int i = 0; i < array.length; i++) {
            array[row][i] = -1;
        }

        for (int j = 0; j < array[0].length; j++) {
            array[j][col] = -1;
        }

    }

    public void display() {
 
        System.out.println("MATCHES: ");
        System.out.println(String.format("%-10s%-10s%-10s", "Male", "Female", "Compatible"));
        System.out.println("--------------------------");
        for (int i = 0; i < compatibilityVals.length; i++) {
            System.out.println(String.format("%-10d%-10d%-10d", male[i], female[i], compatible[i]));
        }
    }

    public static void main(String[] args) {
        DatingService obj = new DatingService();
        obj.run();
    }
}
