package io.mmaltsev.vkeducation.domain.appdetails
import javax.inject.Inject

class GetAppDetailsUseCase @Inject constructor(
    private val repository: AppDetailsRepository,
) {
    suspend operator fun invoke(id: String): AppDetails {
        return repository.getAppDetails(id)
    }
}