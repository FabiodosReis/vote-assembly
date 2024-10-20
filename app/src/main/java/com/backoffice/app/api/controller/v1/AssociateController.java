package com.backoffice.app.api.controller.v1;

import com.backoffice.app.api.config.doc.AssociateControllerDoc;
import com.backoffice.app.api.vo.AssociateResponseVO;
import com.backoffice.app.api.vo.CustomPage;
import com.backoffice.app.api.vo.CustomPageable;
import com.backoffice.app.api.facade.AssociateUseCaseFacade;
import com.backoffice.app.api.vo.AssociateCreateRequestVO;
import com.backoffice.app.api.vo.AssociateUpdateRequestVO;
import com.backoffice.core.associate.v1.exception.AssociateException;
import com.backoffice.core.associate.v1.vo.AssociateFilterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/associates")
@RequiredArgsConstructor
public class AssociateController implements AssociateControllerDoc {


    private final AssociateUseCaseFacade facade;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public AssociateResponseVO findById(@PathVariable("id") String id) {
        var associate = facade.findByIdAssociateUseCase.execute(id);
        return associate.map(AssociateResponseVO::new)
                .orElseThrow(() -> new AssociateException("Associate not found"));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomPage<?> findAll(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "cpf", required = false) String cpf
    ) {
        var associateList = facade.findAllAssociateUseCase.execute(
                AssociateFilterVO.builder()
                        .size(size)
                        .page(page)
                        .cpf(cpf)
                        .build()
        );

        var customPageable = new CustomPageable(
                page,
                size,
                associateList.size()
        );

        return new CustomPage<>(associateList, customPageable);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody AssociateCreateRequestVO vo) {
        facade.createAssociateUseCase.execute(
                vo.toEntity()
        );
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @RequestBody AssociateUpdateRequestVO vo,
            @PathVariable("id") String id
    ) {

        vo.setId(id);
        facade.updateAssociateUseCase.execute(
                vo.toEntity()
        );
    }
}
