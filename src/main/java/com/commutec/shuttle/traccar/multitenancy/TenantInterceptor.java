package com.commutec.shuttle.traccar.multitenancy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commutec.shuttle.traccar.exception.TenantNotFoundException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * TenantInterceptor
 */
@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) throws Exception {
        String tenantID = request.getHeader("X-TenantID");
        if (tenantID == null) {
            throw new TenantNotFoundException("X-TenantID not present in the Request Header");
        }
        TenantContext.setCurrentTenant(tenantID);
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        TenantContext.clear();
    }
    
}