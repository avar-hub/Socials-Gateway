package com.socials.Gateway.filter;

import com.socials.Gateway.exception.TokenMissingException;
import com.socials.Gateway.util.JWTValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends AbstractGatewayFilterFactory {

    private final RouteValidator validator;

    private final JWTValidation jwtValidation;
    public static class config{

    }
    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
        if(validator.isSecured.test(exchange.getRequest()))
        {
            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                throw new TokenMissingException("Token is not present");
            }
            String authHeader= exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if(authHeader!=null && authHeader.startsWith("Bearer ")) {
                authHeader=authHeader.substring(7);
            }
            try {
                    jwtValidation.validateToken(authHeader);
            }
            catch (Exception ex){
                    throw new TokenMissingException("Not authorized to access the request");
            }
        }
        return chain.filter(exchange);
        });
    }
}
