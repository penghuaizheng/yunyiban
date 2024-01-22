package com.huashan.yebserver.filter;

import com.huashan.yebserver.domain.vo.AdminVo;
import com.huashan.yebserver.utils.JwtUtil;
import com.huashan.yebserver.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response); //可能是登录也可能不是，需要放行后交给后面去判断。
            return; //这里是为了获取结果返回的时候不往下执行。
        }
        String username;
        try {
            username = jwtUtil.getUsernameFromToken(token);
        } catch (Exception e) {
            throw new RuntimeException("token出错");
        }
        //从redis中获取AdminVo信息
        AdminVo adminVo = (AdminVo) redisUtil.get("security:"+username);
        if (ObjectUtils.isEmpty(adminVo)) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println(adminVo);

        Collection<? extends GrantedAuthority> authorities = adminVo.getAuthorities();
        //存入SecurityContextHolder
        //TODO 获取权限信息封装到authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(adminVo, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);

    }
}
