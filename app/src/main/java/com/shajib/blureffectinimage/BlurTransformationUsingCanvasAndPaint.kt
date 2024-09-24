package com.shajib.blureffectinimage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

/**
 * @author Shajib
 * @since Sept 23, 2024
 **/
class BlurTransformationUsingCanvasAndPaint(
    private val context: Context,
    private val ivImage: ImageView,
    private val imageUrl: String,
    private val radius: Float
) {
     fun showBlurImage() {
        Glide.with(context)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val downscaledBitmap = Bitmap.createScaledBitmap(resource, resource.width / 4, resource.height / 4, true)
                    val blurredBitmap = blurBitmap(downscaledBitmap, radius)  // 25f is the maximum radius
                    ivImage.setImageBitmap(blurredBitmap)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Handle cleanup
                }
            })
    }

    private fun blurBitmap(original: Bitmap, radius: Float): Bitmap {
        val width = original.width
        val height = original.height
        val outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(outputBitmap)
        val paint = Paint()

        // Apply the BlurMaskFilter
        paint.maskFilter = BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL)
        canvas.drawBitmap(original, 0f, 0f, paint)

        // Return the blurred bitmap, which was scaled down
        return Bitmap.createScaledBitmap(outputBitmap, width * 4, height * 4, true)
    }
}