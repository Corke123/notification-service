package org.unibl.etf.pisio.notificationservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import org.unibl.etf.pisio.notificationservice.notification.Notification
import reactor.core.publisher.Sinks

@Configuration
class WebsocketConfig {

    @Bean
    fun handlerMapping(webSocketHandler: WebSocketHandler) =
        SimpleUrlHandlerMapping(mapOf("/ws/notifications" to webSocketHandler), 1)

    @Bean
    fun webSocketHandlerAdapter() = WebSocketHandlerAdapter()

    @Bean
    fun sink() = Sinks.many().multicast().directBestEffort<Notification>()
}