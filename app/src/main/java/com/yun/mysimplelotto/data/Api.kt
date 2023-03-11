package com.yun.mysimplelotto.data

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/common.do")
    fun searchLotto(
        @Query("method") method: String = "getLottoNumber",
        @Query("drwNo") drwNo: Int
    ) : Observable<Any>
}