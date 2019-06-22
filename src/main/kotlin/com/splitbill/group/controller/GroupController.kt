package com.splitbill.group.controller

import com.splitbill.auth.model.LoginModel
import com.splitbill.common.exception.BadRequestException
import com.splitbill.group.model.GroupModel
import com.splitbill.group.service.GroupService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException
import java.security.Principal

@RestController
@RequestMapping("/group")
class GroupController(
    val groupService: GroupService
) {

    @PostMapping("/")
    fun create(
        @RequestParam("name") name: String,
        principal: Authentication
    ): ResponseEntity<GroupModel> {
        val login = principal.principal as LoginModel
        return ResponseEntity(groupService.create(name, login.profileId), HttpStatus.OK)
    }
}