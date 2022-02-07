package br.com.itau.personaldataapproval.adapter.input.web.v1.response

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class PersonResponse(
    val id: String,
    val name: String,
    val lastName: String,
    val age: Int,
    val country: String,
    val status: String
)
