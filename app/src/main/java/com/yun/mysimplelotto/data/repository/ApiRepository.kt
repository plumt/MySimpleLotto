package com.yun.mysimplelotto.data.repository

import com.yun.mysimplelotto.data.Api
import javax.inject.Inject

class ApiRepository @Inject constructor(private val api: Api) {

    fun searchLotto(drwNo: Int) = api.searchLotto(drwNo = drwNo)

}