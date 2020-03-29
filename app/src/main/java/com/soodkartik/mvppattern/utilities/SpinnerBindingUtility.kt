package com.soodkartik.mvppattern.utilities

import android.content.Context
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.*
import com.soodkartik.mvppattern.BR
import com.soodkartik.mvppattern.R


object SpinnerBindingUtility : BaseObservable() {

    var selectedSpinnerValue: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.selectedSpinnerValue)
        }


    fun setSpinnerLayout(
        context: Context,
        textArrayResId: Int,
        appCompatSpinner: AppCompatSpinner?
    ) {
        val adapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            context,
            textArrayResId, R.layout.spinner_item_layout
        )
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
        appCompatSpinner?.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter(value = ["selectedValue", "selectedValueAttrChanged"], requireAll = false)
    fun setSpinner(
        spinner: AppCompatSpinner,
        selectedOpt: String?,
        changeListener: InverseBindingListener?
    ) {
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                i: Int,
                l: Long
            ) {
                changeListener?.onChange()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                changeListener?.onChange()
            }
        }
        if (selectedOpt != null) {
            spinner.setSelection(getIndexOfItem(spinner, selectedOpt))
        }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    fun getSpinnerValueAsString(spinner: AppCompatSpinner): String? {
        return spinner.selectedItem as String
    }

    private fun getIndexOfItem(spinner: AppCompatSpinner, item: String): Int {
        val adapter: Adapter = spinner.adapter
        for (i in 0 until adapter.count) {
            if (adapter.getItem(i) == item) {
                return i
            }
        }
        return 0
    }
}