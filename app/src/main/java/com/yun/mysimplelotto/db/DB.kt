package com.yun.mysimplelotto.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yun.mysimplelotto.data.model.LottoDBModel
import com.yun.mysimplelotto.db.dao.LottoDao

@Database(entities = [LottoDBModel::class], version = 1, exportSchema = true)
abstract class DB : RoomDatabase() {
    abstract fun LottoDao(): LottoDao
}