package com.backoffice.app.infraestructure.aws;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sns.model.Topic;

@Configuration
public class AwsConfig {

    public AwsConfig(
            @Value("${aws.access-key}") String awsAccessKey,
            @Value("${aws.secret-key}") String awsSecretKey) {
        this.awsAccessKey = awsAccessKey;
        this.awsSecretKey = awsSecretKey;
    }


    private final String awsAccessKey;
    private final String awsSecretKey;


    @Bean
    @Primary
    public AwsCredentialsProvider awsCredentialsProvider() {
        if (ObjectUtils.isEmpty(awsAccessKey) || ObjectUtils.isEmpty(awsSecretKey)) {
            return DefaultCredentialsProvider.create();
        }
        return () -> {
            return AwsBasicCredentials.create(
                    awsAccessKey,
                    awsSecretKey);
        };
    }

    @Bean
    public S3Client s3Client(AwsCredentialsProvider credentials) {
        return S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentials)
                .build();
    }

    @Bean(name = "voteTopic")
    public Topic voteTopic() {
        return Topic.builder()
                .topicArn("NOME DO TOPICO")
                .build();
    }
}
