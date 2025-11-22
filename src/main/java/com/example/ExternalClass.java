package com.example;

import java.util.List;

public class ExternalClass {

    public List<String> send(List<SearchParamDTO> paramList) {

        if (paramList.get(0).getField1().equals("param1 value1")) {
            throw new IllegalArgumentException("Invalid value for field1 in first parameter");
        }

        paramList.forEach(param -> {
            System.out.println("field1: " + param.getField1() + ", field2: " + param.getField2());
        });

        return List.of("alpha", "beta", "gamma");
    }
}
