package com.yun.mysimplelotto.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yun.mysimplelotto.data.model.SpinnerModel
import com.yun.mysimplelotto.data.repository.DBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val db: DBRepository
) : ViewModel(){

    val yearTitle = MutableLiveData("")
    val monthTitle = MutableLiveData("")

    val nowYear = LocalDate.now().year
    val nowMonth = LocalDate.now().monthValue

    val yearList = ArrayList<SpinnerModel>()
    val monthList = ArrayList<SpinnerModel>()


    init {

        for(i in 2002 .. nowYear){
            yearList.add(SpinnerModel(i, i.toString()))
        }
        monthSetting(nowMonth)

        yearTitle.value =  String.format("%02d",nowYear)
        monthTitle.value = String.format("%02d",nowMonth)
        searchDB(yearTitle.value!!, monthTitle.value!!)
    }

    fun monthSetting(lastMonth: Int){
        monthList.clear()
        for(i in 1 .. lastMonth){
            monthList.add(SpinnerModel(i,i.toString()))
        }
    }

    fun searchDB(year: String, month: String){
        GlobalScope.launch(Dispatchers.IO) {
            val a = db.selectYearMonth("$year-$month")
            if (a == null) {
                Log.d("lys", "selectLastLotto is null")

            } else {
                a.forEach {
                    Log.d("lys","result : ${it.drwNoDate}")

                }

            }
        }
    }
}