package br.com.itau.personaldataapproval.adapter.output.persistence

import br.com.itau.personaldataapproval.adapter.output.persistence.converter.toDomain
import br.com.itau.personaldataapproval.adapter.output.persistence.converter.toEntity
import br.com.itau.personaldataapproval.adapter.output.persistence.repository.PersonRepository
import br.com.itau.personaldataapproval.application.domain.Person
import br.com.itau.personaldataapproval.application.port.output.PersonRepositoryPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class PersonRepositoryService(
   private val personRepository: PersonRepository
): PersonRepositoryPort {

    private var log = LoggerFactory.getLogger(PersonRepositoryService::class.java)

    override fun save(person: Person) {
        log.info("Start to persist person entity {}", person)
        personRepository.save(person.toEntity())
        log.info("Finish to persist person entity {}")
    }

    override fun findAll(): List<Person> {
        log.info("Start to find all person approval")
        val threeDaysAgoDate = LocalDateTime.now().minusMinutes(2)
        return personRepository.findAllByRequestDateGreaterThan(threeDaysAgoDate).toDomain().also {
            log.info("Finish to find all person approval")
        }
    }

    override fun findByPersonId(personId: String): Person {
        log.info("Start to find a person approval")
        return personRepository.findById(personId).get().toDomain().also {
            log.info("Finish to find a person approval")
        }
    }
}