package com.rocketraman

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class WebsocketIssueApplication

fun main(args: Array<String>) {
    SpringApplication.run(WebsocketIssueApplication::class.java, *args)
}
