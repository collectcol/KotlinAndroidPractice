package com.my.doha

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import com.google.firebase.auth.FirebaseUser
import com.my.doha.base.BaseActivity
import com.my.doha.data.UserData
import com.my.doha.database.AppDatabase
import com.my.doha.database.DatabaseProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IntroActivity : BaseActivity() {
    private lateinit var mDohaAppContext: DohaApp
    private val LOGIN = 0
    private val MAIN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

//        supportActionBar?.hide()

        mDohaAppContext = applicationContext as DohaApp

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

            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mDohaAppContext.mAuth.currentUser?.let { user ->
                        loadUserData(user)
                    } ?: run {
                        // 로그인 화면으로 이동
                        movePage(LOGIN)
                    }
                }
            })
        }
    }

    private fun loadUserData(user: FirebaseUser) {
//        val db = DatabaseProvider.getDatabase(mDohaAppContext)

        CoroutineScope(Dispatchers.IO).launch {
            val userData = mDohaAppContext.mDB.userDataDao().getUserDataById(user.uid)
            if (userData != null) {
                withContext(Dispatchers.Main) {
                    movePage(MAIN)
                }
            } else {
                mDohaAppContext.mFirestore.collection("users").document(user.uid)
                    .get()
                    .addOnSuccessListener { document ->
                        document?.toObject(UserData::class.java)?.let { userData ->
                            CoroutineScope(Dispatchers.IO).launch {
                                mDohaAppContext.mDB.userDataDao().insertUserData(userData)
                                withContext(Dispatchers.Main) {
                                    movePage(MAIN)
                                }
                            }
                        } ?: run {
                            movePage(LOGIN)
                        }
                    }
                    .addOnFailureListener { e ->
                        movePage(LOGIN)
                    }
            }
        }
    }

    private fun movePage(flag: Int) {
        if (flag == LOGIN) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }
}


