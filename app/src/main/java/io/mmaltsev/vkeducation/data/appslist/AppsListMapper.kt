package io.mmaltsev.vkeducation.data.appslist

import io.mmaltsev.vkeducation.domain.appslist.App
import io.mmaltsev.vkeducation.domain.appslist.CatalogAppDetails
import javax.inject.Inject

class AppsListMapper @Inject constructor() {

    fun toDomain(dto: AppDto): App {
        return App(
            id = dto.id,
            title = dto.name.ifBlank { "Без названия" },
            subtitle = dto.developer.ifBlank { "Разработчик не указан" },
            category = dto.category.ifBlank { "Без категории" },
        )
    }
    fun toDetailsDomain(dto: CatalogAppDetailsDto): CatalogAppDetails {
        return CatalogAppDetails(
            id = dto.id,
            name = dto.name,
            developer = dto.developer,
            category = dto.category,
            ageRating = dto.ageRating,
            size = dto.size,
            iconUrl = dto.iconUrl,
            screenshots = dto.screenshots,
            description = dto.description,
            lastUpdated = dto.lastUpdated,
        )
    }
}
