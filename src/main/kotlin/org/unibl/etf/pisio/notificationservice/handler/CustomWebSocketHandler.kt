package org.unibl.etf.pisio.notificationservice.handler

import com.google.gson.Gson
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import org.unibl.etf.pisio.notificationservice.notification.Notification
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks

@Component
class CustomWebSocketHandler(private val sink: Sinks.Many<Notification>) : WebSocketHandler {


    override fun handle(session: WebSocketSession): Mono<Void> {
        val gson = Gson();
        val flux = sink.asFlux().map {
            session.textMessage(gson.toJson(it))
        }
        return session.send(flux)
    }
}