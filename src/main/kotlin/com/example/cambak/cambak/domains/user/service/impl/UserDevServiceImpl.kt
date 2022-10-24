package com.example.cambak.cambak.domains.user.service.impl

import com.example.cambak.cambak.common.util.RepositoryProvider
import com.example.cambak.cambak.domains.user.service.UserDevService
import com.example.cambak.database.entity.UserTemp
import com.example.cambak.database.repository.UserTempRepository
import org.springframework.stereotype.Service

@Service
class UserDevServiceImpl(
    var repo: RepositoryProvider
): UserDevService {
    override fun migration(): List<UserTemp> {
        return repo.userTempRepository.findAll()
    }
}