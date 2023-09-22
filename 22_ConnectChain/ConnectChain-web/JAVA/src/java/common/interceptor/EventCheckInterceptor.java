package common.interceptor;

import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import common.collection.ABox;
import common.collection.ASession;

/**
 * <pre>
 * Controller 호출 전 Handler를 통한 Filter Interceptor Class
 * </pre>
 */
@Controller
public class EventCheckInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	protected ASession aSession; // aSession 객체 선언

	@Autowired
	protected HttpSession httpSession; // HttpSession 객체 선언

	/**
	 * <pre>
	 *  Controller 호출전 실행 메소드
	 * </pre>
	 * 
	 * @param request
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}
}