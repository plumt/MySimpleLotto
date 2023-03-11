package com.yun.mysimplelotto.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yun.mysimplelotto.base.ListLiveData
import com.yun.mysimplelotto.data.model.LottoModel
import com.yun.mysimplelotto.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: ApiRepository
) : ViewModel(){

    val lottoIndex = MutableLiveData(1)

    val lottoList = ListLiveData<LottoModel.RS>()

    val isLoading = MutableLiveData(false)

    @SuppressLint("CheckResult")
    fun callApi(context: Context){
        isLoading.value = true
        api.searchLotto(lottoIndex.value!!).observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
            .flatMap { Observable.just(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Log.d("lys","result : $it")
                when(it.returnValue){
                    "success" -> {
                     lottoList.add(it.apply {
                         id = lottoList.value!!.size
                     })
                        lottoIndex.value = lottoIndex.value!! + 1
                    }
                    "fail" -> {
                        isLoading.value = false
                        Toast.makeText(context, "가져오기 완료", Toast.LENGTH_LONG).show()
                    }
                }
            }.subscribe({
                Log.d("lys","success")
            },{
                isLoading.value = false
                Log.d("lys","fail > ${it.message}")
            })
    }
}