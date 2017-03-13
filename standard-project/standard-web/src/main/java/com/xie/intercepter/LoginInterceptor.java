package com.xie.intercepter;

import com.alibaba.fastjson.JSONObject;
import com.xie.java.common.response.ResponseDataVo;
import com.xie.java.common.response.ResponseEnum;
import com.xie.java.constant.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 登录拦截
 *
 * @author Window 7
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static String USER_TOKEN = "token";


    @Autowired
    AppConfig appConfig;

    private Set<String> whileListSet = new HashSet<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String pathInfo = request.getPathInfo();
        String requestURI = request.getRequestURI();
        if(requestURI == null ){
            return true;
        }
        if (isInWileList(requestURI) || isStaticResouce(requestURI) || isLogin(request, response)) {
            return true;
        }
        return false;

    }

    private boolean isLogin(HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader(USER_TOKEN);
        if ("".equals(token) || token == null) {
            JSONObject json = new JSONObject();
            ResponseDataVo.error(ResponseEnum.TOKEN_ERR);
            String res = JSONObject.toJSONString(ResponseDataVo.error(ResponseEnum.TOKEN_ERR));
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "text/html; charset=UTF-8");
            try {
                response.getWriter().println(res);
            } catch (IOException e) {
                logger.error("login eer:{}", e.getMessage());

            }
            return false;
        } else {
            Object userInfo = request.getSession().getAttribute(token);
            if (userInfo == null) {
                return false;
            }
        }
        return true;
    }

    private boolean isStaticResouce(String path) {

        if(path.startsWith("static")){
            return true;
        }
        return false;
    }

    private boolean isInWileList(String path) {

        if (whileListSet.size() == 0) {
            loadWhileList();
        }
        return whileListSet.contains(path);

    }

    private void loadWhileList() {

        String[] split = appConfig.getWhileList().split(",");
        for(String s:split){
            whileListSet.add(s);
        }
    }


}
