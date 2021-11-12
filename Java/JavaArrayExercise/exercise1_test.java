import java.security.SecureRandom;

public class exercise1_test {

    public static void main (String[] args) {

        exercise1 oExercise = new exercise1();

        int iArraySize = 20;
        int[] iArrayNumbers = new int[iArraySize];
        SecureRandom oRand = new SecureRandom();
        for(int rowCounter = 0; rowCounter < iArrayNumbers.length; rowCounter++){
            iArrayNumbers[rowCounter] = oRand.nextInt(iArraySize);
        }

        int iTargetSum = 16;

        System.out.println("Array values: ");
        for (int i = 0; i < iArrayNumbers.length; i++)
            System.out.print(iArrayNumbers[i] + " ");

        System.out.println("\nTarget sum: " + iTargetSum);

        oExercise.linearVsBinarySearch(iArrayNumbers, iArraySize, iTargetSum);

    }

}
