package net.darkhax.elysian.util;

import java.util.ArrayList;

public class DataUtils {

    /**
     * Method to turn an Integer[] to an int[]
     * @param integerArray: the Integer[] being converted.
     * @return int[]: the results of the outcome.
     */
    public static int[] integerToInt(Integer[] integerArray) {

        int[] intArray = new int[integerArray.length];

        for (int i = 0; i < integerArray.length; i++) {

            if (integerArray[i] != null) {

                intArray[i] = integerArray[i].intValue();
            }

            else
                intArray[i] = -1;
        }

        return intArray;
    }

    /**
     * Metod to turn an Int[] into an Ineger[]
     *
     * @param intArray
     *            : The int[] being converted.
     * @return Integer[]: the results of the conversion.
     */
    public static Integer[] intToInteger(int[] intArray) {

        Integer[] integerArray = new Integer[intArray.length];

        for (int i = 0; i < intArray.length; i++) {

            integerArray[i] = Integer.valueOf(intArray[i]);
        }

        return integerArray;
    }

    public static ArrayList<Integer> intToArrayList(Integer[] list) {

        ArrayList<Integer> arrayList = new ArrayList();

        for (Integer element : list) {

            arrayList.add(element);
        }

        return arrayList;
    }
}