package br.com.itau.personaldataapproval.application.port.input

import br.com.itau.personaldataapproval.application.domain.Person

interface PersonRejectUseCase {
    fun execute(personId: String)
}