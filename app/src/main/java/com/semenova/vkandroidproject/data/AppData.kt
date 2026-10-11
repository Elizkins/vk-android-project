package com.semenova.vkandroidproject.data

import com.semenova.vkandroidproject.domain.AppCategory
import com.semenova.vkandroidproject.domain.AppItem

object AppData {
    private fun shots(seed: String) = List(3) {
        "https://picsum.photos/seed/$seed$it/1600/900"
    }
    
    val apps = listOf(
        AppItem(
            id = 1,
            name = "Telegram",
            developer = "Telegram FZ-LLC",
            category = AppCategory.COMM,
            ageRating = 12,
            size = 78.4f,
            iconUrl = "https://www.google.com/s2/favicons?domain=telegram.org&sz=128",
            screenshotUrlList = shots("telegram"),
            description = "Быстрый и безопасный мессенджер для общения без границ. Создавайте чаты, звоните друзьям и подписывайтесь на любимые каналы!"
        ),
        AppItem(
            id = 2,
            name = "Spotify",
            developer = "Spotify AB",
            category = AppCategory.FUN,
            ageRating = 12,
            size = 95.2f,
            iconUrl = "https://www.google.com/s2/favicons?domain=spotify.com&sz=128",
            screenshotUrlList = shots("spotify"),
            description = "Миллионы треков и подкастов в одном приложении. Собирайте плейлисты под настроение и слушайте музыку где угодно!"
        ),
        AppItem(
            id = 3,
            name = "Google Карты",
            developer = "Google LLC",
            category = AppCategory.TRAVEL,
            ageRating = 3,
            size = 121.6f,
            iconUrl = "https://www.google.com/s2/favicons?domain=maps.google.com&sz=128",
            screenshotUrlList = shots("maps"),
            description = "Подробные карты и точная навигация по всему миру. Стройте маршруты, находите интересные места и не теряйтесь в пути!"
        ),
        AppItem(
            id = 4,
            name = "Notion",
            developer = "Notion Labs, Inc.",
            category = AppCategory.WORK,
            ageRating = 3,
            size = 64.8f,
            iconUrl = "https://www.google.com/s2/favicons?domain=notion.so&sz=128",
            screenshotUrlList = shots("notion"),
            description = "Единое пространство для заметок, задач и проектов. Наведите порядок в делах и создайте собственную базу знаний!"
        ),
        AppItem(
            id = 5,
            name = "Instagram",
            developer = "Meta Platforms, Inc.",
            category = AppCategory.PHOTO,
            ageRating = 12,
            size = 58.3f,
            iconUrl = "https://www.google.com/s2/favicons?domain=instagram.com&sz=128",
            screenshotUrlList = shots("instagram"),
            description = "Соцсеть для ярких фото и коротких видео. Делитесь моментами жизни и следите за историями друзей и любимых авторов!"
        ),
        AppItem(
            id = 6,
            name = "Яндекс Go",
            developer = "Яндекс",
            category = AppCategory.TRAVEL,
            ageRating = 12,
            size = 142.0f,
            iconUrl = "https://www.google.com/s2/favicons?domain=go.yandex&sz=128",
            screenshotUrlList = shots("yandexgo"),
            description = "Такси, доставка и самокаты в одном приложении. Выбирайте удобный способ поездки и добирайтесь быстрее!"
        ),
        AppItem(
            id = 7,
            name = "YouTube",
            developer = "Google LLC",
            category = AppCategory.FUN,
            ageRating = 12,
            size = 86.5f,
            iconUrl = "https://www.google.com/s2/favicons?domain=youtube.com&sz=128",
            screenshotUrlList = shots("youtube"),
            description = "Крупнейший видеосервис с миллионами роликов и прямых трансляций. Смотрите, учитесь и открывайте новых авторов!"
        ),
        AppItem(
            id = 8,
            name = "Google Docs",
            developer = "Google LLC",
            category = AppCategory.WORK,
            ageRating = 3,
            size = 49.7f,
            iconUrl = "https://www.google.com/s2/favicons?domain=docs.google.com&sz=128",
            screenshotUrlList = shots("docs"),
            description = "Онлайн-редактор текстовых документов с совместным доступом. Пишите вместе с командой и не теряйте ни одной правки!"
        ),
        AppItem(
            id = 9,
            name = "Google Фото",
            developer = "Google LLC",
            category = AppCategory.PHOTO,
            ageRating = 3,
            size = 73.1f,
            iconUrl = "https://www.google.com/s2/favicons?domain=photos.google.com&sz=128",
            screenshotUrlList = shots("photos"),
            description = "Храните фото и видео в облаке и находите нужные кадры за секунды. Редактируйте снимки и делитесь альбомами с близкими!"
        ),
        AppItem(
            id = 10,
            name = "VK",
            developer = "VK",
            category = AppCategory.COMM,
            ageRating = 12,
            size = 134.5f,
            iconUrl = "https://www.google.com/s2/favicons?domain=vk.com&sz=128",
            screenshotUrlList = shots("vk"),
            description = "Социальная сеть для общения, музыки и видео. Переписывайтесь с друзьями, следите за новостями и находите интересные сообщества!"
        ),
        AppItem(
            id = 11,
            name = "Яндекс Музыка",
            developer = "Яндекс",
            category = AppCategory.FUN,
            ageRating = 12,
            size = 89.9f,
            iconUrl = "https://www.google.com/s2/favicons?domain=music.yandex.ru&sz=128",
            screenshotUrlList = shots("yandexmusic"),
            description = "Огромная музыкальная коллекция с умными подборками. Слушайте любимые треки, открывайте новых исполнителей и сохраняйте музыку офлайн!"
        ),
        AppItem(
            id = 12,
            name = "Яндекс Карты",
            developer = "Яндекс",
            category = AppCategory.TRAVEL,
            ageRating = 3,
            size = 112.3f,
            iconUrl = "https://www.google.com/s2/favicons?domain=yandex.ru/maps&sz=128",
            screenshotUrlList = shots("yandexmaps"),
            description = "Карты, навигация и общественный транспорт с учётом пробок. Находите организации, стройте маршруты и добирайтесь без задержек!"
        ),
        AppItem(
            id = 13,
            name = "Google Таблицы",
            developer = "Google LLC",
            category = AppCategory.WORK,
            ageRating = 3,
            size = 52.6f,
            iconUrl = "https://www.google.com/s2/favicons?domain=sheets.google.com&sz=128",
            screenshotUrlList = shots("sheets"),
            description = "Онлайн-таблицы для расчётов, отчётов и анализа данных. Работайте с командой в реальном времени и сохраняйте изменения автоматически!"
        ),
        AppItem(
            id = 14,
            name = "Snapseed",
            developer = "Google LLC",
            category = AppCategory.PHOTO,
            ageRating = 3,
            size = 38.2f,
            iconUrl = "https://www.google.com/s2/favicons?domain=snapseed.online&sz=128",
            screenshotUrlList = shots("snapseed"),
            description = "Мощный фоторедактор с десятками инструментов и фильтров. Улучшайте снимки, меняйте цвета и придавайте фото нужное настроение!"
        )
    )
    
    fun byId(id: Int): AppItem? = apps.firstOrNull { it.id == id }
}