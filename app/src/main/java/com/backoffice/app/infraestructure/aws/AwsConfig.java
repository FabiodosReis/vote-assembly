package com.backoffice.app.infraestructure.aws;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.Topic;

import java.util.Arrays;


@Configuration
public class AwsConfig {

    @Value("${aws.sns.topic.session-vote}")
    private String topicSessionVote;

    @Autowired
    private Environment environment;

    //when execute out production need pass user key and user secret to run project
    @Bean @Primary
    public AwsCredentialsProvider awsCredentialsProvider() {
        if (Arrays.asList(environment.getActiveProfiles()).contains("production")) {
            return DefaultCredentialsProvider.create();
        }
        return () -> {
            return AwsBasicCredentials
                    .create(
                    environment.getProperty("ACCESS_KEY"),
                    environment.getProperty("SECRET_KEY")
            );
        };
    }

    @Bean
    public S3Client s3Client(AwsCredentialsProvider credentials) {
        return S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentials)
                .build();
    }


    @Bean
    public SnsClient snsClient(AwsCredentialsProvider credentials) {
       return SnsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentials)
                .build();
    }

    @Bean(name = "topicSessionVote")
    public Topic topicSessionVote() {
        return Topic.builder()
                .topicArn(topicSessionVote)
                .build();
    }
}
