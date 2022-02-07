package br.com.itau.personaldataapproval.adapter.output.persistence.converter

import br.com.itau.personaldataapproval.adapter.output.persistence.entity.PersonEntity
import br.com.itau.personaldataapproval.application.domain.Person

fun Person.toEntity() = PersonEntity(
    name = name,
    lastName = lastName,
    age = age,
    country = country,
    requestDate = requestDate,
    approvalDate = approvalDate,
    rejectionDate = rejectionDate,
    status = status.name,
    id = id
)

fun List<PersonEntity>.toDomain() = map {
    Person(
        name = it.name!!,
        lastName = it.lastName!!,
        age = it.age!!,
        country = it.country!!,
        requestDate = it.requestDate,
        approvalDate = it.approvalDate,
        rejectionDate = it.rejectionDate,
        status = Person.Status.valueOf(it.status!!),
        id = it.id
    )
}

fun PersonEntity.toDomain() = Person(
        name = name!!,
        lastName = lastName!!,
        age = age!!,
        country = country!!,
        requestDate = requestDate,
        approvalDate = approvalDate,
        rejectionDate = rejectionDate,
        status = Person.Status.valueOf(status!!),
        id = id
    )
