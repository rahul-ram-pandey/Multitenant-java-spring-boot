package com.commutec.shuttle.traccar.model.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * ErrorModel
 */
@Setter
@Getter
public class ErrorModel {

    private String code;
    private String message;
    private String stack;
    private String developerMessage;
    private List<Object> exceptionObjectList;
}