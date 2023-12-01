package com.work.working.web.adapter;

import com.google.common.collect.Maps;
import com.woke.working.common.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionAdapter {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public ResponseVo handleResourceNotFoundException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        Map<String, String> errorMap = Maps.newHashMap();
        for (ObjectError error : allErrors) {
            FieldError fieldError = (FieldError) error;
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseVo.fail(400, errorMap);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVo systemExceptionHandler(Exception exception, HttpServletRequest request) {
        return ResponseVo.fail(500, "系统异常");
    }
}
