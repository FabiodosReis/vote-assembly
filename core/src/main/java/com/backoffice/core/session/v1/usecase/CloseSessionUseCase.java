package com.backoffice.core.session.v1.usecase;

import com.backoffice.core.associate.exception.AssociateException;
import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.exception.SessionException;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Named
@RequiredArgsConstructor
public class CloseSessionUseCase {

    private final SessionDataProvider dataProvider;

    public void execute(String id) throws SessionException {

        var optionalSession = dataProvider.findById(id)
                .orElseThrow(() -> new AssociateException(String.format("Session %s not found", id)));

        optionalSession.setEndDate(LocalDateTime.now(ZoneId.of("UTC")));

        dataProvider.closeSession(optionalSession);

    }
}
