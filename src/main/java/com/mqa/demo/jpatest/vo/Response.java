package com.mqa.demo.jpatest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装返回对象
 * @author mengqa
 * @create 2018-05-10 11:40
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    // 处理是否成功
    private boolean success;
    // 消息数据
    private String message;
    // 返回数据
    private Object data;



}
