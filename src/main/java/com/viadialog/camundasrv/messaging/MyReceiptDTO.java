package com.viadialog.camundasrv.messaging;


import java.io.Serializable;

public class MyReceiptDTO extends GenericDTO {

    private String executionId;

    public String getExecutionId() {
        return executionId;
    }

    public MyReceiptDTO() {
        super();
        this.type = "MYRECEIPT";
    }

    MyReceiptDTO executionId(String executionId) {
        this.executionId = executionId;
        return this;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    @Override
    public String toString() {
        return "MyReceiptDTO{" +
            "executionId='" + getExecutionId() + "'" +
            "}";
    }
}
