package com.myapplication

import com.squareup.sqldelight.db.SqlDriver

expect object DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}