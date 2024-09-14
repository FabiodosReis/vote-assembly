package com.backoffice.app.session;

import com.backoffice.app.associate.FileServiceCsv;
import com.backoffice.app.client.AwsS3Client;
import com.backoffice.app.vo.ObjectResponseVO;
import com.backoffice.core.session.v1.usecase.FindAllSessionUseCase;
import com.backoffice.core.session.vo.SessionFilterVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.backoffice.app.constants.ApiConstants.FILE_NAME_VOTE;

@Service
@RequiredArgsConstructor
public class SessionFileService {

    private final FileServiceCsv fileServiceCsv;
    private final FindAllSessionUseCase findAllSessionUseCase;
    private final AwsS3Client awsS3Client;

    public ObjectResponseVO generateFile(SessionFilterVO filterVO) {
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
