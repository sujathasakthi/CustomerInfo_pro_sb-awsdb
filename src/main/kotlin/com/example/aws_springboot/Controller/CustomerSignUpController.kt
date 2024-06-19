package com.example.aws_springboot.Controller

import com.example.aws_springboot.Model.CustomerSignup
import com.example.aws_springboot.Model.ResponseMsg
import com.example.aws_springboot.Repository.CustomerSigunpRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.regex.Pattern


@RestController
@RequestMapping("/signup")
class CustomerSignUpController @Autowired constructor(

        private val customerSigunpRepo: CustomerSigunpRepo
) {


    @PostMapping("/add")
    fun addSignupInfo(@RequestBody customerSignup: CustomerSignup): ResponseEntity<Any> {
        val emailId = customerSignup.emailId
        val password = customerSignup.password
        val emailRegex = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")


        // Validate email format
        if (emailId == null || !emailRegex.matcher(emailId).matches()) {
            val errorMsg = ResponseMsg(message = "Invalid email format")
            return ResponseEntity(errorMsg, HttpStatus.BAD_REQUEST)
        }

        // Validate password
          if (password == null || password.length < 8 ||
            !password.any { it.isDigit() } ||
            !password.any { it.isLowerCase() } ||
            !password.any { it.isUpperCase() }) {
            val errorMsg = ResponseMsg(message = "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, and one uppercase letter")
            return ResponseEntity(errorMsg, HttpStatus.BAD_REQUEST)
        }
        //compare confirm password and password
         if(password != customerSignup.confirmPassword){
             val ermsg = ResponseMsg(message = "this confirmpasword is not equal to password")
             return ResponseEntity(ermsg,HttpStatus.BAD_REQUEST)

         }

       else{
        customerSigunpRepo.save(customerSignup)

        val msg = ResponseMsg(message = "SignUp Done Sucessfully")
        return ResponseEntity(msg, HttpStatus.OK)
    }
    }
    @PutMapping("/update")
    fun updateSignupInfo(@RequestBody customerSignup: CustomerSignup):ResponseEntity<ResponseMsg>{
        customerSigunpRepo.update(customerSignup)
        val msg = ResponseMsg(message = "Sign Updated Successfully ")
        return ResponseEntity(msg, HttpStatus.OK)
    }

    @DeleteMapping("/remove")
    fun deleteSignUp(@RequestBody customerSignup: CustomerSignup):ResponseEntity<ResponseMsg>{
         customerSigunpRepo.delete(customerSignup)
        val msg = ResponseMsg(message = "SignUp  Deleted Successfully ")
        return ResponseEntity(msg, HttpStatus.OK)
    }

      @GetMapping("/fetch/{emailId}")
      fun getByIdSignupInfo(@PathVariable("emailId")emailId: String):CustomerSignup?{
           return customerSigunpRepo.getById(emailId)
        }

    @GetMapping("/all")
    fun getAllInfo():MutableList<CustomerSignup>{
        return customerSigunpRepo.getAll()
    }




}