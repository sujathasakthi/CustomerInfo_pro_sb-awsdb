package com.example.aws_springboot.Controller

import com.example.aws_springboot.Model.CustomerInfo
import com.example.aws_springboot.Model.ResponseMessage
import com.example.aws_springboot.Repository.CustomerRepo2
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer")
class CustomerController (

    @Autowired private val customerRepo2: CustomerRepo2
){
    @PostMapping("/add")
    fun createEmployee(@RequestBody customerInfo: CustomerInfo):ResponseEntity<ResponseMessage> {
        customerRepo2.save(customerInfo)
        val msg = ResponseMessage(message = "Data Saved Successfully = ${customerInfo.customerId}")
        return ResponseEntity(msg,HttpStatus.OK)
    }

    @PutMapping("/update")
    fun updateEmployee( @RequestBody customerInfo: CustomerInfo):ResponseEntity<ResponseMessage> {
         customerRepo2.update(customerInfo)
        val msg = ResponseMessage(message = "Data Updated Successfully = ${customerInfo.customerId}")
        return ResponseEntity(msg,HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteEmployee(@PathVariable id: String,@RequestBody customerInfo: CustomerInfo):ResponseEntity<ResponseMessage> {
       customerRepo2.delete(id)
        val msg = ResponseMessage(message = "Data Deleted Successfully = ${customerInfo.customerId}")
        return ResponseEntity(msg,HttpStatus.OK)
    }

    @GetMapping("/fetch/{id}")
    fun getEmployeeById(@PathVariable id: String): CustomerInfo? {
        return customerRepo2.getById(id)
    }

    @GetMapping("/all")
    fun getAllEmployees(): List<CustomerInfo> {
        return customerRepo2.getAll()
    }



}