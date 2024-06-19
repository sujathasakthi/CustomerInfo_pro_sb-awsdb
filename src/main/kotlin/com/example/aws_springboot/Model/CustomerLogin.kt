package com.example.aws_springboot.Model

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "CustomerLogin")
data class CustomerLogin(

    @get:DynamoDBHashKey(attributeName = "EmailId")
    var EmailId:String? = null,
    @DynamoDBAttribute
    var Password:String? = null,

)

