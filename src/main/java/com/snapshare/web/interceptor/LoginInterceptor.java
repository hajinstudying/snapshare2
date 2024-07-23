package com.snapshare.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.snapshare.web.vo.MemberVo;


/**
 * 로그인 인터셉터
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    /*
     * 컨트롤러(즉 RequestMapping이 선언된 메서드 진입) 실행 직전에 동작.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    	log.info("preHandle 메소드");
    	
        HttpSession session = request.getSession();
        MemberVo memberVo = (MemberVo)session.getAttribute("memberVo");
        
		// 세션에 사용자 정보가 없다는 것은 로그인  안한 사용자!
        if(memberVo == null) {
        	
        	 log.info("세션에 사용자 정보가 없음");
        	 
        	// 인터셉터 오기 전에 가려고 했던 Url 추출
        	String previousUrl = request.getRequestURL().toString();

        	// 세션에 저장했다가 나중에 로그인이 되면 그 때 이동하기 위해서 저장
        	request.setAttribute("previousUrl", previousUrl);
        	log.info("원래 가려고 했던 URL : " + previousUrl);
        	
        	// 로그인 안한 사용자는 로그인 폼으로 이동		
        	String contextPath = request.getContextPath();
        	String url = contextPath + "/login"; // 로그인폼 띄워주는 메소드(핸들러) 호출
        	response.sendRedirect(url);
        	
        	return false; // 더이상 다음줄 이동 않고 리턴
        }

        log.info("이미 로그인 한 사용자입니다.");

        return true; //컨트롤러 요청 uri로 가도 되냐 안되냐를 허가하는 의미임. true:가도됨.
    }    
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
    						Object handler, ModelAndView modelAndView) throws Exception {
    	super.postHandle(request, response, handler, modelAndView);
    	log.info("postHandle");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    	log.info("afterCompletion 메소드");
    }    
    
}
