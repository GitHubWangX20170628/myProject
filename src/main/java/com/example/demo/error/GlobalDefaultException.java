package com.example.demo.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Decription:统一业务异常处理类
 * @author wangX
 * @date 2018/7/24 9:05
 *
 */
@ControllerAdvice(basePackages = "com.example.demo")
public class GlobalDefaultException {
    @ExceptionHandler({BusinessException.class})
    //如果返回的为json数据或其他对象，就添加该注解
    @ResponseBody
    public ErrorInfor defaultErrorHandler(HttpServletRequest req,Exception e) throws Exception{
        ErrorInfor errorInfor=new ErrorInfor();
        errorInfor.setMessage(e.getMessage());
        errorInfor.setUrl(req.getRequestURI());
        errorInfor.setCode(ErrorInfor.getSUCCESS());
        return  errorInfor;
    }
}
