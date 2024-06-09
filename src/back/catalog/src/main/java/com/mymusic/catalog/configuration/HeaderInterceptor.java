package com.mymusic.catalog.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymusic.catalog.util.HeaderStorage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@Component
public class HeaderInterceptor  implements HandlerInterceptor {

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (Objects.equals(request.getMethod(), "OPTIONS")){
            return true;
        }
        String myHeader = request.getHeader("Authorization");

        if (myHeader == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        myHeader = myHeader.replace("Bearer ", "");
        var jwtParts = myHeader.split("\\.");
        var payload = new String(Base64.getDecoder().decode(jwtParts[1]), StandardCharsets.UTF_8);
        var node = mapper.readTree(payload);
        HeaderStorage.setMyHeader(node.get("sub").textValue());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Очистка ThreadLocal после завершения обработки запроса
        HeaderStorage.clear();
    }
}
