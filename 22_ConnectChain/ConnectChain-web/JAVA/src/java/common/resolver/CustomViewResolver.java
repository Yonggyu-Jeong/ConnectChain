package common.resolver;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * <pre>
 *   CustomViewResolver 구현체 Class
 *   UrlBasedViewResolver 에서는 특정 surfix에 매칭되는 URL Pattern이 아니면 Exception을 발생하는 문제가 있음.
 *   다중 View Resolver를 구현하기 위해서 Exception이 아닌 null을 반환하여 p:order값에 따른 여러
 *   ViewResolver를 호출할 수 있도록 Customizing함.
 * </pre>
 * 
 * @package com.example.framework.common.resolver.CustomViewResolver.java
 */
public class CustomViewResolver extends UrlBasedViewResolver implements Ordered {
	protected View loadView(String viewName, Locale locale) throws Exception {
		AbstractUrlBasedView view = buildView(viewName);
		View viewObj = (View) getApplicationContext().getAutowireCapableBeanFactory().initializeBean(view, viewName);
		if (viewObj instanceof JstlView) {
			JstlView jv = (JstlView) viewObj;
			if (jv.getBeanName().indexOf(".jsp") != -1) {
				return null;
			}
		}
		return viewObj;
	}
}
