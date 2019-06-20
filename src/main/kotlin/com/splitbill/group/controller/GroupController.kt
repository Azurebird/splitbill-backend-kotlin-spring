package com.splitbill.group.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/group")
class GroupController {

    @PostMapping("/")
    fun getGroups(): String {
        return "You made it"
    }
}