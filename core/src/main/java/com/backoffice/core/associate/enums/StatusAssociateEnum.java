package com.backoffice.core.associate.enums;

import com.backoffice.core.associate.exception.AssociateException;

public enum StatusAssociateEnum {

    ABLE_TO_VOTE,
    UNABLE_TO_VOTE;

    public static StatusAssociateEnum get(String description) {
        for (StatusAssociateEnum status : StatusAssociateEnum.values()) {
            if (status.name().equals(description)) return status;
        }
        throw new AssociateException("Associate status not found");
    }

}
