package com.maybe.pojo.enumeration;

/**
 * Maybe has infinite possibilities
 *
 * @author Created by sugar on 2018/7/8
 */
public enum TaskStatus {
    /**
     * 任务类型
     */
    INIT(1, "初始录入"),
    DELING(2, "处理中"),
    OVERED(3, "已结束"),;

    int status;
    String description;

    TaskStatus(int status, String description) {
        this.description = description;
        this.status = status;
    }


    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

}
