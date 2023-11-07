package com.cdr.Lab811.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cdr.Lab811.model.database.entities.DifficultyLevelsDbEntity
import com.cdr.Lab811.model.database.entities.ResultsDbEntity
import com.cdr.Lab811.model.database.entities.StatisticDbEntity

@Database(
    version = 1,
    entities = [
        DifficultyLevelsDbEntity::class,
        ResultsDbEntity::class,
        StatisticDbEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getStatisticDao(): StatisticDao

}