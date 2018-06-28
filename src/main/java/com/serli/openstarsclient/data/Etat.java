package com.serli.openstarsclient.data;

import com.fasterxml.jackson.annotation.JsonValue;


public enum Etat {
    TRAITEE(1),PASTRAITEE(0), ACCEPTEE(2);
    private Integer code;

    @JsonValue
    public Integer getCode() {
        return code;
    }

    private Etat(Integer code)
    {
        this.code = code;
    }

    public static Etat fromCode(Integer code){
        if (code == 1)
            return TRAITEE;
        else if (code == 2)
            return ACCEPTEE;
        else
            return PASTRAITEE;
    }
}
