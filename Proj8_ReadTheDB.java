import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;                       //Access to Scanner

public class Proj8_ReadTheDB {
    public static void main(String[] args) throws FileNotFoundException {
        
        if(args.length == 0) {
            System.out.println("Error. Please enter file");
            System.exit(0);
        }
        final int ELEMENTS = 1000;
        String[] firstName = new String[ELEMENTS];  //Array for storing last names
        String[] lastName = new String[ELEMENTS];   //Array for storing first names
        int[] age = new int[ELEMENTS];              //Array for storing ages
        Scanner in = new Scanner(new File(args[0]));
        int numOfRecords = 0;
        int numOfErrors = 0;
        
        
        while(in.hasNextLine()){
            String line = in.nextLine();
            if(line.isEmpty()) {
                continue;
            }
            String[] words = line.split(" ");
            
            if((numOfRecords == ELEMENTS) ||
               (numOfRecords == ELEMENTS) || (numOfRecords == ELEMENTS)) {
                System.out.println("Error. Arrays full.");
                break;
            }
            
            
            if(isCorrectFormat(words)) {
                firstName[numOfRecords] = words[0];
                lastName[numOfRecords] = words[1];
                age[numOfRecords] = Integer.parseInt(words[2]);
                numOfRecords++;
            } else {
                System.out.println("(" + line + ") Error. Wrong format.");
                numOfErrors++;
                continue;
            }
        }
        
        firstName = Arrays.copyOf(firstName, numOfRecords);
        lastName = Arrays.copyOf(lastName, numOfRecords);
        age = Arrays.copyOf(age, numOfRecords);
        
        System.out.println("\nNumber of Records: " + numOfRecords);
        System.out.println("Number of Wrong Formats: " + numOfErrors);
        System.out.println("\nRecords:");
        System.out.println("-------------------------");
        for(int i=0;i<numOfRecords;i++) {
            System.out.printf("%s %s %d\n",firstName[i],lastName[i],age[i]);
        }
    }
    
    public static boolean isValidName(String name) {
        char[] c = name.toCharArray();
        for(int i=0;i<c.length;i++) {
            if(Character.isAlphabetic(c[i]) == false){
                return false;
            }
        }
        return true;
    }
    
    public static boolean isValidAge(String age) {
        char[] c = age.toCharArray();
        for(int i=0;i<c.length;i++) {
            if((!Character.isDigit(c[i])) || (Integer.parseInt(age) < 0)){
                return false;
            }
        }
        return true;
    }
               


    public static boolean isCorrectFormat(String[] w) {
        if((w.length < 3) || (w.length > 3)){
            return false;
        }
        return isValidName(w[0]) && isValidName(w[1]) && isValidAge(w[2]);
    }
    
}
