package com.yun.mysimplelotto.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.yun.mysimplelotto.R
import com.yun.mysimplelotto.BR
import com.yun.mysimplelotto.base.BaseFragment
import com.yun.mysimplelotto.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_home
    override fun setVariable(): Int = BR.home
    override fun isOnBackEvent(): Boolean = true
    override fun onBackEvent() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSearch.setOnClickListener {
            navigate(R.id.action_homeFragment_to_searchFragment)
        }

        viewModel.lottoIndex.observe(viewLifecycleOwner) {
            if (it > 0) {
                viewModel.callApi(requireContext())
            } else {
                viewModel.selectLastLotto()
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            sharedViewModel.isLoading.value = it
        }

    }
}