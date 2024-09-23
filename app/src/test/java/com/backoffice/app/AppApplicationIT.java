package com.backoffice.app;

import com.backoffice.app.annotation.TestRepositoryCommonAnnotation;
import com.backoffice.app.config.DataSourceTestConfig;
import com.backoffice.app.config.DatabaseContainerConfig;
import com.backoffice.app.port.associate.AssociateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

@TestRepositoryCommonAnnotation
@ContextConfiguration(
		classes = {DataSourceTestConfig.class, AssociateRepository.class},
		initializers = {DatabaseContainerConfig.class}
)
class AppApplicationIT {

	@Test
	void contextLoads() {
	}

}
