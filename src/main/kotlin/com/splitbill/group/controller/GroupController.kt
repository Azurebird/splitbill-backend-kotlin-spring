package com.splitbill.group.controller

import com.splitbill.auth.model.LoginModel
import com.splitbill.group.model.GroupModel
import com.splitbill.group.service.GroupService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/group")
class GroupController(
    val groupService: GroupService
) {

    @PostMapping("/")
    fun create(
        @RequestParam("name") name: String,
        auth: Authentication
    ): ResponseEntity<GroupModel> {
        val login = auth.principal as LoginModel
        return ResponseEntity(groupService.create(name, login.profileId), HttpStatus.OK)
    }

    @PostMapping("/profile")
    fun addProfile(
        @RequestParam("groupId") groupId: String,
        @RequestParam("email") email: String
    ): ResponseEntity<Any> {
        groupService.addProfile(groupId, email)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/")
    fun list(auth: Authentication): ResponseEntity<List<GroupModel>> {
        val login = auth.principal as LoginModel
        return ResponseEntity(groupService.list(login.profileId), HttpStatus.OK)
    }
}