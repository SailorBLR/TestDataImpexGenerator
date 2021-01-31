package com.epam.generator.utils;

import com.epam.generator.model.SolutionEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.CATALOG_VARIABLE;

public class OrderEntriesGenUtil {
    private static final List<String> cloudProducts =
            new LinkedList<>(Arrays.asList("000000000008005337", "000000000008005338", "000000000008005339", "000000000008005950", "000000000008005340"));
    private static final List<String> premProducts =
            new LinkedList<>(Arrays.asList("000000000007018123", "000000000007011027", "000000000007012048", "000000000007002299", "000000000007015905"));
    private static final List<String> bydProducts =
            new LinkedList<>(Arrays.asList("000000000008005570", "000000000008000047", "000000000008000048", "000000000008002711", "000000000008000044"));
    private static Map<SolutionEnum, List<String>> productsMap = new HashMap<>();

    static {
        productsMap.put(SolutionEnum.CLOUD_SAP, cloudProducts);
        productsMap.put(SolutionEnum.CLOUD_PARTNER, cloudProducts);
        productsMap.put(SolutionEnum.PREM, premProducts);
        productsMap.put(SolutionEnum.BYD_CLOUD, bydProducts);
    }

    public static String getProductByNumberAndSolution(final String solution, final int number) {
        return productsMap.get(SolutionEnum.valueOf(solution)).get(number)+ CATALOG_VARIABLE;
    }

}
