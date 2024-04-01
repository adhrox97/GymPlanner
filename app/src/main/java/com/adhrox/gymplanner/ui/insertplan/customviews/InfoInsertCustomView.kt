package com.adhrox.gymplanner.ui.insertplan.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.adhrox.gymplanner.R
import com.adhrox.gymplanner.databinding.CustomViewInsertInfoBinding

class InfoInsertCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: CustomViewInsertInfoBinding

    init {
        binding = CustomViewInsertInfoBinding.inflate(LayoutInflater.from(context), this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.InfoInsertCustomView)
        val imageSrc = attributes.getResourceId(
            R.styleable.InfoInsertCustomView_imageSrc,
            R.drawable.place_holder
        )
        val customText = attributes.getString(R.styleable.InfoInsertCustomView_customText)
        val customTextInfo = attributes.getString(R.styleable.InfoInsertCustomView_customTextInfo)
        val viewType = attributes.getInt(R.styleable.InfoInsertCustomView_viewType, 0)
        attributes.recycle()

        binding.customImage.setImageResource(imageSrc)
        binding.customTextView.text = customText
        binding.customTextInfoView.text = customTextInfo

        if (viewType == 0) {
            binding.customEditText.visibility = View.VISIBLE
            binding.customSpinner.visibility = View.GONE
        } else {
            binding.customEditText.visibility = View.GONE
            binding.customSpinner.visibility = View.VISIBLE
        }
    }

    val editText = binding.customEditText
    val spinner = binding.customSpinner
    val editTextInfo = binding.customTextInfoView


}