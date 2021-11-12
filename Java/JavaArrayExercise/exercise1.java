// Cayla Mason
// CS310
// Lab 1
// Due 2019-09-23



import java.security.SecureRandom;
import java.util.Arrays;


/*"given an unsorted array of integers, its length, and
a target sum, write a function that return true if any
two items in the input array combine to equal the
target value"
*/

public class exercise1 {

    public void linearVsBinarySearch (int[] iArray, int iLength, int iTarget) {

        // --- BEGIN LINEAR SEARCH ---
        System.out.println("--- Begin Linear Search ---");
        long lStartTime = System.nanoTime();
        boolean bLinear = findSumLinear(iArray, iTarget);
        if (bLinear)
            System.out.println("Target sum found in linear search.");
        long lEndTime = System.nanoTime();
        System.out.println("Elapsed time: " + (lEndTime - lStartTime));

        // --- BEGIN BINARY SEARCH ---
        System.out.println("--- Begin Binary Search ---");
        lStartTime = System.nanoTime();
        boolean bBinary = findSumBinary(iArray, iLength, iTarget);
        if (bBinary)
            System.out.println("Target sum found in binary search.");
        lEndTime = System.nanoTime();
        System.out.println("Elapsed time: " + (lEndTime - lStartTime));

    }


    public boolean findSumLinear (int[] iArray, int iTarget)
    {
        for (int i = 0; i < iArray.length-1; i++){
            for (int k = 1; k < iArray.length; k++){
                if (iArray[i] + iArray[k] == iTarget) {
                    return true; }
            }
        }
        return false;
    }

    public int findSecondNumber (int[] iArray, int iMin, int iMax, int iSecondNum) {


        int iMiddle = (iMax + iMin) / 2;

        // Check if target is the middle.
        if (iSecondNum == iArray[iMiddle]) {
            return iMiddle;
        }
        // Check if target is greater than middle.
        else if (iSecondNum > iArray[iMiddle]) {

            // Recursively call this function adjusting low to the middle.
            return findSecondNumber(iArray, iSecondNum,
                    iMiddle + 1, iMax);
        }
        // target is less than middle.
        else {

            // Recursively call this function adjusting high to the middle.
            return findSecondNumber(iArray, iSecondNum,
                    iMin, iMiddle - 1);
        }
    }

    public boolean findSumBinary (int[] aiArray, int iLength, int iTarget) {

        Arrays.sort(aiArray);

        for (int i = 0; (i < aiArray.length - 1); i++) {
            int iSecondNumber = iTarget - aiArray[i];
            if (findSecondNumber(aiArray, i + 1, iLength - 1, iSecondNumber) == iSecondNumber) {
                return true;
            }
        }

        // cannot find the target sum
        return false;
    }


    }
