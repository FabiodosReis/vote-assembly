package com.backoffice.app.subject;

import com.backoffice.app.subject.vo.SubjectRequestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectMediator mediator;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SubjectRequestVO vo) {
        mediator.createSubjectUseCase.execute(vo.toEntity());
    }

    @PutMapping("/{id}/disable")
    @ResponseStatus(HttpStatus.OK)
    public void disable(@PathVariable("id") String id) {
        mediator.disableSubjectUseCase.execute(id);
    }
}
