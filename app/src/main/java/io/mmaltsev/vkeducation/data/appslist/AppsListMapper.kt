package io.mmaltsev.vkeducation.data.appslist

import io.mmaltsev.vkeducation.domain.appslist.App
import javax.inject.Inject

class AppsListMapper @Inject constructor() {

    fun toDomain(dto: AppDto): App {
        return App(
            id = dto.id,
            title = dto.title,
            subtitle = dto.subtitle,
            category = dto.category,
            iconKey = dto.iconKey,
        )
    }
}