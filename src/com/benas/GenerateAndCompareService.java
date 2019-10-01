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

        List<String[]> binaryLists = stringListsToBinaryLists(listA, listB);

        return compareBinaryLists(binaryLists);
    }

    private static long[] generateList(long pradinisSkaicius, long koficientas) {
        long liekana;
        long[] rezultatas = new long[ITERACIJU_KIEKIS];

        liekana = pradinisSkaicius;
        for (int i = 0; i < ITERACIJU_KIEKIS; i++) {
            liekana = (liekana * koficientas) % GenerateAndCompareService.DALYBA;
            rezultatas[i] = liekana;
        }
        return rezultatas;
    }

    private static List<String[]> stringListsToBinaryLists(long[]... list) {
        List<String[]> result = new ArrayList<>();

        for (long[] aList : list) {
            String[] binaryList = new String[aList.length];
            String binaryString;
            for (int j = 0; j < aList.length; j++) {
                binaryString = Integer.toBinaryString(Math.toIntExact(aList[j]));
                StringBuilder resultStringLength8 = new StringBuilder(binaryString);

                while (resultStringLength8.length() < 8) {
                    resultStringLength8.insert(0, "0");
                }
                binaryList[j] = resultStringLength8.toString();
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
