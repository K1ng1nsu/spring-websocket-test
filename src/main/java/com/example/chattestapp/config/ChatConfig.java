package com.example.chattestapp.config;


import com.example.chattestapp.interceptor.JwtChannelInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.nio.file.AccessDeniedException;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class ChatConfig implements WebSocketMessageBrokerConfigurer {

    private final JwtChannelInterceptor  jwtChannelInterceptor;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

        registration.interceptors(jwtChannelInterceptor);

//        registration.interceptors(new ChannelInterceptor() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//                if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
//                    String destination = accessor.getDestination();
//                    // 예: 특정 채널만 허용
//                    if ("/sub/chat/room/secret".equals(destination)) {
//                        // 사용자 정보 등으로 검사
//                        Authentication auth = (Authentication) accessor.getUser();
//                        if (auth == null || !auth.getAuthorities().stream()
//                                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
//                            // 예외 발생 시 클라이언트는 구독 실패
//                            throw new AccessDeniedException("이 채널은 관리자만 구독할 수 있습니다.");
//                        }
//                    }
//                }
//                return message;
//            }
//        });
    }
}
