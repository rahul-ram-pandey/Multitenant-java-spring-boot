package com.commutec.shuttle.traccar.model.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * ResponseModel
 */
@Setter
@Getter
public class ResponseModel {
    private Boolean success;
    private String message;
    private Object data;
    private ErrorModel error;
}