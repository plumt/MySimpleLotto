package com.yun.mysimplelotto.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yun.mysimplelotto.data.model.LottoDBModel
import io.reactivex.rxjava3.core.Observable

@Dao
abstract class LottoDao {

    @Insert
    abstract fun insertLotto(lottoDBModel: LottoDBModel)//: Observable<Long>

    @Query("SELECT * FROM LOTTO ORDER BY drwNoDate")
    abstract fun selectLotto(): List<LottoDBModel>

    @Query("SELECT * FROM LOTTO ORDER BY drwNoDate DESC LIMIT 1")
    abstract fun selectLastLotto(): LottoDBModel?

    @Query("SELECT * FROM LOTTO WHERE drwNoDate LIKE :date")
    abstract fun selectYearMonth(date: String): List<LottoDBModel>

//    @Query("SELECT * FROM CALENDAR WHERE DATE == :Dt")
//    abstract fun selectEvent(Dt: Long): List<CalendarModel>
//
//    @Query("SELECT * FROM CALENDAR WHERE DATE >= :sDt AND DATE <= :eDt ORDER BY DATE")
//    abstract fun selectMonth(sDt: Long, eDt: Long): List<CalendarModel>
//
//    @Query("DELETE FROM Calendar WHERE DATE == :dt AND EVENT == :event")
//    abstract fun deleteEvent(dt: Long, event: String)
//
//    @Insert
//    abstract fun insertEvent(calendarModel: CalendarModel): Long
}