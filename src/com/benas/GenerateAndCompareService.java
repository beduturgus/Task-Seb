package com.benas;

import java.util.ArrayList;
import java.util.List;

class GenerateAndCompareService {

    private static final int ITERACIJU_KIEKIS = 1000000;
    private static final long KOEFICIENTAS_A = 16807;
    private static final long KOEFICIENTAS_B = 48271;
    private static final long DALYBA = 2147483647;

    static int generateAndCompare(String[] pradiniaiSkaiciai) {

        long[] listA = generateList(Long.parseLong(pradiniaiSkaiciai[0]), KOEFICIENTAS_A);
        long[] listB = generateList(Long.parseLong(pradiniaiSkaiciai[1]), KOEFICIENTAS_B);

        List<String[]> binaryLists = longListsToBinaryLists(listA, listB);

        return compareBinaryLists(binaryLists);
    }

    private static long[] generateList(long pradinisSkaicius, long koficientas) {
        long remainder;
        long[] result = new long[ITERACIJU_KIEKIS];

        remainder = pradinisSkaicius;
        for (int i = 0; i < ITERACIJU_KIEKIS; i++) {
            remainder = (remainder * koficientas) % GenerateAndCompareService.DALYBA;
            result[i] = remainder;
        }
        return result;
    }

    private static List<String[]> longListsToBinaryLists(long[]... longList) {
        List<String[]> result = new ArrayList<>();

        for (long[] list : longList) {
            String[] binaryList = new String[list.length];
            StringBuilder binaryString;
            for (int j = 0; j < list.length; j++) {
                binaryString = new StringBuilder(Integer.toBinaryString(Math.toIntExact(list[j])));

                while (binaryString.length() < 8) {
                    binaryString.insert(0, "0");
                }
                binaryList[j] = binaryString.toString();
            }
            result.add(binaryList);
        }
        return result;
    }

    private static int compareBinaryLists(List<String[]> binaryLists) {
        String[] listA = binaryLists.get(0);
        String[] listB = binaryLists.get(1);
        int result = 0;

        for (int i = 0; i < listA.length; i++) {
            String binaryA = listA[i].substring(listA[i].length() - 8, listA[i].length());
            String binaryB = listB[i].substring(listB[i].length() - 8, listB[i].length());
            if (binaryA.equals(binaryB)) {
                result++;
            }
        }
        return result;
    }
}
