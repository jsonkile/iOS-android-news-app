package com.myapplication

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual object DatabaseDriverFactory {
    private var applicationContext: Context? = null
    private val context get() = applicationContext ?: error("Android context has not been set")

    fun setContext(c: Context) {
        applicationContext = c
    }

    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "white_chalk.db")
    }
}