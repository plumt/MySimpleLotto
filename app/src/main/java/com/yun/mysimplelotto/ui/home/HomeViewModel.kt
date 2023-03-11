package com.yun.mysimplelotto.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
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

    init {
        callApi()
    }

    @SuppressLint("CheckResult")
    fun callApi(){
        api.searchLotto(1).observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
            .flatMap { Observable.just(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Log.d("lys","result : $it")
            }.subscribe({
                Log.d("lys","success")
            },{
                Log.d("lys","fail > ${it.message}")
            })
    }
}