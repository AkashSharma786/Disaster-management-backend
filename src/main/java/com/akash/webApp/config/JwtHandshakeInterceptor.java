package com.akash.webApp.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.akash.webApp.Service.JWTService;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    @Autowired
    JWTService jwtService;

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) {

        if (request instanceof ServletServerHttpRequest servletRequest) {

            HttpServletRequest httpRequest = servletRequest.getServletRequest();

            String token = httpRequest.getParameter("token");
            System.out.println("token sssss" + token);

            if (token != null) {

                try { 
                    String username = jwtService.extractUsername(token);

                    System.out.println("UserName " + username);
                    attributes.put("username", username);
                } 

                catch (IllegalArgumentException e) {
                    // Handles: malformed token, invalid JWT format
                    System.err.println("Invalid token format: " + e.getMessage());
                    return false;

                } catch (io.jsonwebtoken.ExpiredJwtException e) {
                    // Handles: expired JWT tokens
                    System.err.println("Token expired: " + e.getMessage());
                    return false;

                } catch (io.jsonwebtoken.SignatureException e) {
                    // Handles: invalid token signature
                    System.err.println("Invalid token signature: " + e.getMessage());
                    return false;

                } catch (NullPointerException e) {
                    // Handles: jwtService or other dependencies are null
                    System.err.println("Null pointer exception during handshake: " + e.getMessage());
                    return false;

                } catch (Exception e) {
                    // Catch-all for unexpected exceptions
                    System.err.println("Unexpected error during WebSocket handshake: " + e.getMessage());
                    e.printStackTrace();
                    return false; // Reject connection on unexpected error
                }

                // System.out.println("Usernameasdsadasd" + username);

            }
        } 

        return true;

    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception exception) {
    }
}