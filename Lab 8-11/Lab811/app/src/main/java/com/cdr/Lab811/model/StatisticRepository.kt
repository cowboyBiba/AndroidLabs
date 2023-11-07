package com.cdr.Lab811.model

import com.cdr.Lab811.model.database.StatisticDao
import com.cdr.Lab811.model.database.StatisticInfoTuple
import com.cdr.Lab811.model.database.entities.StatisticDbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatisticRepository(private val statisticDao: StatisticDao) {

    suspend fun insertNewStatisticData(statisticDbEntity: StatisticDbEntity) {
        withContext(Dispatchers.IO) {
            statisticDao.insertNewStatisticData(statisticDbEntity)
        }
    }

    suspend fun getAllStatisticData(): List<StatisticInfoTuple> {
        return withContext(Dispatchers.IO) {
            return@withContext statisticDao.getAllStatisticData()
        }
    }

    suspend fun removeStatisticDataById(id: Long) {
        withContext(Dispatchers.IO) {
            statisticDao.deleteStatisticDataById(id)
        }
    }
}