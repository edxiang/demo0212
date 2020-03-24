package org.kevin.demo0212.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * @author Kevin.Z
 * @version 2020-03-19
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IOException.class)
    public String ioException(){
        System.out.println("in IOException");
        return "error";
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String indexOutOfBoundsException(){
        System.out.println("Index Out Of Bounds Exception");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(){
        System.err.println("Exception Error");
        return "error";
    }
}
