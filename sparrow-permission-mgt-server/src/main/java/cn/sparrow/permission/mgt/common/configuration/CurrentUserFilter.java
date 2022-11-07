package cn.sparrow.permission.mgt.common.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.sparrow.permission.model.common.CurrentUser;

@Component
public class CurrentUserFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 用于设置hibernate获取操作用户日志使用
        if(SecurityContextHolder.getContext().getAuthentication()!=null){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            CurrentUser.logIn(username);
        }else{
            CurrentUser.logOut();
        }
        
        filterChain.doFilter(request, response);
    }

}
