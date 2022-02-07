package br.com.itau.personaldataapproval.application.port.input

import br.com.itau.personaldataapproval.application.domain.Person

interface RequestPersonApprovalUseCase {
    fun execute(person: Person)
}