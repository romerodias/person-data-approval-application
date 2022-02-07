package br.com.itau.personaldataapproval.application.service

import br.com.itau.personaldataapproval.application.domain.Person
import br.com.itau.personaldataapproval.application.port.input.FindPersonUseCase
import br.com.itau.personaldataapproval.application.port.input.PersonRejectUseCase
import br.com.itau.personaldataapproval.application.port.output.PersonRepositoryPort
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class FindPersonService(
    private val personRepositoryPort: PersonRepositoryPort
) : FindPersonUseCase {

    override fun execute() : List<Person>{
       return personRepositoryPort.findAll()
    }
}