package com.mqa.demo.blog.util;

import org.apache.tomcat.util.buf.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengqa
 * @create 2018-05-10 11:42
 **/
public class ConstraintViolationExcepitonHanlder {

    /**
     * 获取批量异常
     * @param e
     * @return
     */
    public static String getMessage(ConstraintViolationException e) {
        List<String> msgList = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            msgList.add(constraintViolation.getMessage());
        }
        String messages = StringUtils.join(msgList, ';');
        return messages;
    }
}
