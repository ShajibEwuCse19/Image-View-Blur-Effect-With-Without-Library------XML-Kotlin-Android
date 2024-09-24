package com.shajib.blureffectinimage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.hoko.blur.HokoBlur

/**
 * @author Shajib
 * @since Sept 23, 2024
 **/
class BlurTransformationUsingHookBlurLib(
    private val context: Context,
    private val ivImage: ImageView,
    private val imageUrl: String,
    private val radius: Int,
    private val sampleFactor: Float
) {
    fun initHookBlur() {
        Glide.with(context)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    val blurredBitmap = HokoBlur.with(context)
                        .scheme(HokoBlur.SCHEME_NATIVE)
                        .mode(HokoBlur.MODE_STACK)
                        .radius(radius)
                        .sampleFactor(sampleFactor)
                        .forceCopy(true)
                        .processor()
                        .blur(resource)

                    ivImage.setImageBitmap(blurredBitmap)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Handle any cleanup if necessary
                }
            })

    }
}