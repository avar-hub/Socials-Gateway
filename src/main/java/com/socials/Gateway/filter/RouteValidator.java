package com.socials.Gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> apiList= List.of(

            "auth/register",
            "auth/getToken",
            "auth/verify-account",
            "/auth/forgotPass",
            "/auth/resetPass",
            "eureka"
    );

    // Server Http req must be of reactive package

    // serverHttpRequest.getURI().getPath(): This retrieves the path from the incoming HTTP request URI.

//    apiList.stream().noneMatch(...): This iterates over the apiList and checks if
//    none of the API routes in the list match the request URI path
//    . If none match, it returns true, indicating that the route is secured.
    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest ->
            apiList.stream().noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
