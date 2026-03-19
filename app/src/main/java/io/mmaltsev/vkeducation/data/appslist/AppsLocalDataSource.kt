package io.mmaltsev.vkeducation.data.appslist

import javax.inject.Inject

class AppsLocalDataSource @Inject constructor() {

    fun getApps(): List<AppDto> {
        return listOf(
            AppDto(
                id = "sber",
                title = "СберБанк Онлайн — с Салютом",
                subtitle = "Больше чем банк",
                category = "Финансы",
                iconKey = "sber",
            ),
            AppDto(
                id = "browser",
                title = "Яндекс.Браузер — с Алисой",
                subtitle = "Быстрый и безопасный браузер",
                category = "Инструменты",
                iconKey = "browser",
            ),
            AppDto(
                id = "mail",
                title = "Почта Mail.ru",
                subtitle = "Почтовый клиент для любых ящиков",
                category = "Инструменты",
                iconKey = "mail",
            ),
            AppDto(
                id = "navigator",
                title = "Яндекс Навигатор",
                subtitle = "Парковки и заправки — по пути",
                category = "Транспорт",
                iconKey = "navigator",
            ),
            AppDto(
                id = "mts",
                title = "Мой МТС",
                subtitle = "Мой МТС — центр экосистемы МТС",
                category = "Инструменты",
                iconKey = "mts",
            ),
            AppDto(
                id = "yandex",
                title = "Яндекс — с Алисой",
                subtitle = "Яндекс — поиск всегда под рукой",
                category = "Инструменты",
                iconKey = "yandex",
            ),
        )
    }
}