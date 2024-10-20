package com.backoffice.app.application.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;

import static com.backoffice.app.application.constants.ApplicationConstants.FILE_SEPARATOR;

public class FileUtils {

    public static String getSql(String fileName) {
        try {

            var file = new ClassPathResource("sql".concat(FILE_SEPARATOR).concat(fileName)).getFile();
            //var file = ResourceUtils.getFile("classpath:sql".concat(FILE_SEPARATOR).concat(fileName));
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
