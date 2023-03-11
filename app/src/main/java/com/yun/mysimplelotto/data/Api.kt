package com.yun.mysimplelotto.data

import com.yun.mysimplelotto.data.model.LottoModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/common.do")
    fun searchLotto(
        @Query("method") method: String = "getLottoNumber",
        @Query("drwNo") drwNo: Int
    ) : Observable<LottoModel.RS>
}