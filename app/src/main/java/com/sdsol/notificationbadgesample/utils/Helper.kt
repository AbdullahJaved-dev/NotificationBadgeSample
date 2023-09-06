package com.sdsol.notificationbadgesample.utils

import android.content.Context
import android.content.Intent


fun Context.setBadge(count: Int) {
    val launcherClassName = this.getLauncherClassName() ?: return
    val intent = Intent("android.intent.action.BADGE_COUNT_UPDATE")
    intent.putExtra("badge_count", count)
    intent.putExtra("badge_count_package_name", this.packageName)
    intent.putExtra("badge_count_class_name", launcherClassName)
    this.sendBroadcast(intent)
}

fun Context.getLauncherClassName(): String? {
    val pm = this.packageManager
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_LAUNCHER)
    val resolveInfo = pm.queryIntentActivities(intent, 0)
    for (resolveInfo in resolveInfo) {
        val pkgName = resolveInfo.activityInfo.applicationInfo.packageName
        if (pkgName.equals(this.packageName, ignoreCase = true)) {
            return resolveInfo.activityInfo.name
        }
    }
    return null
}