package com.semenova.vkandroidproject.domain.usecase

import com.semenova.vkandroidproject.domain.AppRepository
import javax.inject.Inject

class GetAllApps @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke() = repository.getAllApps()
}