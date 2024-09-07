package com.backoffice.app.session;

import com.backoffice.app.session.vo.RequestSessionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
public class SessionController {


    private final SessionMediator mediator;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSession(@RequestBody RequestSessionVO vo) {
        mediator.createSessionUseCase.execute(vo.toEntity());

    }

    @PutMapping("close/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void closeSession(@PathVariable("id") String id) {
        mediator.closeSessionUseCase.execute(id);
    }

}
