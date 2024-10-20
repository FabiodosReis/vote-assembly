package com.backoffice.app.deprecated;

import org.springframework.stereotype.Component;

import java.util.List;

import static com.backoffice.app.deprecated.EnumTest.MODEL_1;
import static com.backoffice.app.deprecated.EnumTest.MODEL_2;

@Deprecated
@Component
public class Client {

    public void exec() {
        var enumList = List.of(MODEL_1, MODEL_2);
        for (EnumTest en : EnumTest.values()) {
            en.service().process(null);
        }
    }
}
