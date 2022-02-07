package br.com.itau.personaldataapproval.adapter.output.approval.notification

import br.com.itau.personaldataapproval.adapter.output.approval.notification.config.SnsConfig
import br.com.itau.personaldataapproval.application.domain.Person
import br.com.itau.personaldataapproval.application.port.output.PersonApprovalNotificationPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Component
class PersonApprovalNotificationPublisher(
    private val snsConfig: SnsConfig
) : PersonApprovalNotificationPort {

    private var log = LoggerFactory.getLogger(PersonApprovalNotificationPublisher::class.java)

    override fun send(person: Person) {
        log.info("Start to send person approval notification")
        val publishResult = snsConfig.snsClient().publish(
            snsConfig.productEventsTopic().topicArn,
            person.toString()
        )
        log.info("Finish to send person approval notification with messageId {}",
            publishResult.messageId)
    }
}