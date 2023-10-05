package org.unibl.etf.pisio.notificationservice.notification

import mu.two.KotlinLogging
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import reactor.core.publisher.Sinks
import java.beans.ConstructorProperties
import java.util.*

private val logger = KotlinLogging.logger {}

@Component
class NotificationReceiver(private val sink: Sinks.Many<Notification>) {

    @RabbitListener(queues = ["#{queue.name}"])
    fun receive(@Payload notification: Notification) {
        logger.info("New message received with payload: $notification")
        sink.emitNext(notification, Sinks.EmitFailureHandler.FAIL_FAST)
    }
}

data class Notification @ConstructorProperties(
    "incidentId", "relatedIncidents"
) constructor(val incidentId: UUID, val relatedIncidents: List<UUID>)