/*
Cayla Mason
edoras acct cssc0973
CS 310
Shawn Healey
TTh section

Lab 2
Due 2019-10-21
 */

import java.util.*;
import java.io.*;

public class Manager {

    private Scanner scanner;

    //reads a CSV file, returns List of array of Strings
    //tested & confirmed
    public List<String[]> readCSV (String fileName) {

        try {
            scanner = new Scanner(new File(fileName));
            List<String[]> parts = new ArrayList<>();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] sArray = line.split(",");
                parts.add(sArray);

            }
            scanner.close();


            return parts;

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
        return null;
    }

    //converts a List of an array of strings to an ArrayList of integers
    //tested & confirmed

    public ArrayList<Integer> toIntArray (List<String[]> asList) {

        ArrayList<Integer> patterns = new ArrayList<>();

        for (int i = 0; i < asList.size(); i++) {
            for (int j = 0; j < asList.get(i).length; j++)
                patterns.add(Integer.parseInt(asList.get(i)[j]));
        }

        return patterns;
    }

    //convert a List of an array of Strings to a queue of integers
    //tested & confirmed
    public MyQueue toIntQueue (List<String[]> asList) {

        MyQueue qValues = new MyQueue();

        for (int i = 0; i < asList.size(); i++) {
            for (int j = 0; j < asList.get(i).length; j++)
                qValues.enqueue(Integer.parseInt(asList.get(i)[j]));
        }

        return qValues;
    }


    public static void main (String[] args) {

        Manager oManage = new Manager();
        SoftwareDecode oSD = new SoftwareDecode();
        MyQueue qValues = new MyQueue();
        ArrayList<Integer> patterns = new ArrayList<>();

        //take in patterns and values files, place in appropriate data structure. Sets pattern spacing.
        try {
            for (int i = 0; i < args.length; i++) {
                List<String[]> sArray = oManage.readCSV(args[i]);
                if (sArray.size() == 1) {
                    qValues = oManage.toIntQueue(sArray);
                } else {
                    patterns = oManage.toIntArray(sArray);
                    oSD.setDifference(patterns);
                }
            }
        }
        catch (Exception e){
            System.out.println("Incorrect files entered. " +
                    "Enter two csv files, one for patterns and one for values.");
        }



        try{
            //create output file
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("decodedMilesWords.txt"));

            //call decoder
            int word;
            do {
                word = oSD.decoder(qValues, patterns);
                if (word != -1) {
                    fileWriter.write(Integer.toString(word) + ",");
                } else {
                    fileWriter.write("No code words transmitted.");
                }
            } while (!qValues.isEmpty());

            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
