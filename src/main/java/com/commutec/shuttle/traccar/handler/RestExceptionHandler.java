package com.commutec.shuttle.traccar.handler;

import com.commutec.shuttle.traccar.exception.InvalidTenantException;
import com.commutec.shuttle.traccar.exception.TenantNotFoundException;
import com.commutec.shuttle.traccar.model.pojo.ErrorModel;
import com.commutec.shuttle.traccar.model.pojo.ResponseModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * RestExceptionHandler
 */
@RestControllerAdvice
public class RestExceptionHandler {

    @RequestMapping(produces = "application/json")
    @ExceptionHandler(value = { TenantNotFoundException.class })
    public ResponseEntity<Object> handleTenantNotFoundException(TenantNotFoundException ex) {
        try {
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("TENANT_NOT_FND");
            errorModel.setMessage(ex.getMessage());
            ResponseModel responseModel = new ResponseModel();
            responseModel.setSuccess(false);
            responseModel.setMessage("X-TenantID not present in the Request Header");
            responseModel.setError(errorModel);
            return buildResponseEntity(responseModel, HttpStatus.BAD_REQUEST);
        } catch (Exception execption) {
            throw execption;
        }
    }

    @RequestMapping(produces = "application/json")
    @ExceptionHandler(value = { InvalidTenantException.class })
    public ResponseEntity<Object> handleInvalidTenantException(InvalidTenantException ex) {
        try {
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("TENANT_INV_EXP");
            errorModel.setMessage(ex.getMessage());
            ResponseModel responseModel = new ResponseModel();
            responseModel.setSuccess(false);
            responseModel.setMessage("No DB found with given X-TenantID");
            responseModel.setError(errorModel);
            return buildResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        } catch (Exception execption) {
            throw execption;
        }
    }

    private ResponseEntity<Object> buildResponseEntity(ResponseModel responseModel, HttpStatus httpStatusCode) {
        return new ResponseEntity<>(responseModel, httpStatusCode);
    }
}