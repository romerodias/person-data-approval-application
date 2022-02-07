package br.com.itau.personaldataapproval.adapter.output.approval.notification.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import com.amazonaws.services.sns.model.Topic

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain

import com.amazonaws.services.sns.AmazonSNSClientBuilder

import com.amazonaws.services.sns.AmazonSNS
import org.springframework.context.annotation.Bean
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration


@Configuration
open class SnsConfig {

    @Value("\${aws.region}")
    private val awsRegion: String? = null

    @Value("\${aws.sns.topic.person.approval.notification.arn}")
    private val personApprovalNotificationTopic: String? = null

    var endpointConfiguration = EndpointConfiguration(
        "http://localhost:4566",
        awsRegion
    )
    @Bean
    open fun snsClient(): AmazonSNS {
        return AmazonSNSClientBuilder.standard()
            .withEndpointConfiguration(endpointConfiguration)
            .build()
    }

    @Bean
    open fun productEventsTopic(): Topic {
        return Topic().withTopicArn(personApprovalNotificationTopic)
    }
}