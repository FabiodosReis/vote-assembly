package com.backoffice.app.application.service;

import com.backoffice.core.exception.FileGenerateException;
import com.opencsv.CSVWriter;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.backoffice.app.application.constants.ApplicationConstants.SEMICOLON;

@Service
public class FileServiceCsv {

    public File generateFile(String fileName, String[] fileHeader, List<String[]> content) {
        File file = new File(fileName);

        try (var writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
             var csvWriter = getWriter(writer);
        ) {
            csvWriter.writeNext(fileHeader);
            csvWriter.writeAll(content);
        } catch (Exception e) {
            FileUtils.deleteQuietly(file);
            throw new FileGenerateException(e.getMessage(), e);
        }
        return file;
    }

    private CSVWriter getWriter(Writer writer) {
        return new CSVWriter(
                writer,
                SEMICOLON,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END
        );
    }
}
