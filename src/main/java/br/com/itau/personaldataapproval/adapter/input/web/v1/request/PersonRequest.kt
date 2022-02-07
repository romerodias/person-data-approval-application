package br.com.itau.personaldataapproval.adapter.input.web.v1.request

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class PersonRequest(
    val name: String,
    val lastName: String,
    val age: Int,
    val country: String
)
