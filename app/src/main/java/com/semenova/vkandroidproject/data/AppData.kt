package com.semenova.vkandroidproject.data

import com.semenova.vkandroidproject.domain.AppCategory
import com.semenova.vkandroidproject.domain.AppItem

object AppData {
    val apps = listOf(
        AppItem(
            name = "Telegram",
            description = "Быстрый и безопасный мессенджер для общения без границ. Создавайте чаты, звоните друзьям и подписывайтесь на любимые каналы!",
            category = AppCategory.COMM,
            icon = "https://www.google.com/s2/favicons?domain=telegram.org&sz=128"
        ),
        AppItem(
            name = "Spotify",
            description = "Миллионы треков и подкастов в одном приложении. Собирайте плейлисты под настроение и слушайте музыку где угодно!",
            category = AppCategory.FUN,
            icon = "https://www.google.com/s2/favicons?domain=spotify.com&sz=128"
        ),
        AppItem(
            name = "Google Карты",
            description = "Подробные карты и точная навигация по всему миру. Стройте маршруты, находите интересные места и не теряйтесь в пути!",
            category = AppCategory.TRAVEL,
            icon = "https://www.google.com/s2/favicons?domain=maps.google.com&sz=128"
        ),
        AppItem(
            name = "Notion",
            description = "Единое пространство для заметок, задач и проектов. Наведите порядок в делах и создайте собственную базу знаний!",
            category = AppCategory.WORK,
            icon = "https://www.google.com/s2/favicons?domain=notion.so&sz=128"
        ),
        AppItem(
            name = "Instagram",
            description = "Соцсеть для ярких фото и коротких видео. Делитесь моментами жизни и следите за историями друзей и любимых авторов!",
            category = AppCategory.PHOTO,
            icon = "https://www.google.com/s2/favicons?domain=instagram.com&sz=128"
        ),
        AppItem(
            name = "Яндекс Go",
            description = "Такси, доставка и самокаты в одном приложении. Выбирайте удобный способ поездки и добирайтесь быстрее!",
            category = AppCategory.TRAVEL,
            icon = "https://www.google.com/s2/favicons?domain=go.yandex&sz=128"
        ),
        AppItem(
            name = "YouTube",
            description = "Крупнейший видеосервис с миллионами роликов и прямых трансляций. Смотрите, учитесь и открывайте новых авторов!",
            category = AppCategory.FUN,
            icon = "https://www.google.com/s2/favicons?domain=youtube.com&sz=128"
        ),
        AppItem(
            name = "Google Docs",
            description = "Онлайн-редактор текстовых документов с совместным доступом. Пишите вместе с командой и не теряйте ни одной правки!",
            category = AppCategory.WORK,
            icon = "https://www.google.com/s2/favicons?domain=docs.google.com&sz=128"
        )
    )
}