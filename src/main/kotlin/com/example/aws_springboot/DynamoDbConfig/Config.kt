package com.example.aws_springboot.DynamoDbConfig

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
@Configuration
@EnableDynamoDBRepositories(basePackages = ["com.example.aws_springboot.Repository"])
class Config {

        @Value("\${aws.access.key}")
        private lateinit var accessKey: String

        @Value("\${aws.access.secret-key}")
        private lateinit var secretKey: String

        @Value("\${aws.region}")
        private lateinit var region: String

        @Value("\${aws.dynamodb.endpoint}")
        private lateinit var endpoint: String

            @Bean
             fun dynamoDBMapper(): DynamoDBMapper {
                return DynamoDBMapper(buildAmazonDynamoDB())
            }
            @Bean
             fun buildAmazonDynamoDB(): AmazonDynamoDB {
            return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
                .build()
             }
    }
