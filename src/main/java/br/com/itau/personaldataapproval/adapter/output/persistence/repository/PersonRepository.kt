package br.com.itau.personaldataapproval.adapter.output.persistence.repository

import br.com.itau.personaldataapproval.adapter.output.persistence.entity.PersonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface PersonRepository : JpaRepository<PersonEntity, String> {
    @Query(value = "from PersonEntity t where requestDate < :date")
    fun findAllByRequestDateGreaterThan(@Param("date") date: LocalDateTime): List<PersonEntity>
}


