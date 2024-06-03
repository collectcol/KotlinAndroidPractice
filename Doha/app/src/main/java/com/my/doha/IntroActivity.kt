package com.my.doha

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import com.my.doha.base.BaseActivity

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        supportActionBar?.hide()

        val mainLogo = findViewById<ImageView>(R.id.intro_logo)

        applyFadeInOutAnimation(mainLogo)


    }

    private fun applyFadeInOutAnimation(imageView: ImageView) {
        // 애니메이션 설정
        imageView.visibility = View.VISIBLE

        // 나타나는 애니메이션
        val fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f).apply {
            duration = 1000 // 1초 동안
        }

        // 사라지는 애니메이션
        val fadeOut = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f).apply {
            duration = 1000 // 1초 동안
            startDelay = 1000 // 나타난 후 1초 동안 유지
        }

        // 애니메이션 세트
        AnimatorSet().apply {
            playSequentially(fadeIn, fadeOut)
            interpolator = AccelerateDecelerateInterpolator()
            start()

            addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator) {
                    finish()
                    startActivity(Intent(this@IntroActivity, LoginActivity::class.java))
                }
            })
        }
    }
}


