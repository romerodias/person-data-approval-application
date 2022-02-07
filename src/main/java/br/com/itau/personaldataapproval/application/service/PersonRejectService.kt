package br.com.itau.personaldataapproval.application.service

import br.com.itau.personaldataapproval.application.domain.Person
import br.com.itau.personaldataapproval.application.port.input.PersonRejectUseCase
import br.com.itau.personaldataapproval.application.port.output.PersonRepositoryPort
import br.com.itau.personaldataapproval.application.service.events.PersonRejectEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PersonRejectService(
    private val personRepositoryPort: PersonRepositoryPort,
    private val eventPublisher: ApplicationEventPublisher
) : PersonRejectUseCase {

    override fun execute(personId: String) {
        val person = personRepositoryPort.findByPersonId(personId)

        if(person.status == Person.Status.PENDING) {
            person.status = Person.Status.REJECTED
            person.rejectionDate = LocalDateTime.now()
            person.save(personRepositoryPort)
            eventPublisher.publishEvent(PersonRejectEvent(personId))
        }
    }
}