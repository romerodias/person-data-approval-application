package br.com.itau.personaldataapproval.adapter.input.web.v1.api

import br.com.itau.personaldataapproval.adapter.input.web.v1.request.PersonRequest
import br.com.itau.personaldataapproval.adapter.input.web.v1.response.PersonResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1/person")
interface PersonApi {

    @PostMapping("/approval")
    fun approval(@RequestBody personRequest: PersonRequest)

    @PostMapping("/{person_id}/approve")
    fun approve(@PathVariable("person_id") personId: String)

    @PostMapping("/{person_id}/reject")
    fun reject(@PathVariable("person_id") personId: String)

    @GetMapping("/on-approval")
    fun onApproval(): List<PersonResponse>
}