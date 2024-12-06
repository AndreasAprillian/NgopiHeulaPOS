package com.example.NgopiHeula.utility;

import com.example.NgopiHeula.model.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponsesHelper {

    public ResponseEntity<Object> statusOk(){
        GenericResponse<Object> genericResponse = GenericResponse.builder().
                status(HttpStatus.OK.name()).
                code(HttpStatus.OK.value()).
                message(HttpStatus.OK.getReasonPhrase()).
                build();
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }
    public ResponseEntity<Object> statusOk(Object object){
        GenericResponse<Object> genericResponse = GenericResponse.builder().
                status(HttpStatus.OK.name()).
                code(HttpStatus.OK.value()).
                message(HttpStatus.OK.getReasonPhrase()).
                data(object).
                build();
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }
    public ResponseEntity<Object> statusCreated(Object object){
        GenericResponse<Object> genericResponse = GenericResponse.builder().
                status(HttpStatus.CREATED.name()).
                code(HttpStatus.CREATED.value()).
                message(HttpStatus.CREATED.getReasonPhrase()).
                data(object).
                build();
        return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
    }
    public ResponseEntity<Object> statusUnprosesable(Object object){
        GenericResponse<Object> genericResponse = GenericResponse.builder().
                status(HttpStatus.UNPROCESSABLE_ENTITY.name()).
                code(HttpStatus.UNPROCESSABLE_ENTITY.value()).
                message(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase()).
                data(object).
                build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(genericResponse);
    }
    public ResponseEntity<Object> statusAccepted(Object object){
        GenericResponse<Object> genericResponse = GenericResponse.builder().
                status(HttpStatus.ACCEPTED.name()).
                code(HttpStatus.ACCEPTED.value()).
                message(HttpStatus.ACCEPTED.getReasonPhrase()).
                data(object).
                build();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genericResponse);
    }
    public ResponseEntity<Object> statusBadRequest(Object object){
        GenericResponse<Object> genericResponse = GenericResponse.builder().
                status(HttpStatus.BAD_REQUEST.name()).
                code(HttpStatus.BAD_REQUEST.value()).
                message(HttpStatus.BAD_REQUEST.getReasonPhrase()).
                data(object).
                build();
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }
    public ResponseEntity<Object> statusNotFound(){
        GenericResponse<Object> genericResponse = GenericResponse.builder().
                status(HttpStatus.NOT_FOUND.name()).
                code(HttpStatus.NOT_FOUND.value()).
                message(HttpStatus.NOT_FOUND.getReasonPhrase()).
                build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(genericResponse);
    }
    public ResponseEntity<Object> statusNotFound(Object object){
        GenericResponse<Object> genericResponse = GenericResponse.builder().
                status(HttpStatus.NOT_FOUND.name()).
                code(HttpStatus.NOT_FOUND.value()).
                message(HttpStatus.NOT_FOUND.getReasonPhrase()).
                data(object).
                build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(genericResponse);
    }
    public ResponseEntity<Object> statusInternalError(String Message){
        GenericResponse<Object> genericResponse = GenericResponse.builder().
                status(HttpStatus.INTERNAL_SERVER_ERROR.name()).
                code(HttpStatus.INTERNAL_SERVER_ERROR.value()).
                message(Message).
                build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResponse);
    }
}
