package com.viadialog.camundasrv.messaging;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class MyCommandDTO extends GenericDTO {

    @NotNull
    private String executionId;

    @NotNull
    private String label;

    public MyCommandDTO() {
        super();
        this.type = "MYCOMMAND";
    }


//    @NotNull
//    private Map<String, Object> payload = new HashMap<String, Object>();

    public String getExecutionId() {
        return executionId;
    }

    public MyCommandDTO executionId(String executionId) {
        this.executionId = executionId;
        return this;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getLabel() {
        return label;
    }

    public MyCommandDTO label(String label) {
        this.label = label;
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

//    public Map<String, Object> getPayload() {
//        return this.payload;
//    }
//
//    public MyCommandDTO payload(Map<String, Object> payload) {
//        this.payload = payload;
//        return this;
//    }
//
//    public void setPayload(Map<String, Object> payload) {
//        this.payload = payload;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyCommandDTO myCommandDTO = (MyCommandDTO) o;
        if(myCommandDTO.getLabel() == null || getLabel() == null) {
            return false;
        }
        return Objects.equals(getLabel(), myCommandDTO.getLabel());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getLabel());
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
            "label=" + getLabel() +
//            ", payload='" + getPayload() + "'" +
            "}";
    }
}
