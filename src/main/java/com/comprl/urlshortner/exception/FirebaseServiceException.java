package com.comprl.urlshortner.exception;


public class FirebaseServiceException extends RuntimeException{

    public FirebaseServiceException(String message){
        super(message);
    }
}
