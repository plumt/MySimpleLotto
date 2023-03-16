package com.yun.mysimplelotto.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Lotto")
class LottoDBModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "seq") val seq: Long = 0,
    @ColumnInfo(name = "drwNoDate") val drwNoDate: String,
    @ColumnInfo(name = "drwtNo1") val drwtNo1: String,
    @ColumnInfo(name = "drwtNo2") val drwtNo2: String,
    @ColumnInfo(name = "drwtNo3") val drwtNo3: String,
    @ColumnInfo(name = "drwtNo4") val drwtNo4: String,
    @ColumnInfo(name = "drwtNo5") val drwtNo5: String,
    @ColumnInfo(name = "drwtNo6") val drwtNo6: String,
    @ColumnInfo(name = "bnusNo") val bnusNo: String,
    @ColumnInfo(name = "firstWinamnt") val firstWinamnt: String,
    @ColumnInfo(name = "firstPrzwnerCo") val firstPrzwnerCo: String,
    @ColumnInfo(name = "firstAccumamnt") val firstAccumamnt: String,
    @ColumnInfo(name = "drwNo") val drwNo: String,
    @ColumnInfo(name = "drwtNo") val drwtNo: String
)