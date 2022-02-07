package br.com.itau.personaldataapproval.adapter.output.persistence.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "person")
data class PersonEntity(
    @Id
    val id: String? = null,
    val name: String? = null,
    val lastName: String? = null,
    val age: Int? = null,
    val country: String? = null,
    val requestDate: LocalDateTime? = null,
    val approvalDate: LocalDateTime? = null,
    val rejectionDate: LocalDateTime? = null,
    val status: String? = null
)