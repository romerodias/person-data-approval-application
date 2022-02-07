package br.com.itau.personaldataapproval.application.domain

import br.com.itau.personaldataapproval.application.port.output.PersonApprovalNotificationPort
import br.com.itau.personaldataapproval.application.port.output.PersonRepositoryPort
import java.time.LocalDateTime

data class Person(
    val name: String,
    val lastName: String,
    val age: Int,
    val country: String,
    var requestDate: LocalDateTime? = null,
    var approvalDate: LocalDateTime? = null,
    var rejectionDate: LocalDateTime? = null,
    var status: Status = Status.PENDING,
    var id: String? = ""
) {
    enum class Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    fun save(personRepositoryPort: PersonRepositoryPort) {
        personRepositoryPort.save(this)
    }

    fun notifyApprovalRequest(personApprovalNotificationPort: PersonApprovalNotificationPort) {
        personApprovalNotificationPort.send(this)
    }


}
