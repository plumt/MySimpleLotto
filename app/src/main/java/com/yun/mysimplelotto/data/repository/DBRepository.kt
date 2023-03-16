package com.yun.mysimplelotto.data.repository

import com.yun.mysimplelotto.data.model.LottoDBModel
import com.yun.mysimplelotto.db.DB
import javax.inject.Inject

class DBRepository @Inject constructor(private val db: DB){
    fun insertLotto(lottoDBModel: LottoDBModel) = db.LottoDao().insertLotto(lottoDBModel)
    fun selectLastLotto() = db.LottoDao().selectLastLotto()
    fun selectYearMonth(date: String) = db.LottoDao().selectYearMonth("${date}-%")
    fun selectBestNumber6() = db.LottoDao().selectBestNumber6()
}