package com.splitbill.profile.controller

import com.splitbill.profile.model.ProfileModel
import com.splitbill.profile.service.ProfileService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/profile")
class ProfileController(
    var profileService: ProfileService
) {

    @PostMapping("/")
    fun register(@RequestBody profileModel: ProfileModel): ProfileModel {

        return profileService.createProfile(profileModel.email, profileModel.password)
    }
}