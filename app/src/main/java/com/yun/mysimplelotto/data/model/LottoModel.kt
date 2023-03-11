package com.yun.mysimplelotto.data.model

import com.yun.mysimplelotto.base.Item

class LottoModel {
    data class RS(
        override var id: Int,
        val returnValue: String,
        val drwNoDate: String?,
        val drwtNo1: Double,
        val drwtNo2: Double,
        val drwtNo3: Double,
        val drwtNo4: Double,
        val drwtNo5: Double,
        val drwtNo6: Double,
        val bnusNo: Double
    ): Item()
}