package com.yun.mysimplelotto.data.model

import com.yun.mysimplelotto.base.Item

data class SpinnerModel(
    override var id: Int,
    val title: String
) : Item() {
    fun titleConvert(_title: String) = if(_title.length >= 4) "${_title}년" else "${_title}월"
}