/*
Cayla Mason
edoras acct cssc0973
CS 310
Shawn Healey
TTh section

Lab 2
Due 2019-10-21
 */

import java.util.ArrayList;

public class SoftwareDecode {

    public SoftwareDecode(){}

    private int difference;

    public int getDifference() {
        return difference;
    }

    public void setDifference(ArrayList<Integer> pats) {
        difference = pats.get(1) - pats.get(0);
    }

    private int noise = 0;
    private boolean noiseSwitch = false;

    public int getNoise() {
        return noise;
    }

    public void setNoise() {
        noise++;
    }

    public void clearNoise() {
        noise = 0;
    }

    public boolean getNoiseSwitch() {
        return noiseSwitch;
    }

    public void setNoiseSwitch(boolean b) {
        noiseSwitch = b;
    }

    //is the pulse a valid number?
    public int noiseEval (MyQueue qVal, int val1){

        System.out.println("noiseEval: " + qVal.peek() + " Value1: " + val1);

            if ((qVal.peek() - val1) % getDifference() != 0) {
                if (getNoiseSwitch()) {
                    setNoise();
                    qVal.dequeue();
                    return noiseEval(qVal, val1);
                } else return noiseEval(qVal, qVal.dequeue());
            } else {
                setNoiseSwitch(true);
                return val1;
            }
    }

    public int decoder (MyQueue qValues, ArrayList<Integer> patterns) {

        while (!qValues.isEmpty() && getNoise() != 2) {

            final int value1 = noiseEval(qValues, qValues.dequeue());
            System.out.println(value1);

            if ((noiseEval(qValues, value1) == value1) && (qValues.peek() == value1 + getDifference())) {
                int value2 = qValues.dequeue();
                System.out.println(value1 + " " + value2);
                if ((noiseEval(qValues, value1) == value1) && (qValues.peek() != value2 + getDifference())) {
                    int value3 = qValues.dequeue();
                    System.out.println(value1 + " " + value2 + " " + value3);
                    if (noiseEval(qValues, value1) == value1) {
                        int value4 = qValues.dequeue();
                        if (noiseEval(qValues, value1) == value1) {
                            int value5 = qValues.dequeue();
                            if (noiseEval(qValues, value1) == value1) {
                                int value6 = qValues.dequeue();

                                //find the code word
                                value3 -= value1;
                                value4 -= value1;
                                value5 -= value1;
                                value6 -= value1;

                                for (int row = 2; row < patterns.size(); row+=7) {
                                    if (value3 == patterns.get(row) && value4 == patterns.get(row + 1) &&
                                            value5 == patterns.get(row + 2) && value6 == patterns.get(row + 3))
                                        return patterns.get(row + 4);
                                }

                            }
                        }
                    }
                } else if (qValues.size() >= 6)
                    return decoder(qValues, patterns); //3rd position does not fulfill 1-1-0, go to next node in queue
                else return -1; //not enough values left to evaluate
            } else if (qValues.size() >= 6)
                return decoder(qValues, patterns); //2nd position does not fulfill 1-1, go to next node in queue
            else return -1; //not enough values left to evaluate
        }

        setNoiseSwitch(false);
        clearNoise();

        if(qValues.size() >= 6)
            return decoder(qValues, patterns);
        else return -1; //not enough values left to evaluate
    }

}


