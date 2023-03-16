package com.yun.mysimplelotto.ui.best

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yun.mysimplelotto.data.repository.DBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BestNumberViewModel @Inject constructor(
    private val db: DBRepository
) : ViewModel(){

    val bestNumber = MutableLiveData("")

    init {
        selectBestNumber()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun selectBestNumber() {
        GlobalScope.launch(Dispatchers.IO) {
            val a = db.selectBestNumber6()
            if(a.size == 6){
                var result = ""
                a.forEachIndexed { index, data ->
                    if(index != 0) result += "\n"
                    result += "${data.num}(${data.count}번)"
                }
                bestNumber.postValue(result)

            }
        }
    }

}