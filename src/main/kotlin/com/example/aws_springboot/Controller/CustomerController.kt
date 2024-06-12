package com.example.aws_springboot.Controller

import com.example.aws_springboot.Model.CustomerInfo
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
    fun createEmployee(@RequestBody customerInfo: CustomerInfo):ResponseEntity<String> {
        customerRepo2.save(customerInfo)
        return ResponseEntity("Data Saved SuccessFully = ${customerInfo.customerId}",HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateEmployee(@PathVariable id: String, @RequestBody customerInfo: CustomerInfo):ResponseEntity<String> {
         customerRepo2.update(id,customerInfo)
        return ResponseEntity("Updated Saved Successfully = ${customerInfo.customerId}",HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: String,@RequestBody customerInfo: CustomerInfo):ResponseEntity<String> {
       customerRepo2.delete(id)
        return ResponseEntity("Deleted Saved Successfully = ${customerInfo.customerId}",HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable id: String): CustomerInfo? {
        return customerRepo2.getById(id)
    }

    @GetMapping("/all")
    fun getAllEmployees(): List<CustomerInfo> {
        return customerRepo2.getAll()
    }



}