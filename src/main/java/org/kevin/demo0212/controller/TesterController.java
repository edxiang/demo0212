package org.kevin.demo0212.controller;

import org.kevin.demo0212.model.SecretMoment;
import org.kevin.demo0212.service.SecretMomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.List;

/**
 * @author Kevin.Z
 * @version 2020-03-12
 */
@Controller
public class TesterController {

    @GetMapping("/getData")
    public String getData(){
        return "hello world~";
    }

    @GetMapping("/ioPage/{i}")
    public String ioPage(@PathVariable("i") int x) throws Exception {
        try{
            if(x < 10)
                throw new Exception();
        } catch (Exception e){
            if(x == 0)
                throw new IOException();
            else if(x == 1){
                throw new IndexOutOfBoundsException();
            } else {
                throw e;
            }
        }
        return "index";
    }
}
