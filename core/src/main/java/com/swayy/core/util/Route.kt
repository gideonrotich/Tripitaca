package com.swayy.core.util

class Route {
    companion object {
        const val HOME = "home"
        const val LOGIN = "login"
        const val SPLASH = "splash"
        const val SETTINGS = "settings/?fromGame={fromGame}"
        const val LISTING_DETAIL = "listing/{listing}/{name}"
    }
}