package com.br.TravelEase.EmailSmsSender.Config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    companion object{
    const val QUEUE_NAME: String = "emailQueue"
    const val EXCHANGE_NAME: String = "emailExchange"
    }

    @Bean
    fun emailQueue(): Queue {
        return Queue(QUEUE_NAME, true)
    }

    @Bean
    fun emailExchange(): TopicExchange {
        return TopicExchange(EXCHANGE_NAME)
    }

    @Bean
    fun binding(emailQueue: Queue, emailExchange: TopicExchange): Binding {
        return BindingBuilder.bind(emailQueue).to(emailExchange).with("email.#")
    }

    @Bean
    fun messageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.setMessageConverter(messageConverter())
        return rabbitTemplate
    }

}