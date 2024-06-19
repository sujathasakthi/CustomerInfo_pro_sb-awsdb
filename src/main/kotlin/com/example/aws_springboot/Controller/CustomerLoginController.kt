package com.example.aws_springboot.Controller

import com.example.aws_springboot.DTO.EmailFetch
import com.example.aws_springboot.Model.CustomerLogin
import com.example.aws_springboot.Model.CustomerSignup
import com.example.aws_springboot.Model.ResponseMsg
import com.example.aws_springboot.Repository.CustomerLoginRepo
import com.example.aws_springboot.Repository.CustomerSigunpRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class CustomerLoginController(

    @Autowired private val customerLoginRepo: CustomerLoginRepo,
    @Autowired private val customerSigunpRepo: CustomerSigunpRepo
) {

   @PostMapping("/add")
    fun addLoginInfo(@RequestBody customerLogin: CustomerLogin): ResponseEntity<ResponseMsg> {
       val loginEmailId = customerLogin.EmailId

       // Check if the login email ID is not null
       if (loginEmailId != null) {
           // Fetch the CustomerSignup record by email ID
           val customerSignup: CustomerSignup? = customerSigunpRepo.getById(loginEmailId)
           return if (customerSignup != null) {
               // Compare the email IDs
               if (customerSignup.emailId == loginEmailId) {
                   // Save the login information if the email IDs match
                   customerLoginRepo.save(customerLogin)
                   val response = ResponseMsg("Email is correct and login successful")
                   ResponseEntity(response, HttpStatus.OK)
               } else {
                   // Return a bad request if the email IDs do not match
                   val response = ResponseMsg("Email IDs do not match")
                   ResponseEntity(response, HttpStatus.BAD_REQUEST)
               }
           } else {
               // Return a bad request if the signup email is not found
               val response = ResponseMsg("Signup email not found")
               ResponseEntity(response, HttpStatus.BAD_REQUEST)
           }
       } else {
           // Return a bad request if the login email ID is missing
           val response = ResponseMsg("Email ID is missing")
           return ResponseEntity(response, HttpStatus.BAD_REQUEST)
       }
   }



}


