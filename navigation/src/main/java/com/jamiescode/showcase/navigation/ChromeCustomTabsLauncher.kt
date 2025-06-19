package com.jamiescode.showcase.navigation

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

fun Context.launchCustomTabs(url: String) {
    val intent = CustomTabsIntent.Builder().build()
    intent.launchUrl(this, url.toUri())
}
