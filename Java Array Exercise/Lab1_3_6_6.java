public class Lab1_3_6_6 {

    public int detect111 (short[] sArray, int iDifference) {

        for (int iVal1Index = 0; iVal1Index < sArray.length; iVal1Index++) {

            int iValue2 = (sArray[iVal1Index] + iDifference) % 32767;
            int iValue3 = (sArray[iVal1Index] + (iDifference * 2)) % 32767;

            boolean iVal2Index = findValue(sArray, iValue2, iVal1Index+1, sArray.length + iVal1Index - 1);

            boolean iVal3Index = findValue(sArray, iValue3, iVal1Index+2, sArray.length + iVal1Index - 1);

            if (iVal2Index & iVal3Index)
                return iVal1Index;
        }

        return -1;
    }

    public boolean findValue (short arr[], int iTarget, int iLeft, int iRight) {

        while (iLeft <= iRight) {
            if (iTarget == arr[iLeft % arr.length])
                return true;
            else iLeft++;
        }

        //cannot find value
        return false;
    }

    public static void main (String[] args) {

        short[] data = { 32767, 84, 9, 20, 31, 40, 55};

        Lab1_3_6_6 oExercise = new Lab1_3_6_6();

        System.out.println("Start position of 1-1-1: " + oExercise.detect111(data,20));
    }
}
