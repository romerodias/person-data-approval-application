package br.com.itau.personaldataapproval.application.port.input

interface PersonApproveUseCase {
    fun execute(personId: String)
}