package com.example.aws_springboot.Model

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "CustomerInfo")
data class CustomerInfo(

    @get:DynamoDBHashKey
    @get:DynamoDBAutoGeneratedKey
    var customerId:String? = null,
    @DynamoDBAttribute
    var customerName:String? = null


)