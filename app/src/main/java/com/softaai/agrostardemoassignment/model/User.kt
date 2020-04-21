package com.softaai.agrostardemoassignment.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

data class User(var imgUrl: String, var name: String, var address: String) {

    companion object {
        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, profileImage1: String) {
            Glide.with(view.context)
                .load(profileImage1)
                .apply(RequestOptions.circleCropTransform())
                .into(view)
        }
    }
}