package com.cdr.Lab811

import android.content.Context
import androidx.room.Room
import com.cdr.Lab811.model.StatisticRepository
import com.cdr.Lab811.model.database.AppDatabase

object Dependencies {

    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context
    }

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
            .createFromAsset("room_article.db")
            .build()
    }

    val statisticRepository: StatisticRepository by lazy { StatisticRepository(appDatabase.getStatisticDao()) }
}