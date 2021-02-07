package shop.util;

import javassist.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView pagesNotFoundException(){
        return new ModelAndView("/404");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView pagesException(Exception e){
        ModelAndView mav = new ModelAndView("/500");
        mav.addObject("exception", e);
        return mav;
    }
}
