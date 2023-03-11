package com.yun.mysimplelotto.data.model

import com.yun.mysimplelotto.base.Item

class LottoModel {

    /**
     * @param id
     * @param returnValue 성공 / 실패
     * @param drwNoDate 추첨일
     * @param drwtNo1 당첨번호 1번
     * @param drwtNo2 당첨번호 2번
     * @param drwtNo3 당첨번호 3번
     * @param drwtNo4 당첨번호 4번
     * @param drwtNo5 당첨번호 5번
     * @param drwtNo6 당첨번호 6번
     * @param bnusNo 보너스 당참번호
     * @param firstWinamnt 1등 당첨액
     * @param firstPrzwnerCo 1등 담청 인원 수
     * @param firstAccumamnt 총 금액
     */
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
        val bnusNo: Double,
        val firstWinamnt: String,
        val firstPrzwnerCo: Int,
        val firstAccumamnt: String
    ) : Item()
}