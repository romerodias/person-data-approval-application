package br.com.itau.personaldataapproval.application.port.output

import br.com.itau.personaldataapproval.application.domain.Person

interface PersonApprovalNotificationPort {
    fun send(person: Person)
}