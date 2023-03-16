package com.yun.mysimplelotto.ui.best

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.yun.mysimplelotto.R
import com.yun.mysimplelotto.BR
import com.yun.mysimplelotto.base.BaseFragment
import com.yun.mysimplelotto.databinding.FragmentBestNumberBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BestNumberFragment : BaseFragment<FragmentBestNumberBinding, BestNumberViewModel>(){
    override val viewModel: BestNumberViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_best_number
    override fun isOnBackEvent(): Boolean = false
    override fun setVariable(): Int = BR.best
    override fun onBackEvent() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}