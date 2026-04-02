package io.mmaltsev.vkeducation.domain.appdetails

import javax.inject.Inject

class ToggleWishlistUseCase @Inject constructor(
    private val repository: AppDetailsRepository,
) {
    suspend operator fun invoke(id: String) {
        repository.toggleWishlist(id)
    }
}