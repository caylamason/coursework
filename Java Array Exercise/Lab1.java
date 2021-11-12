/*
Cayla Mason
CS 310
Shawn Healey
TTh section
Lab 1
Due 2019-09-24


*/

public class Lab1 {

    /* Exercise 3.6.3

    Software Decode: Write a function that accepts an in-order array of unsigned short values. The function shall then
scan the array for a specific pattern: Three values contained within the array equally spaced 20 units apart. The
function shall return the index position within the original array where the pattern begins or -1 if not present.

Given the input array: data[] = {10,20,31,40,55,60,65525}

The function shall return: 1

        Exercise 3.6.5

        Modify Problem 3 to generalize the gap spacing. Instead of hard- coding the difference of 20 into the function,
        change the function sig- nature to include it as a parameter.


     */

    public int detect111 (short[] sArray, int iDifference)
    {
        for (int iVal1Index = 0; iVal1Index < sArray.length-2; iVal1Index++) {
            int iValue2 = sArray[iVal1Index] + iDifference;
            //call findValue to locate iValue2
            int iVal2Index = findValue(sArray, iValue2, iVal1Index+1, sArray.length-1);
            int iVal3Index = 2 * iVal2Index - iVal1Index;
            if (sArray[iVal3Index] == sArray[iVal1Index] + iDifference * 2)
                return iVal1Index;
        }

        return -1;
    }

/*  Exercise 3.6.4

* 1-1-0 Detection: Modify Problem 3 such that it returns the index of the beginning of a sequence where the first two
* elements are 20 units apart and there is NO element 40 units away from the first. Two elements present, one absent
* */

    public int detect110 (short[] sArray, int iDifference)
    {
        for (int iVal1Index = 0; iVal1Index < sArray.length-2; iVal1Index++) {
            int iValue2 = sArray[iVal1Index] + iDifference;
            //call findValue to locate iValue2
            int iVal2Index = findValue(sArray, iValue2, iVal1Index+1, sArray.length-1);
            int iVal3Index = 2 * iVal2Index - iVal1Index;
            if (sArray[iVal3Index] != sArray[iVal1Index] + iDifference * 2)
                return iVal1Index;
        }

        return -1;
    }


    //find the position at which my value of interest resides
    //using this method assumes there are no duplicate values
    public int findValue (short arr[] , int iTarget, int iLeft, int iRight) {

        while (iLeft <= iRight) {
            int iMiddle = (iLeft + iRight)/2;
            if (arr[iMiddle] < iTarget) {
                iLeft = iMiddle + 1;
            } else if (arr[iMiddle] > iTarget) {
                iRight = iMiddle - 1;
            } else return iMiddle;
        }

        //cannot find value
        return -1;
    }

    public static void main (String[] args)
    {
        short data[] = {10,20,30,40,55,60,32762};

        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }

        Lab1 oExercise = new Lab1();

        System.out.println();
        System.out.println("Start position of 1-1-1: " + oExercise.detect111(data, 20));
        System.out.println("Start position of 1-1-0: " + oExercise.detect110(data, 20));
    }
}
