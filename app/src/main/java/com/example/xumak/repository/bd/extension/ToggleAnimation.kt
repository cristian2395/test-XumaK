package com.example.xumak.repository.bd.extension

import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.ToggleButton


fun ToggleButton.heartEffect() {
    val scaleAnimation = ScaleAnimation(
        0.7f,
        1.0f,
        0.7f,
        1.0f,
        Animation.RELATIVE_TO_SELF,
        0.7f,
        Animation.RELATIVE_TO_SELF,
        0.7f
    )

    scaleAnimation.duration = 500;
    val bounceInterpolator = BounceInterpolator()
    scaleAnimation.interpolator = bounceInterpolator

    this.setOnCheckedChangeListener { buttonView, isChecked ->
        buttonView.startAnimation(scaleAnimation)
    }
}