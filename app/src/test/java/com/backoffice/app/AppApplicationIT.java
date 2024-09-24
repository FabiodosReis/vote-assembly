package com.backoffice.app;

import com.backoffice.app.annotation.TestRepositoryCommonAnnotation;
import com.backoffice.app.application.api.v1.controller.VoteController;
import com.backoffice.app.application.api.v1.facade.VoteUseCaseFacade;
import com.backoffice.app.application.client.AwsSnsClient;
import com.backoffice.app.config.DataSourceTestConfig;
import com.backoffice.app.config.DatabaseContainerConfig;
import com.backoffice.app.config.LiquibaseTestConfig;
import com.backoffice.app.infraestructure.aws.AwsConfig;
import com.backoffice.app.infraestructure.config.ObjectMapperConfig;
import com.backoffice.app.infraestructure.handler.ExceptionHandlerConfig;
import com.backoffice.app.port.associate.AssociateRepository;
import com.backoffice.app.port.subject.SubjectRepository;
import com.backoffice.app.port.vote.VoteProcess;
import com.backoffice.app.port.vote.VoteRepository;
import com.backoffice.core.vote.v1.usecase.CreateVoteUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;


@SpringBootTest
@TestRepositoryCommonAnnotation
@ContextConfiguration(
        classes = {
                DataSourceTestConfig.class, LiquibaseTestConfig.class, ObjectMapperConfig.class,
                VoteController.class, ExceptionHandlerConfig.class, SubjectRepository.class,
                AssociateRepository.class, CreateVoteUseCase.class, VoteUseCaseFacade.class,
                VoteProcess.class, VoteRepository.class, AwsSnsClient.class,
                AwsConfig.class
        },
        initializers = {DatabaseContainerConfig.class}
)
class AppApplicationIT {

    @Autowired
    private AssociateRepository repository;

    @MockBean
    private AwsSnsClient awsSnsClient;

    @Test
    void contextLoads() {

        var associate =
                repository.findById("cde53c5d-7a03-426d-8538-c3b2ffc338aa");

        assertTrue(associate.isPresent());

    }

}
