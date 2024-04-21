package com.study.locadora.exception;

public class CarroNaoExisteException extends RuntimeException {
    public CarroNaoExisteException(final String msg) {
        super(msg);
    }
}
