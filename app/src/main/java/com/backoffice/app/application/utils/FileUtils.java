package com.backoffice.app.application.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.backoffice.app.application.constants.ApplicationConstants.FILE_SEPARATOR;

public class FileUtils {

    public String getSql2(String fileName){

        try{
            var classLoader = getClass().getClassLoader();
            var file = new File(classLoader.getResource("sql".concat(FILE_SEPARATOR).concat(fileName)).getFile());
            return new String(Files.readAllBytes(file.toPath()));
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String getSql(String fileName){
        try{
           var file = ResourceUtils.getFile("sql".concat(FILE_SEPARATOR).concat(fileName));
           return new String(Files.readAllBytes(file.toPath()));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
