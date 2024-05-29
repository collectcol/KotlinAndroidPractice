package com.my.doha

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var edit_input_email: EditText
    private lateinit var edit_input_password: EditText
    private lateinit var btn_login: Button
    private lateinit var btn_google_login: SignInButton
    private lateinit var gso: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        edit_input_email = findViewById(R.id.edt_input_email)
        edit_input_password = findViewById(R.id.edt_input_password)
        btn_login = findViewById<Button?>(R.id.btn_login).apply {
            setOnClickListener {
                sinUp()
            }
        }


        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.gcm_defaultSenderId))
            .requestEmail()
            .build()
        var googleSignInClient = GoogleSignIn.getClient(this, gso)

        btn_google_login = findViewById<SignInButton>(R.id.btn_google_login).apply {
            setOnClickListener {
                googleLogin(googleSignInClient)
            }
        }
    }

    private fun sinUp() {
        auth?.createUserWithEmailAndPassword(
            edit_input_email.text.toString(),
            edit_input_password.text.toString()
        )
            ?.addOnCompleteListener {//통신 완료가 된 후 무슨일을 할지
                if (it.isSuccessful) {
                    //정상적으로 이메일과 비번이 전달되어
                    //새 유저 계정을 생성과 서버db 저장 완료 및 로그인
                    //즉, 기존에 있는 계정이 아니다!
                    finish()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else if (!it.exception?.message.isNullOrEmpty()) {
                    //예외메세지가 있다면 출력
                    //에러가 났다거나 서버가 연결이 실패했다거나
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_LONG).show()
                } else {
                    //여기가 실행되는 경우는 이미 db에 해당 이메일과 패스워드가 있는 경우
                    //그래서 계정 생성이 아닌 로그인 함수로 이동
                    signIn()
                }
            }
    }

    private fun signIn() {
        auth?.signInWithEmailAndPassword(
            edit_input_email.text.toString(),
            edit_input_password.text.toString()
        )
            ?.addOnCompleteListener {
                if (it.isSuccessful) {
                    // 로그인 처리
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun googleLogin(googleSignInClient: GoogleSignInClient) {
        val signInIntent = googleSignInClient?.signInIntent
        if (signInIntent != null) {
            googleSignInLauncher.launch(signInIntent)
        }
    }

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        } else {
            Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_LONG).show()
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                firebaseAuthWithGoogle(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, "Google Sign-In failed: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 1004) {
//            if (resultCode == Activity.RESULT_CANCELED) {
//                //결과 Intent(data 매개변수) 에서 구글로그인 결과 꺼내오기
//                val result = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }!!
//
//                //정상적으로 로그인되었다면
//                if(result.isSuccess){
//                    //우리의 Firebase 서버에 사용자 이메일정보보내기
//                    val account = result.signInAccount
//                    firebaseAuthWithGoogle(account)
//                }
//            }
//        }
//    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        //구글로부터 로그인된 사용자의 정보(Credentail)을 얻어온다.
        val credential = GoogleAuthProvider.getCredential(account?.idToken!!, null)
        //그 정보를 사용하여 Firebase의 auth를 실행한다.
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener {  //통신 완료가 된 후 무슨일을 할지
                    task ->
                if (task.isSuccessful) {
                    // 로그인 처리를 해주면 됨!
//                    goMainActivity(task.result?.user)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    // 오류가 난 경우!
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
//                progressBar.visibility = View.GONE
            }
    }
}