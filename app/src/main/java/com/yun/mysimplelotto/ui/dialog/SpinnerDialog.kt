package com.yun.mysimplelotto.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yun.mysimplelotto.R
import com.yun.mysimplelotto.BR
import com.yun.mysimplelotto.base.BaseRecyclerAdapter
import com.yun.mysimplelotto.base.replace
import com.yun.mysimplelotto.data.model.SpinnerModel
import com.yun.mysimplelotto.databinding.DialogSpinnerBinding
import com.yun.mysimplelotto.databinding.ItemSpinnerBinding

class SpinnerDialog {
    lateinit var customDialogListener: CustomDialogListener

    fun showPopup(context: Context, item: ArrayList<SpinnerModel>) {
        AlertDialog.Builder(context).run {
            setCancelable(true)
            val view = View.inflate(context, R.layout.dialog_spinner, null)
            val binding = DialogSpinnerBinding.bind(view)
            binding.setVariable(BR.list, item)
            setView(binding.root)
            val dialog = create()


//            dialog.setOnDismissListener {
//                customDialogListener.onResultClicked(false)
//            }


            binding.rvSelect.run {
                adapter = object : BaseRecyclerAdapter.Create<SpinnerModel, ItemSpinnerBinding>(
                    R.layout.item_spinner,
                    BR.itemSpinner,
                    BR.spinnerItemListener
                ) {
                    override fun onItemClick(item: SpinnerModel, view: View) {
                        customDialogListener.onResultClicked(item.title)
                        dialog.dismiss()
                    }

                    override fun onItemLongClick(item: SpinnerModel, view: View): Boolean {
                        return true
                    }
                }
            }

            dialog
        }.show()
    }


    interface CustomDialogListener {
        fun onResultClicked(title: String)
    }

    fun setDialogListener(customDialogListener: CustomDialogListener) {
        this.customDialogListener = customDialogListener
    }


}