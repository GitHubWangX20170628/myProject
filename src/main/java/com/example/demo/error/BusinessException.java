package com.example.demo.error;
/**
 * @Decription:业务异常
 * @author wangX
 * @date 2018/7/24 9:11
 *
 */
public class BusinessException extends RuntimeException{
    public BusinessException(){}
    public BusinessException(String message){
        super(message);
    }
}
