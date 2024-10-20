package com.backoffice.app;

import com.backoffice.app.annotation.TestRepositoryCommonAnnotation;
import com.backoffice.app.application.repository.associate.AssociateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestRepositoryCommonAnnotation
@SpringBootTest
class AppApplicationIT {

    @Autowired
    private AssociateRepository repository;

    @Test
    void contextLoads() {
        var associate =
                repository.findById("cde53c5d-7a03-426d-8538-c3b2ffc338aa");

        assertTrue(associate.isPresent());
    }
}
