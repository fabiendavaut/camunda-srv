package com.viadialog.camundasrv.messaging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = MyCommandDTO.class, name = "MYCOMMAND"),
    @JsonSubTypes.Type(value = MyReceiptDTO.class, name = "MYRECEIPT")
})
public class GenericDTO implements Serializable {

    @JsonIgnore
    protected String type;

    public String getType() {
        return type;
    }

    public GenericDTO type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

}
