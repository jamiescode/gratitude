package com.jamiescode.showcase.navigation

sealed class Destinations(
    val route: String,
) {
    data object Gratitude : Destinations(Routes.GRATITUDE.value)

    data object Settings : Destinations(Routes.SETTINGS.value)

    data object OpenSourceLicenses : Destinations(Routes.OPEN_SOURCE_LICENSES.value)

    data object FeedbackForm : Destinations(Routes.FEEDBACK_FORM.value)

    data object BuyMeACoffee : Destinations(Routes.BUY_ME_A_COFFEE.value)

    data object UnderConstruction : Destinations(Routes.UNDER_CONSTRUCTION.value)

    data object Nowhere : Destinations("")
}
