package org.tustcs.eztable.enums;

import lombok.Getter;

@Getter
public enum  ResultEnums {

    SUCCESS(0,"成功"),
    ERROR(1,"系统异常"),
    USER_TOOSHORT(101,"用户名过短"),
    USER_NOTFOUND(102,"用户未找到"),
    USER_EXISTED(103,"用户已存在"),
    FILE_NOTFOUND(201,"文件未找到"),
    FILE_NOTINLOC(202,"文件未在本地"),
    TOKEN_NOTSEND(301,"未传Token"),
    ;

    ResultEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;


}
