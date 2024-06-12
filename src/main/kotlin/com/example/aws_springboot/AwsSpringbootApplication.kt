package com.example.aws_springboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class AwsSpringbootApplication


fun main(args: Array<String>) {
	runApplication<AwsSpringbootApplication>(*args)
}
