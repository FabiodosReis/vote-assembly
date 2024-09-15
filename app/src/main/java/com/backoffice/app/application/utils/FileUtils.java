package com.backoffice.app.application.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {

    public static String getSql(String fileName){
        try{
            File file = ResourceUtils.getFile("classpath:sql/".concat(fileName));
            return new String(Files.readAllBytes(file.toPath()));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
