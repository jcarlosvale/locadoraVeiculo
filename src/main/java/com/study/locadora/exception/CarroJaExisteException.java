package com.study.locadora.exception;

public class CarroJaExisteException extends RuntimeException{
    public CarroJaExisteException(final String msg) {
        super(msg);
    }
}
