package com.my.doha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.my.doha.base.BaseActivity
import com.my.doha.data.UserData
import kotlinx.coroutines.*

class LoginActivity : BaseActivity() {
    private lateinit var mEmailEditText: EditText
    private lateinit var mPasswordEditText: EditText
    private lateinit var mLoginButton: Button
    private lateinit var mGoogleLoginButton: SignInButton
    private lateinit var mGSO: GoogleSignInOptions
    private lateinit var mDohaAppContext: DohaApp
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        supportActionBar?.hide()

        mDohaAppContext = applicationContext as DohaApp

        mEmailEditText = findViewById(R.id.edt_input_email)
        mPasswordEditText = findViewById(R.id.edt_input_password)
        progressBar = findViewById(R.id.progress_bar)

        mLoginButton = findViewById<Button>(R.id.btn_login).apply {
            setOnClickListener {
                signUp()
            }
        }

        mGSO = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, mGSO)

        mGoogleLoginButton = findViewById<SignInButton>(R.id.btn_google_login).apply {
            setOnClickListener {
                googleLogin(googleSignInClient)
            }
        }
    }

    private fun signUp() {
        val email = mEmailEditText.text.toString()
        val password = mPasswordEditText.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "이메일과 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
            return
        }
        showProgressBar()
        mDohaAppContext.mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                hideProgressBar()
                if (task.isSuccessful) {
                    mDohaAppContext.mAuth.currentUser?.let { user ->
                        saveUserToFirestoreAndLocal(user)
                    }
                } else {
                    task.exception?.message?.let {
                        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                    } ?: run {
                        signIn()
                    }
                }
            }
    }

    private fun signIn() {
        val email = mEmailEditText.text.toString()
        val password = mPasswordEditText.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "이메일과 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
            return
        }

        showProgressBar()
        mDohaAppContext.mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                hideProgressBar()
                if (task.isSuccessful) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        } else {
            hideProgressBar()
            Toast.makeText(this, "구글 로그인 실패", Toast.LENGTH_LONG).show()
        }
    }

    private fun googleLogin(googleSignInClient: GoogleSignInClient) {
        showProgressBar()
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                firebaseAuthWithGoogle(account)
            }
        } catch (e: ApiException) {
            hideProgressBar()
            Toast.makeText(this, "구글 로그인 실패: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken!!, null)
        mDohaAppContext.mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                hideProgressBar()
                if (task.isSuccessful) {
                    mDohaAppContext.mAuth.currentUser?.let { user ->
                        saveUserToFirestoreAndLocal(user)
                    }
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }

//    override fun onStart() {
//        super.onStart()
//        mDohaAppContext.mAuth.currentUser?.let { user ->
//            loadUserData(user)
//        }
//    }
//
//    private fun loadUserData(user: FirebaseUser) {
//        val db = DatabaseProvider.getDatabase(mDohaAppContext)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val userData = db.userDataDao().getUserDataById(user.uid)
//            if (userData != null) {
//                withContext(Dispatchers.Main) {
//                    moveMainPage(user)
//                }
//            } else {
//                mDohaAppContext.mFirestore.collection("users").document(user.uid)
//                    .get()
//                    .addOnSuccessListener { document ->
//                        document?.toObject(UserData::class.java)?.let { userData ->
//                            CoroutineScope(Dispatchers.IO).launch {
//                                db.userDataDao().insertUserData(userData)
//                                withContext(Dispatchers.Main) {
//                                    moveMainPage(user)
//                                }
//                            }
//                        } ?: run {
//                            Toast.makeText(
//                                this@LoginActivity,
//                                "No such document",
//                                Toast.LENGTH_LONG
//                            ).show()
//                        }
//                    }
//                    .addOnFailureListener { e ->
//                        Toast.makeText(
//                            this@LoginActivity,
//                            "Firestore 데이터 가져오기 실패: ${e.message}",
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//            }
//        }
//    }

    private fun saveUserToFirestoreAndLocal(user: FirebaseUser) {
        val userData = UserData(user.uid, user.email, user.displayName)
        mDohaAppContext.mFirestore.collection("users").document(user.uid)
            .set(userData)
            .addOnSuccessListener {
                val db = DatabaseProvider.getDatabase(mDohaAppContext)
                CoroutineScope(Dispatchers.IO).launch {
                    db.userDataDao().insertUserData(userData)
                    withContext(Dispatchers.Main) {
                        moveMainPage(user)
                    }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "사용자 정보 저장 실패: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun moveMainPage(user: FirebaseUser) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun syncUserData(user: FirebaseUser) {
        val db = DatabaseProvider.getDatabase(mDohaAppContext)
        CoroutineScope(Dispatchers.IO).launch {
            val localUserData = db.userDataDao().getUserDataById(user.uid)
            mDohaAppContext.mFirestore.collection("users").document(user.uid).get()
                .addOnSuccessListener { document ->
                    document?.toObject(UserData::class.java)?.let { remoteUserData ->
                        if (localUserData == null || localUserData != remoteUserData) {
                            CoroutineScope(Dispatchers.IO).launch {
                                db.userDataDao().insertUserData(remoteUserData)
                            }
                        }
                    }
                }
                .addOnFailureListener { e ->
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(
                            this@LoginActivity,
                            "데이터 동기화 실패: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    private fun showProgressBar() {
        progressBar.visibility = ProgressBar.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = ProgressBar.GONE
    }
}
