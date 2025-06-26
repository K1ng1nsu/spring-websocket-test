package com.example.chattestapp.interceptor;

import com.example.chattestapp.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtChannelInterceptor implements ChannelInterceptor {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER_ = "Bearer ";
    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String token = Optional.ofNullable(accessor.getFirstNativeHeader(AUTHORIZATION))
                    .filter(t -> t.startsWith(BEARER_))
                    .map(t -> t.substring(BEARER_.length()))
                    .orElseThrow(() -> new RuntimeException("Invalid token"));

            // JWT 검증 및 사용자 정보 추출
            String username = jwtUtil.getUsernameByToken(token);


            String userRole = userDetails.get
            Authentication authentication = createAuthentication(userId, userRole);
            accessor.setUser(authentication);
        }
        return message;
    }


}
