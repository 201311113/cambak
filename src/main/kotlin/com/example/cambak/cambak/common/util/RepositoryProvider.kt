package com.example.cambak.cambak.common.util

import com.example.cambak.database.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RepositoryProvider {

    @Autowired
    lateinit var userRepository: UserRepository
}