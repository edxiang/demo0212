package org.kevin.demo0212.common;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author Kevin.Z
 * @version 2020-03-12
 */
public class CommonUtils {
    private final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String replaceLineCharacter(String str){
        return str.replaceAll("[\\n]", "<br/>");
    }

    public static String securityPwdEncoder(String pwd){
        PasswordEncoder pwdEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return pwdEncoder.encode(pwd);
    }

    public static LocalDateTime parseStr2LocalDateTime(String ldtStr, boolean starts){
        if(starts){
            ldtStr += " 00:00:00";
        } else {
            ldtStr += " 23:59:59";
        }

        return LocalDateTime.parse(ldtStr, dtf);
    }

}
