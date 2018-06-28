package jp.co.rakus.ec201804a.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 500エラーを検知したときに呼ばれるハンドラクラス.
 * 
 * @author tatsuro.okazaki
 */
@Component
public class GlobalExceptionHandoler implements HandlerExceptionResolver {
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		ModelAndView mav = new ModelAndView("redirect:/user/systemError");
		ex.printStackTrace();
		return mav;
	}
}