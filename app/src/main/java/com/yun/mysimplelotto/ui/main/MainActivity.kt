package com.yun.mysimplelotto.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yun.mysimplelotto.R
import com.yun.mysimplelotto.databinding.ActivityMainBinding
import com.yun.mysimplelotto.ui.dialog.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var dialog: LoadingDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.run {
            lifecycleOwner = this@MainActivity
            main = mainViewModel
        }
        dialog = LoadingDialog(this)
        mainViewModel.isLoading.observe(this){
            if(it) dialog.show()
            else dialog.dismiss()
        }

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

    }
}