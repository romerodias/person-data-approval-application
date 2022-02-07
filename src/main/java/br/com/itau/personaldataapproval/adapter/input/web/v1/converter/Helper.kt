package br.com.itau.personaldataapproval.adapter.input.web.v1.converter

import br.com.itau.personaldataapproval.adapter.input.web.v1.request.PersonRequest
import br.com.itau.personaldataapproval.adapter.input.web.v1.response.PersonResponse
import br.com.itau.personaldataapproval.application.domain.Person

fun PersonRequest.toDomain() = Person(
    name = name,
    lastName = lastName,
    age = age,
    country = country
)

fun List<Person>.toAdapter() = map{
    PersonResponse(
        id = it.id.orEmpty(),
        name = it.name,
        lastName = it.lastName,
        age = it.age,
        country = it.country,
        status = it.status.name
    )
}