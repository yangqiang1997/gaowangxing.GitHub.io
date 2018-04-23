package com.cqut.auth.auth;

import com.alibaba.fastjson.JSONObject;
import com.cqut.auth.entity.AuthUser;
import com.cqut.auth.entity.JWTAuthenticationToken;
import com.cqut.auth.service.AuthenticationService;
import com.cqut.entity.global.JSONResult;
import com.cqut.util.constants.ResponseCodeConstants;
import com.cqut.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Component
public class HeaderTokenWebFilter extends OncePerRequestFilter {

    private static String HEADER_TOKEN_NAME = "Authorization";

    private static String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader(HEADER_TOKEN_NAME);
        if (StringUtil.isNullOrEmpty(accessToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = accessToken.replace(TOKEN_PREFIX, "");
        AuthUser authUser = authenticationService.findUserByToken(token);
        if (authUser != null) {
            Authentication auth = new JWTAuthenticationToken(authUser);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        } else {
            handleInvalidToken(response);
        }
    }

    private static String DEFAULT_CONTENT_TYPE = "application/json;charset=utf-8";

    private static String ACCESS_CONTROL_ALLOW_ORIGIN_NAME = "Access-Control-Allow-Origin";

    private static String ACCESS_CONTROL_ALLOW_ORIGIN_VALUE = "*";

    private void handleInvalidToken(HttpServletResponse response) {
        response.setContentType(DEFAULT_CONTENT_TYPE);
        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN_NAME, ACCESS_CONTROL_ALLOW_ORIGIN_VALUE);
        try (Writer writer = response.getWriter()) {
            writer.write(invalidTokenResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String INVALID_TOKEN_MSG = "token值无效或过期";

    private String invalidTokenResult() {
        JSONObject result = new JSONObject();
        result.put(JSONResult.CODE_KEY, ResponseCodeConstants.INVALID_TOKEN);
        result.put(JSONResult.MESSAGE_KEY, INVALID_TOKEN_MSG);
        return result.toJSONString();
    }
}
