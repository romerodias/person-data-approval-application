package br.com.itau.personaldataapproval.application.service.events.metrics

import io.micrometer.core.instrument.Metrics.counter
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class PersonMetrics(
    @Value("\${metric.approved.counter}") private val personDataApprovedCounter: String,
    @Value("\${metric.rejected.counter}") private val personDataRejectedCounter: String,
    @Value("\${metric.request.counter}") private val personDataRequestCounter: String
) {


    fun personDataApprovedEvent() {
        counter(personDataApprovedCounter).increment()
    }

    fun personDataRejectedEvent() {
        counter(personDataRejectedCounter).increment()
    }

    fun personDataRequestEvent() {
        counter(personDataRequestCounter).increment()
    }

}