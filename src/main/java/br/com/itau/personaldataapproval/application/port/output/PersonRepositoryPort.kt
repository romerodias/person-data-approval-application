package br.com.itau.personaldataapproval.application.port.output

import br.com.itau.personaldataapproval.application.domain.Person

interface PersonRepositoryPort {
    fun save(person: Person)
    fun findAll(): List<Person>
    fun findByPersonId(personId: String): Person
}