package com.semenova.vkandroidproject.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class AppCategoryDto(val description: String) {
    @SerialName("Общение")
    COMMUNICATION("Общение"),
    @SerialName("Игры")
    GAMES("Игры"),
    @SerialName("Развлечения")
    ENTERTAINMENT("Развлечения"),
    @SerialName("Путешествия")
    TRAVEL("Путешествия"),
    @SerialName("Производительность")
    PRODUCTIVITY("Продуктивность"),
    @SerialName("Фото и видео")
    PHOTO_VIDEO("Фото и видео"),
    @SerialName("Здоровье и фитнес")
    HEALTH_FITNESS("Здоровье и фитнес"),
    @SerialName("Еда и напитки")
    FOOD_DRINK("Еда и напитки"),
    @SerialName("Образование")
    EDUCATION("Образование"),
    @SerialName("Образ жизни")
    LIFESTYLE("Образ жизни"),
    @SerialName("Шопинг")
    SHOPPING("Шопинг"),
    @SerialName("Новости")
    NEWS("Новости"),
    @SerialName("Музыка")
    MUSIC("Музыка"),
    @SerialName("Финансы")
    FINANCE("Финансы"),
    @SerialName("Утилиты")
    UTILITIES("Утилиты"),
    @SerialName("Навигация")
    NAVIGATION("Навигация"),
    @SerialName("Бизнес")
    BUSINESS("Бизнес"),
    @SerialName("Погода")
    WEATHER("Погода"),
    @SerialName("Книги и справочники")
    BOOKS("Книги и справочники"),
    UNKNOWN("Неизвестно")
}