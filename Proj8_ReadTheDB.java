/* ==========================================================
 * Assignment:  Proj8_ReadTheDB (Project #8)
 *      Author:  Jamie John David (jjdavid14@email.arizona.edu)
 * 
 *      Course:  CSc 127A, Fall 2015
 *  Instructor:  R. Lewis
 * 
 *Sect. Leader:  Sujan Patel
 *    Due Date:  11/03/2015, 9:00PM
 * ----------------------------------------------------------
 * Description:  This program reads a text file containing
 *               data. Stores the information gathered from
 *               the data and outputs all valid data.
 * 
 *       Input:  The text file containing the data.
 * 
 *       Ouput:  The program will draw display the information
 *               retrieved from the data. Information shown are
 *               full name and age.
 * 
 *  Known Bugs:  None; the program operates as intended.
 * ==========================================================
 */

import java.io.File;                            //Access to File
import java.io.FileNotFoundException;           //Handle possible exception
import java.util.Arrays;                        //Access to Arrays methods
import java.util.Scanner;                       //Access to Scanner

public class Proj8_ReadTheDB {
    public static void main(String[] args) throws FileNotFoundException {
        
        //If no location for text file is entered, terminates program
        if(args.length == 0) {
            System.out.println("Error. Please enter file");
            System.exit(0);
        }
        
        final int ELEMENTS = 1000;                 //# of Elements for array
        String[] firstName = new String[ELEMENTS]; //Array to store last names
        String[] lastName = new String[ELEMENTS];  //Array to store first names
        int[] age = new int[ELEMENTS];             //Array to store ages
        int numOfRecords = 0;                      //# of valid records
        int numOfErrors = 0;                       //# of invalid records
        
        //Reads the file by storing it in a Scanner
        Scanner in = new Scanner(new File(args[0]));
        
        //Reads the text file containing the data
        while(in.hasNextLine()){
            String line = in.nextLine(); //stores line from the text
            
            //Check if the line is empty, if so skip over
            if(line.isEmpty()) {
                continue;
            }
            
            //Separates the words separated by space and stores into array
            String[] words = line.split(" ");
            
            //Checks if the array of records is full, if so stop reading file
            if((numOfRecords == ELEMENTS) ||
               (numOfRecords == ELEMENTS) || (numOfRecords == ELEMENTS)) {
                System.out.println("Error. Arrays full.");
                break;
            }
            
            //Checks if line is in correct format, if so store the record
            //if not do not store and print error message
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
        
        //Creates new arrays large enough to hold the records
        firstName = Arrays.copyOf(firstName, numOfRecords);
        lastName = Arrays.copyOf(lastName, numOfRecords);
        age = Arrays.copyOf(age, numOfRecords);
        
        //Prints the stored records
        print(firstName, lastName, age, numOfRecords, numOfErrors);
    }
    
    /*
     * Evaluates whether the word is a valid name
     * 
     * @param name The word which is used for first or last name
     * @return true if the word is a valid name, false if not
     */
    public static boolean isValidName(String name) {
        char[] c = name.toCharArray();
        for(int i=0;i<c.length;i++) {
            if(Character.isDigit(c[i])){
                return false;
            }
        }
        return true;
    }
    
    /*
     * Evaluates whether the word extracted from the line
     * is convertable to int
     * 
     * @param age The word which is used for age
     * @return true if the word is convertable to int, false if not
     */
    public static boolean isValidAge(String age) {
        char[] c = age.toCharArray();
        for(int i=0;i<c.length;i++) {
            if((!Character.isDigit(c[i])) || (Integer.parseInt(age) < 0)){
                return false;
            }
        }
        return true;
    }            

    /*
     * Evaluates whether the line is in correct format or not
     * 
     * @param w[] Array containing the words extracted from the line
     * @return true if the line is in correct form, false if not
     */
    public static boolean isCorrectFormat(String[] w) {
        if((w.length < 3) || (w.length > 3)){
            return false;
        }
        return isValidName(w[0]) && isValidName(w[1]) && isValidAge(w[2]);
    }
    
    /*
     * Prints the number of records stored, number of wrong formatted
     * information, and the records themselves.
     * 
     * @param fname[] Array of first names
     * @param lname[] Array of last names
     * @param age[]   Array of ages
     * @param rec     Number of recorded information
     * @param err     Number of unrecorded informaton(wrong format)
     */
    public static void print(String[] fname, String[] lname, int[] age,
                             int rec, int err) {
        System.out.println("\nNumber of Records: " + rec);
        System.out.println("Number of Wrong Formats: " + err);
        System.out.println("\nRecords:");
        System.out.println("-------------------------");
        for(int i=0;i<rec;i++) {
            System.out.printf("%s %s %d\n",fname[i],lname[i],age[i]);
        }
    }
}
