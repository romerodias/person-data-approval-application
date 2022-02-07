package br.com.itau.personaldataapproval

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//@EnableJpaRepositories("br.com.itau.personaldataapproval.adapter.output.persistence.repository")
//@ComponentScan("br.com.itau.personaldataapproval")
@SpringBootApplication
open class PersonalDataApprovalApplication

fun main(args: Array<String>) {
    runApplication<PersonalDataApprovalApplication>(*args)
}