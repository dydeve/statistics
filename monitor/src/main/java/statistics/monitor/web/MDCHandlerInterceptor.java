package statistics.monitor.web;

import org.slf4j.MDC;
import org.springframework.web.context.request.Log4jNestedDiagnosticContextInterceptor;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import statistics.monitor.util.UniqueId;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置mdc 变量
 * @see Log4jNestedDiagnosticContextInterceptor
 * Created by dy on 2018/4/13.
 */
public class MDCHandlerInterceptor implements AsyncHandlerInterceptor {

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.clear();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tid = request.getHeader("tid");
        if (tid == null) {
            tid = UniqueId.generate();
        }
        MDC.put("tid", tid);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.clear();
    }
}
