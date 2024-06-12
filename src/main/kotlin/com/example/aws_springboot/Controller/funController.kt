package com.example.aws_springboot.Controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fun")
class funController {

    @GetMapping("/add")
    fun add():String{
        return "hello"
    }

}