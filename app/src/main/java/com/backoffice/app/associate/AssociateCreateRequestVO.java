package com.backoffice.app.associate;

import com.backoffice.core.associate.model.Associate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AssociateCreateRequestVO {

    private String cpf;
    private String name;


  public Associate toEntity(){
      return new Associate(
              null,
              name,
              cpf
      );
  }

}
