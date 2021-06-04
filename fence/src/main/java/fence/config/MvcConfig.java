package fence.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fence.entity.UserEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.method.HandlerMethod;
import fence.core.annotation.LoginRequired;
import java.io.PrintWriter;
import fence.entity.User;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/archor/list").setViewName("archor/list");
		registry.addViewController("/task/list").setViewName("task/list");
		registry.addViewController("/record/list").setViewName("record/list");
		registry.addViewController("/admin/list").setViewName("admin/list");
		registry.addViewController("/warnlog/list").setViewName("warnlog/list");
		registry.addViewController("/deptment/list").setViewName("deptment/list");
		registry.addViewController("/region/list").setViewName("region/list");
		registry.addViewController("/user/list").setViewName("user/list");
		
		
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/welcome").setViewName("welcome");
		registry.addViewController("/login").setViewName("login");
	}

	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new HandlerInterceptor() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				if (handler instanceof HandlerMethod) {
					HandlerMethod handlerMethod = (HandlerMethod) handler;
					LoginRequired loginRequired = handlerMethod.getMethodAnnotation(LoginRequired.class);
					if (null == loginRequired) {
						return true;
					}
					// 预请求
		            if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
						return true;
					}
					HttpSession session = request.getSession();
					UserEntity user = (UserEntity)session.getAttribute("user");
					if (user == null) {
						response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		                response.setHeader("Access-Control-Allow-Methods", "*");
		                response.setHeader("Access-Control-Max-Age", "3600");
		                response.setHeader("Access-Control-Allow-Credentials", "true");
		                response.setContentType("application/json; charset=utf-8");
		                response.setCharacterEncoding("utf-8");
		                PrintWriter pw = response.getWriter();
		                pw.write("{\"code\":" + HttpServletResponse.SC_UNAUTHORIZED + ",\"status\":\"error\",\"currentAuthority\":\"GUEST\",\"msg\":\"无授权访问，请先登录\"}");
		                pw.flush();
		                pw.close();
		                return false;
					}
				}
				return true;

			}
		}).addPathPatterns("/**").excludePathPatterns("/login", "/register","/currentUser", "/login/doLogin", "/user/register",
				"/mystatic/**", "/druid/**", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
