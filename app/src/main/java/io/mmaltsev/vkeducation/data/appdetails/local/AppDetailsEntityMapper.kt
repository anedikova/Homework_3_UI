package io.mmaltsev.vkeducation.data.appdetails.local

import io.mmaltsev.vkeducation.domain.appdetails.AppDetails
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AppDetailsEntityMapper @Inject constructor() {

    fun toDomain(entity: AppDetailsEntity): AppDetails {
        val screenshots = entity.screenshots
            ?.takeUnless { it == "null" || it.isBlank() }
            ?.let { screenshotsJson ->
                Json.decodeFromString<List<String>>(screenshotsJson)
            }
            ?: emptyList()

        return AppDetails(
            id = entity.id,
            name = entity.name,
            developer = entity.developer,
            category = entity.category,
            ageRating = entity.ageRating,
            size = entity.size,
            iconUrl = entity.iconUrl,
            screenshotUrlList = screenshots,
            description = entity.description,
            isInWishlist = entity.isInWishlist,
        )
    }

    fun toEntity(domain: AppDetails): AppDetailsEntity {
        return AppDetailsEntity(
            id = domain.id,
            name = domain.name,
            developer = domain.developer,
            category = domain.category,
            ageRating = domain.ageRating,
            size = domain.size,
            iconUrl = domain.iconUrl,
            screenshots = Json.encodeToString(domain.screenshotUrlList),
            description = domain.description,
            isInWishlist = domain.isInWishlist,
        )
    }
}