package com.backoffice.app.application.client;

import com.backoffice.app.application.api.v1.vo.FileResponseVO;
import com.backoffice.app.application.exception.FileGenerateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AwsS3Client {

    private final S3Client s3Client;

    @Value("${aws.s3.session}")
    private String bucketName;

    public void uploadFile(File file, String fileName) {
        s3Client.putObject(
                PutObjectRequest.builder().bucket(bucketName).key(fileName).build(),
                RequestBody.fromFile(file)
        );
        log.info("[AwsS3Client] upload {} successfully", fileName);
    }

    public FileResponseVO downloadFile(String fileName) {
        try{

            var getObject = s3Client.getObject(
                    GetObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .build()
            );

            return FileResponseVO.builder()
                    .content(getObject.readAllBytes())
                    .fileName(fileName)
                    .contentType(getObject.response().contentType())
                    .build();

        }catch (IOException e){
            throw new FileGenerateException(e.getMessage(), e);
        }
    }
}
