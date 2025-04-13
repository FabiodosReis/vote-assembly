package com.backoffice.app.deprecated;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Deprecated
public enum EnumTest {

    MODEL_1 {
        @Override
        public Service service() {
            return currentService;
        }
    },
    MODEL_2 {
        @Override
        public Service service() {
            return currentService;
        }
    };

    public abstract Service service();

    private static Service currentService;

    @Component
    @RequiredArgsConstructor
    public static class ServiceProvider {
        private final ConfMapEnum map;

        @PostConstruct
        public void postConstructor() {
            for (EnumTest et : EnumTest.values()) {
                currentService = map.getParam().get(et);
            }
        }
    }
}
