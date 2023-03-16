package com.yun.mysimplelotto.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yun.mysimplelotto.base.ListLiveData
import com.yun.mysimplelotto.data.model.LottoDBModel
import com.yun.mysimplelotto.data.model.LottoModel
import com.yun.mysimplelotto.data.repository.ApiRepository
import com.yun.mysimplelotto.data.repository.DBRepository
import com.yun.mysimplelotto.db.DB
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.annotation.Nullable
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: ApiRepository,
    private val db: DBRepository
) : ViewModel() {

    val lottoIndex = MutableLiveData(0)

    val lottoList = ListLiveData<LottoModel.RS>()

    val isLoading = MutableLiveData(false)

    val temp = arrayListOf<LottoModel.RS>()

    @SuppressLint("CheckResult")
    fun callApi(context: Context) {
        isLoading.value = true
        api.searchLotto(lottoIndex.value!!).observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
            .flatMap { Observable.just(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Log.d("lys", "result : $it")
                when (it.returnValue) {
                    "success" -> {
                        temp.add(it.apply {
                            id = lottoList.value!!.size
                        })
                        lottoList.add(it.apply {
                            id = lottoList.value!!.size
                        })

                        lottoIndex.value = lottoIndex.value!! + 1
                    }
                    "fail" -> {

                        insertDB()
                        Toast.makeText(context, "가져오기 완료", Toast.LENGTH_LONG).show()
                    }
                }
            }.subscribe({
                Log.d("lys", "success")
            }, {
                isLoading.value = false
                Log.d("lys", "fail > ${it.message}")
            })
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun selectLastLotto() {
        GlobalScope.launch(Dispatchers.IO) {
            val a = db.selectLastLotto()
            if (a == null) {
                Log.d("lys", "selectLastLotto is null")
                lottoIndex.postValue(1)
            } else {
                a.run {
                    lottoIndex.postValue(drwNo.toInt() + 1)
                    Log.d("lys", "drwNo : $drwNo")
                }

            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun insertDB() {
        GlobalScope.launch(Dispatchers.IO) {
            temp.forEach {
                val lotto = lottoDBModel(it)
                db.insertLotto(lotto)
            }
            isLoading.postValue(false)
        }
    }

    private fun lottoDBModel(lottoModel: LottoModel.RS): LottoDBModel =
        LottoDBModel(
            drwNoDate = lottoModel.drwNoDate!!,
            drwtNo1 = lottoModel.drwtNo1.toString(),
            drwtNo2 = lottoModel.drwtNo2.toString(),
            drwtNo3 = lottoModel.drwtNo3.toString(),
            drwtNo4 = lottoModel.drwtNo4.toString(),
            drwtNo5 = lottoModel.drwtNo5.toString(),
            drwtNo6 = lottoModel.drwtNo6.toString(),
            bnusNo = lottoModel.bnusNo.toString(),
            firstWinamnt = lottoModel.firstWinamnt!!,
            firstPrzwnerCo = lottoModel.firstPrzwnerCo.toString(),
            firstAccumamnt = lottoModel.firstAccumamnt!!,
            drwNo = lottoModel.drwNo,
            drwtNo = sorted(lottoModel.drwtNo1!!,lottoModel.drwtNo2!!,lottoModel.drwtNo3!!,lottoModel.drwtNo4!!,lottoModel.drwtNo5!!,lottoModel.drwtNo6!!)
        )

    private fun sorted(vararg numbers: Double) : String{
        val sort = numbers.sorted()
        var result = ""
        sort.forEachIndexed { index, d ->
            if(index != 0) result += ","
            result += "${d.toInt()}"
        }
        return result
    }
}