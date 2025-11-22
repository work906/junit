package com.example;

import java.util.List;

public class PublicClass {

    private ExternalClass external = new ExternalClass();

    public void publicFunc() {

        SearchParamDTO param1 = new SearchParamDTO();
        param1.setField1("param1 value1");
        param1.setField2("param1 value2");

        SearchParamDTO param2 = new SearchParamDTO();
        param2.setField1("param2 value1");
        param2.setField2("param2 value2");

        List<SearchParamDTO> paramList = List.of(param1, param2);

        // 呼び出し
        List<String> resultList = privateFunc(paramList);

        // 結果表示
        System.out.println("----- result -----");
        resultList.forEach(System.out::println);
    }

    private List<String> privateFunc(List<SearchParamDTO> paramList) {
        List<String> list = external.send(paramList);
        return list;
    }

}
