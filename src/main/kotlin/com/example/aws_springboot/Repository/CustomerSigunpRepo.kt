package com.example.aws_springboot.Repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.example.aws_springboot.Model.CustomerSignup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class CustomerSigunpRepo @Autowired constructor(private  val dynamoDBMapper: DynamoDBMapper) {


    fun save(customerSignup: CustomerSignup){
        return dynamoDBMapper.save(customerSignup)
    }
    fun update (customerSignup: CustomerSignup):CustomerSignup{
        dynamoDBMapper.save(customerSignup)
        return customerSignup
    }

    fun delete(customerSignup: CustomerSignup) {
        return dynamoDBMapper.delete(customerSignup)
    }

    fun getById(emailId: String):CustomerSignup?{
        return dynamoDBMapper.load(CustomerSignup::class.java,emailId)
    }

    fun getAll():MutableList<CustomerSignup>{
        val scanExpression = DynamoDBScanExpression()
        val scanResult = dynamoDBMapper.scan(CustomerSignup::class.java, scanExpression)
        return scanResult
    }




}