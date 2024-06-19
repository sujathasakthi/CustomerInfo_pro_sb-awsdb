package com.example.aws_springboot.Repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.example.aws_springboot.Model.CustomerInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class CustomerRepo2  @Autowired constructor (
     private val dynamoDBMapper: DynamoDBMapper
)
{
    fun save(customerInfo: CustomerInfo) {
        return dynamoDBMapper.save(customerInfo)
    }
    fun update(customerInfo: CustomerInfo): CustomerInfo {
        dynamoDBMapper.save(customerInfo)
        return customerInfo
    }
    fun delete(id: String) {

        val customer = dynamoDBMapper.load(CustomerInfo::class.java, id)
        if (customer != null) {

            dynamoDBMapper.delete(customer)
        } else {
            throw IllegalArgumentException("Customer with ID $id does not exist.")
        }

    }

    fun getById(id: String): CustomerInfo? {
        return dynamoDBMapper.load(CustomerInfo::class.java, id)
    }

    fun getAll(): List<CustomerInfo> {

        val scanExpression = DynamoDBScanExpression()
        val scanResult = dynamoDBMapper.scan(CustomerInfo::class.java, scanExpression)
        return scanResult
    }




}