package com.semenova.vkandroidproject.domain.usecase

import com.semenova.vkandroidproject.domain.AppRepository
import javax.inject.Inject

class LoadRatingInParallel @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(ids: List<String>) = repository.loadRatingsInParallel(ids)
}