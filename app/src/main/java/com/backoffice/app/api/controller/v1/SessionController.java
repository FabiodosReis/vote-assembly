package com.backoffice.app.api.controller.v1;

import com.backoffice.app.api.config.doc.SessionControllerDoc;
import com.backoffice.app.application.service.SessionFileService;
import com.backoffice.app.api.vo.CustomPage;
import com.backoffice.app.api.vo.CustomPageable;
import com.backoffice.app.api.vo.RequestSessionVO;
import com.backoffice.app.api.facade.SessionUseCaseFacade;
import com.backoffice.core.session.v1.vo.SessionFilterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.backoffice.app.application.constants.ApplicationConstants.MEDIA_TYPE_CSV;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
public class SessionController implements SessionControllerDoc {

    private final SessionUseCaseFacade facade;
    private final SessionFileService fileService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSession(@RequestBody RequestSessionVO vo) {
        facade.createSessionUseCase.execute(vo.toEntity());
    }

    @PutMapping(value = "close/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void closeSession(@PathVariable("id") String id) {
        facade.closeSessionUseCase.execute(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomPage<?> findAll(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page
    ) {
        var sessionList = facade.findAllSessionUseCase.execute(
                SessionFilterVO.builder()
                        .page(page)
                        .size(size)
                        .build()
        );

        var customPageable = new CustomPageable(
                page,
                size,
                sessionList.size()
        );

        return new CustomPage<>(sessionList, customPageable);
    }

    @GetMapping(produces = MEDIA_TYPE_CSV)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllCSV(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page
    ) {

        var filterVO = SessionFilterVO.builder()
                .page(page)
                .size(size)
                .build();

        var response = fileService.generateFile(filterVO);

        return ResponseEntity.ok()
                .header(CONTENT_DISPOSITION, "attachment; filename=" + response.getFileName())
                .contentLength(response.getContent().length)
                .contentType(MediaType.parseMediaType(MEDIA_TYPE_CSV))
                .body(response.getContent());
    }
}
