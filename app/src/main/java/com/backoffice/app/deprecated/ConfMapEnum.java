package com.backoffice.app.deprecated;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Deprecated
@Configuration
@RequiredArgsConstructor
public class ConfMapEnum {

    private final Process1 process1;
    private final Process2 process2;

    @Bean
    public Map<EnumTest, Service> getParam() {
        Map<EnumTest, Service> map = new HashMap<>();
        map.put(EnumTest.MODEL_1, process1);
        map.put(EnumTest.MODEL_2, process2);
        return map;
    }

}
