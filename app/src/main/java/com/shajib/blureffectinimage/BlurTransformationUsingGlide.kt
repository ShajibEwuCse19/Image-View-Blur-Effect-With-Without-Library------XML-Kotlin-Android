package com.shajib.blureffectinimage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class BlurTransformationUsingGlide(
    private val context: Context,
    private val ivImage: ImageView,
    private val imageUrl: String
) {

    fun initGlide() {
        Glide.with(context)
            .load(imageUrl)
            .into(ivImage)
    }

    fun initBlurImage() {
        Glide.with(context)
            .asBitmap()
            .load(imageUrl)
            /*.diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)*/
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val v = BlurTransformation(context, 25f).transform(
                        Glide.get(context).bitmapPool,
                        resource,
                        resource.width,
                        resource.height
                    )

                    ivImage.setImageBitmap(v)

                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }
}