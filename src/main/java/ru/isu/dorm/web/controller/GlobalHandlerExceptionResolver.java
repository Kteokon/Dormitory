package ru.isu.dorm.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o, 
            Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("global_error");
        mav.addObject("exceptionType", e);
        mav.addObject("exceptionMessage", e.getMessage());
        mav.addObject("handlerMethod", o);
        return mav;
    }
}
