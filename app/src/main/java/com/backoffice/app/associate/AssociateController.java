package com.backoffice.app.associate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/associates")
@RequiredArgsConstructor
public class AssociateController {

    private final AssociateUseCaseMediator mediator;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public AssociateResponseVO findById(@PathVariable("id") String id) {
        var associate = mediator.findByIdAssociateUseCase.findById(id);
        return new AssociateResponseVO(associate.get());
    }
}
