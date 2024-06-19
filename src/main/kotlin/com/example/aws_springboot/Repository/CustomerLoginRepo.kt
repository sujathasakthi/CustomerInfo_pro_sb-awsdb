package com.example.aws_springboot.Repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.example.aws_springboot.Model.CustomerLogin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class CustomerLoginRepo (
    @Autowired private val dynamoDBMapper: DynamoDBMapper
){

    fun save(customerLogin: CustomerLogin){
        return dynamoDBMapper.save(customerLogin)
    }


}