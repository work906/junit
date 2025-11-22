package com.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
class SandboxTest {

    @Mock
    ExternalClass externalClass;

    @InjectMocks
    PublicClass service;

    @Test
    void test_argument_passed_to_externalSystem() {

        // モックの戻りを定義
        ArgumentCaptor<List<SearchParamDTO>> captor = ArgumentCaptor.forClass(List.class);
        doReturn(List.of("return1", "return2", "return3")).when(externalClass).send(captor.capture());

        //実行
        service.publicFunc();

//        // モックのメソッドの引数をキャプチャ
//        @SuppressWarnings("unchecked")
//        ArgumentCaptor<List<SearchParamDTO>> captor = ArgumentCaptor.forClass(List.class);
//        verify(externalClass).send(captor.capture());

        // キャプチャから引数を取得
        List<SearchParamDTO> captured = captor.getValue();

        // 検証データ作成
        SearchParamDTO param1 = new SearchParamDTO();
        param1.setField1("param1 value1");
        param1.setField2("param1 value2");

        SearchParamDTO param2 = new SearchParamDTO();
        param2.setField1("param2 value1");
        param2.setField2("param2 value2");

        List<SearchParamDTO> expected = List.of(param1, param2);

        // 検証実施
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getField1(), captured.get(i).getField1());
            assertEquals(expected.get(i).getField2(), captured.get(i).getField2());
        }
    }

    @Test
    void test_argument_passed_to_externalSystem2() {

        //実行
        new PublicClass().publicFunc();

        SearchParamDTO param1 = new SearchParamDTO();
        param1.setField1("param1 value1");
        param1.setField2("param1 value2");

        SearchParamDTO param2 = new SearchParamDTO();
        param2.setField1("param2 value1");
        param2.setField2("param2 value2");

        List<SearchParamDTO> expected = List.of(param1, param2);

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getField1(), "param1");
            assertEquals(expected.get(i).getField2(), "param2");
        }
    }

}
