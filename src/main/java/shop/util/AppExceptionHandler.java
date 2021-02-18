package shop.util;

import javassist.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import shop.util.exception.EmptyStockException;
import shop.util.exception.NotEnoughProductException;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView pagesNotFoundException(){
        return new ModelAndView("/404");
    }

    @ExceptionHandler(NotEnoughProductException.class)
    public ModelAndView productException(NotEnoughProductException e){
        ModelAndView mav = new ModelAndView("/productExc");
        mav.addObject("exception", e);
        return mav;
    }

    @ExceptionHandler(EmptyStockException.class)
    public ModelAndView productException(EmptyStockException e){
        ModelAndView mav = new ModelAndView("/productExc");
        mav.addObject("exception", e);
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView pagesException(Exception e){
        if ( e.getCause() instanceof EmptyStockException){
            return productException((EmptyStockException) e.getCause());
        }
        ModelAndView mav = new ModelAndView("/500");
        mav.addObject("exception", e);
        return mav;
    }


}
