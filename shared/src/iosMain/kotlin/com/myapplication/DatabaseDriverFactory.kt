package com.myapplication

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.kodein.di.DI
import org.kodein.di.DIAware

actual object DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "white_chalk.db")
    }
}