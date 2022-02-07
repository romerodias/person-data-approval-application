package br.com.itau.personaldataapproval.application.service

import br.com.itau.personaldataapproval.application.domain.Person
import br.com.itau.personaldataapproval.application.port.input.PersonApproveUseCase
import br.com.itau.personaldataapproval.application.port.output.PersonRepositoryPort
import br.com.itau.personaldataapproval.application.service.events.PersonApprovedEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PersonApproveService(
    private val personRepositoryPort: PersonRepositoryPort,
    private val eventPublisher: ApplicationEventPublisher
) : PersonApproveUseCase {

    override fun execute(personId: String) {
        val person = personRepositoryPort.findByPersonId(personId)

        if(person.status == Person.Status.PENDING) {
            person.status = Person.Status.APPROVED
            person.approvalDate = LocalDateTime.now()
            person.save(personRepositoryPort)
            eventPublisher.publishEvent(PersonApprovedEvent())
        }
    }
}