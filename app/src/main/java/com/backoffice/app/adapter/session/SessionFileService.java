package com.backoffice.app.adapter.session;

import com.backoffice.app.application.service.FileServiceCsv;
import com.backoffice.app.application.service.client.AwsS3Client;
import com.backoffice.app.application.api.vo.FileResponseVO;
import com.backoffice.core.session.v1.usecase.FindAllSessionUseCase;
import com.backoffice.core.session.vo.SessionFilterVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.backoffice.app.application.constants.ApplicationConstants.FILE_NAME_VOTE;

@Service
@RequiredArgsConstructor
public class SessionFileService {

    private final FileServiceCsv fileServiceCsv;
    private final FindAllSessionUseCase findAllSessionUseCase;
    private final AwsS3Client awsS3Client;

    public FileResponseVO generateFile(SessionFilterVO filterVO) {
        var fileName = FILE_NAME_VOTE
                .concat(LocalDate.now().toString())
                .concat(".csv");

        String[] fileHeader = {
                "quantity",
                "session",
                "subject"
        };

        var file = fileServiceCsv.generateFile(
                fileName,
                fileHeader,
                findAllSessionUseCase.findAllCsvFormat(filterVO)
        );

        awsS3Client.uploadFile(file, file.getName());
        FileUtils.deleteQuietly(file);
        return awsS3Client.downloadFile(file.getName());
    }
}
