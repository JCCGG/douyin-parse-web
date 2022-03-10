package cn.jcc.dy.config;

import cn.jcc.dy.pojo.vo.ResultData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: dy
 * @description: 全局异常处理
 * @author: CCC
 * @create: 2022-03-10 09:22
 **/
@ControllerAdvice
public class SysExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultData<String> handlerEception(Exception e){
        ResultData<String> resultData = new ResultData<>();
        resultData.setCode(-1);
        resultData.setMsg(e.getMessage());
        resultData.setData("系统异常！");
        return resultData;
    }
}
