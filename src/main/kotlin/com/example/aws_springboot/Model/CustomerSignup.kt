package com.example.aws_springboot.Model

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "CustomerSignUpInfo")
data class CustomerSignup(

    @get:DynamoDBHashKey(attributeName = "emailId")
   // @get:DynamoDBAutoGeneratedKey
    var emailId:String? = null,
    @DynamoDBAttribute
    var userName:String? = null,
    @DynamoDBAttribute
    var password:String? = null,
    @DynamoDBAttribute
    var confirmPassword:String? = null


)
data class ResponseMsg(
    var message:String
)