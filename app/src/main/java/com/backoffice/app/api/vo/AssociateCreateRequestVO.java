package com.backoffice.app.api.vo;

import com.backoffice.core.associate.v1.model.Associate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AssociateCreateRequestVO {

    @Schema(example = "164.575.845-19")
    private String cpf;

    @Schema(example = "Robbert")
    private String name;


  public Associate toEntity(){
      return new Associate(
              null,
              name,
              cpf
      );
  }

}
