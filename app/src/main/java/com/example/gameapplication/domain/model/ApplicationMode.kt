package com.example.gameapplication.domain.model

sealed class ApplicationMode {
    object Online : ApplicationMode()
    object Offline : ApplicationMode()
    object Undefined : ApplicationMode()

    companion object {
        const val ONLINE_MODE = "online"
        const val OFFLINE_MODE = "offline"
        const val UNDEFINED_MODE = "undefined"
    }
}

fun String?.getApplicationModeFromString(): ApplicationMode {
    return when (this) {
        ApplicationMode.ONLINE_MODE -> ApplicationMode.Online
        ApplicationMode.OFFLINE_MODE -> ApplicationMode.Offline
        else -> ApplicationMode.Undefined
    }
}

fun ApplicationMode.getStringFromApplicationMode(): String {
    return when (this) {
        ApplicationMode.Online -> ApplicationMode.ONLINE_MODE
        ApplicationMode.Offline -> ApplicationMode.OFFLINE_MODE
        ApplicationMode.Undefined -> ApplicationMode.UNDEFINED_MODE
    }
}