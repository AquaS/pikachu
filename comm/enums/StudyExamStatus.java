package com.oee.pikachu.comm.enums;

/**
 * Created by Aqua on 2018/11/28.
 */
public enum StudyExamStatus {
    ANSWERED("answered", "已完成"),
    NOT_ANSWER("not_answer", "未答题");

    private String status;

    private String statusDesc;

    StudyExamStatus(String status, String statusDesc) {
        this.status = status;
        this.statusDesc = statusDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
