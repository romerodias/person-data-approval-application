package br.com.itau.personaldataapproval.adapter.input.web.v1.controller

import br.com.itau.personaldataapproval.adapter.input.web.v1.api.PersonApi
import br.com.itau.personaldataapproval.adapter.input.web.v1.converter.toAdapter
import br.com.itau.personaldataapproval.adapter.input.web.v1.converter.toDomain
import br.com.itau.personaldataapproval.adapter.input.web.v1.request.PersonRequest
import br.com.itau.personaldataapproval.adapter.input.web.v1.response.PersonResponse
import br.com.itau.personaldataapproval.application.port.input.FindPersonUseCase
import br.com.itau.personaldataapproval.application.port.input.PersonApproveUseCase
import br.com.itau.personaldataapproval.application.port.input.PersonRejectUseCase
import br.com.itau.personaldataapproval.application.port.input.RequestPersonApprovalUseCase
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController(
    private val requestPersonApprovalUseCase: RequestPersonApprovalUseCase,
    private val personApproveUseCase: PersonApproveUseCase,
    private val personRejectUseCase: PersonRejectUseCase,
    private val findPersonUseCase: FindPersonUseCase
) : PersonApi {

    private var log = LoggerFactory.getLogger(PersonController::class.java)

    override fun approval(personRequest: PersonRequest) {
        log.info("Start to create a person approval")
        requestPersonApprovalUseCase.execute(personRequest.toDomain())
        log.info("Finish to create a person approval")
    }

    override fun approve(personId: String) {
        log.info("Start to approve a person approval")
        personApproveUseCase.execute(personId)
        log.info("Finish to approve a person approval")
    }

    override fun reject(personId: String) {
        log.info("Start to reject a person approval")
        personRejectUseCase.execute(personId)
        log.info("Finish to reject a person approval")
    }

    override fun onApproval(): List<PersonResponse> {
        log.info("Start to find person approval")
        return findPersonUseCase.execute().toAdapter().also {
            log.info("Finish to find person approval")
        }
    }
}