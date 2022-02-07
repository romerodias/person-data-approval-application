package br.com.itau.personaldataapproval.application.service

import br.com.itau.personaldataapproval.application.domain.Person
import br.com.itau.personaldataapproval.application.port.input.RequestPersonApprovalUseCase
import br.com.itau.personaldataapproval.application.port.output.PersonApprovalNotificationPort
import br.com.itau.personaldataapproval.application.port.output.PersonRepositoryPort
import br.com.itau.personaldataapproval.application.service.events.RequestPersonApprovalEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class RequestPersonApprovalService(
    private val personRepositoryPort: PersonRepositoryPort,
    private val personApprovalNotificationPort: PersonApprovalNotificationPort,
    private val eventPublisher: ApplicationEventPublisher
) : RequestPersonApprovalUseCase {

    override fun execute(person: Person) {

        person.id = UUID.randomUUID().toString()
        person.status = Person.Status.PENDING
        person.requestDate = LocalDateTime.now()
        person.save(personRepositoryPort)

        person.notifyApprovalRequest(personApprovalNotificationPort)

        eventPublisher.publishEvent(RequestPersonApprovalEvent())
    }
}