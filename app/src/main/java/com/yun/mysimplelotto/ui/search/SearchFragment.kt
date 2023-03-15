package com.yun.mysimplelotto.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.yun.mysimplelotto.R
import com.yun.mysimplelotto.BR
import com.yun.mysimplelotto.base.BaseFragment
import com.yun.mysimplelotto.data.model.SpinnerModel
import com.yun.mysimplelotto.databinding.FragmentSearchBinding
import com.yun.mysimplelotto.ui.dialog.SpinnerDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(){
    override val viewModel: SearchViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_search
    override fun isOnBackEvent(): Boolean = true
    override fun setVariable(): Int = BR.search
    override fun onBackEvent() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvYear.setOnClickListener {
            showDialog(0)
        }

        binding.cvMonth.setOnClickListener {
            showDialog(1)
        }

        viewModel.yearTitle.observe(viewLifecycleOwner){
            if(it == String.format("%02d",viewModel.nowYear)){
                viewModel.monthSetting(viewModel.nowMonth)
            } else {
                viewModel.monthSetting(12)
            }
        }

    }

    private fun showDialog(index: Int){
        SpinnerDialog().run {
            showPopup(requireContext(), if(index==0) viewModel.yearList.reversed() as ArrayList<SpinnerModel> else viewModel.monthList)
            setDialogListener(object : SpinnerDialog.CustomDialogListener{
                override fun onResultClicked(title: String) {
                    if(index == 0){
                        viewModel.yearTitle.value = title
                        if(title == String.format("%02d",viewModel.nowYear)){
                            viewModel.monthTitle.value = String.format("%02d",viewModel.nowMonth)
                        }
                    } else {
                        viewModel.monthTitle.value = title
                    }
                    viewModel.searchDB(viewModel.yearTitle.value!!, viewModel.monthTitle.value!!)
                }
            })
        }
    }
}