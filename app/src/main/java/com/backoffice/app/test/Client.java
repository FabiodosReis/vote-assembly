package com.backoffice.app.test;

import org.springframework.stereotype.Component;

import java.util.List;

import static com.backoffice.app.test.EnumTest.MODEL_1;
import static com.backoffice.app.test.EnumTest.MODEL_2;

@Component
public class Client {

    public void exec() {
        var enumList = List.of(MODEL_1, MODEL_2);
        for (EnumTest en : EnumTest.values()) {
            en.service().process(null);
        }
    }
}
