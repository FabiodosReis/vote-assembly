package com.backoffice.app.application.utils;

import com.backoffice.app.AppApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.backoffice.app.application.constants.ApplicationConstants.FILE_SEPARATOR;

public class FileUtils {

    public static String getSql(String fileName) {

        ClassPathResource resource = new ClassPathResource(FILE_SEPARATOR.concat("sql")
                .concat(FILE_SEPARATOR).concat(fileName), AppApplication.class);

        try (InputStream inputStream = resource.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
