package com.shajib.blureffectinimage

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.shajib.blureffectinimage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var ivImage: ImageView
    private val imageUrl =
        "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg"

    private lateinit var blurTransformationUsingGlide: BlurTransformationUsingGlide
    private lateinit var blurTransformationUsingHookBlurLib: BlurTransformationUsingHookBlurLib
    private lateinit var blurTransformationUsingCanvasAndPaint: BlurTransformationUsingCanvasAndPaint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ivImage = binding.ivImage
        blurTransformationUsingGlide = BlurTransformationUsingGlide(this, ivImage, imageUrl)
        blurTransformationUsingHookBlurLib = BlurTransformationUsingHookBlurLib(
            this,
            ivImage,
            imageUrl,
            30,
            1.0f
        )
        blurTransformationUsingCanvasAndPaint = BlurTransformationUsingCanvasAndPaint(
            this,
            ivImage,
            imageUrl,
            25f
        )

        showMainImage()
        showBlurImageUsingHookBlurLibrary()
        showBlurImageUsingGlide()
        showBlurImageUsingCanvasPaint()
    }

    private fun showMainImage() {
        binding.btnMainImage.setOnClickListener {
            blurTransformationUsingGlide.initGlide()
        }
        blurTransformationUsingGlide.initGlide()
    }

    private fun showBlurImageUsingGlide() {
        binding.btnBlurImageUsingGlide.setOnClickListener {
            blurTransformationUsingGlide.initBlurImage()
        }
    }

    private fun showBlurImageUsingHookBlurLibrary() {
        binding.btnBlurImageUsingHookBlur.setOnClickListener {
            blurTransformationUsingHookBlurLib.initHookBlur()
        }
    }

    private fun showBlurImageUsingCanvasPaint() {
        binding.btnBlurImageUsingCanvasPaint.setOnClickListener {
            blurTransformationUsingCanvasAndPaint.showBlurImage()
        }
    }
}
