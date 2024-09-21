package com.backoffice.app.application.client;

import com.backoffice.app.application.exception.SnsException;
import com.backoffice.app.application.service.client.vo.SnsEventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.Topic;

import java.time.LocalDateTime;

import static com.backoffice.app.application.constants.ApplicationConstants.SYSTEM_NAME;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AwsSnsClient {

    private final Topic voteSessionTopic;
    private final ObjectMapper mapper;
    private final SnsClient snsClient;

    public void publish(Object event, String action, String entity) {
        try {
            log.info("Init publish message aws: {} ", event);

            var dto = SnsEventDto.builder()
                    .action(action)
                    .createDate(LocalDateTime.now().toString())
                    .system(SYSTEM_NAME)
                    .entity(entity)
                    .event(event)
                    .build();

            String dtoEvent = mapper.writeValueAsString(dto);

            snsClient.publish(PublishRequest
                    .builder()
                    .topicArn(voteSessionTopic.topicArn())
                    .message(dtoEvent)
                    .build()
            );

            log.info("Message aws sent successfully");

        } catch (Exception e) {
            throw new SnsException(e.getMessage(), e);
        }
    }
}
