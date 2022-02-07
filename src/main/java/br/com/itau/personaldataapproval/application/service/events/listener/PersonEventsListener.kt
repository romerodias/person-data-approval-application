package br.com.itau.personaldataapproval.application.service.events.listener

import br.com.itau.personaldataapproval.application.service.events.PersonApprovedEvent
import br.com.itau.personaldataapproval.application.service.events.PersonRejectEvent
import br.com.itau.personaldataapproval.application.service.events.RequestPersonApprovalEvent
import io.micrometer.core.instrument.Metrics
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
open class PersonEventsListener(
    @Value("\${metric.approved.counter:approved.counter}") val personDataApprovedCounter: String,
    @Value("\${metric.rejected.counter:rejected.counter}") val personDataRejectedCounter: String,
    @Value("\${metric.request.counter:request.counter}") val personDataRequestCounter: String
) {

    @EventListener
    @Async
    fun onPersonDataApprovedEvent(event: PersonApprovedEvent) {
        Metrics.counter("approved.counter").increment()
    }

    @EventListener
    @Async
    fun onPersonDataRejectedEvent(event: PersonRejectEvent) {
        Metrics.counter("rejected.counter").increment()
    }

    @EventListener
    @Async
    fun onPersonDataRequestEvent(event: RequestPersonApprovalEvent) {
        Metrics.counter("request.counter").increment()
    }
}