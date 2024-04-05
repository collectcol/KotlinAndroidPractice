package com.example.fastcampus_wookyung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class Calculator : AppCompatActivity() {
    private lateinit var textResult: (TextView)
    private lateinit var btnOne: (Button)
    private lateinit var btnTwo: (Button)
    private lateinit var btnThree: (Button)
    private lateinit var btnFour: (Button)
    private lateinit var btnFive: (Button)
    private lateinit var btnSix: (Button)
    private lateinit var btnSeven: (Button)
    private lateinit var btnEight: (Button)
    private lateinit var btnNine: (Button)
    private lateinit var btnZero: (Button)
    private lateinit var btnPlus: (Button)
    private lateinit var btnCA: (Button)
    private lateinit var btnResult: (Button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        var numberList = ArrayList<String>()
        var numbers = "0"
        var plusCheck = false

        textResult = findViewById(R.id.text_result)
        btnOne = findViewById(R.id.btn_one)
        btnTwo = findViewById(R.id.btn_two)
        btnThree = findViewById(R.id.btn_three)
        btnFour = findViewById(R.id.btn_four)
        btnFive = findViewById(R.id.btn_five)
        btnSix = findViewById(R.id.btn_six)
        btnSeven = findViewById(R.id.btn_seven)
        btnEight = findViewById(R.id.btn_eight)
        btnNine = findViewById(R.id.btn_nine)
        btnZero = findViewById(R.id.btn_zero)
        btnPlus = findViewById(R.id.btn_plus)
        btnCA = findViewById(R.id.btn_ca)
        btnResult = findViewById(R.id.btn_result)

        var numberClickListener = View.OnClickListener { v ->
            if (plusCheck) {
                numbers = "0"
                plusCheck = false
            }
            if (numbers != "0") numbers += v.tag.toString()
            else numbers = v.tag.toString()

            textResult.text = numbers
        }

        val numberButtonList: List<TextView> = listOf(btnOne, btnTwo, btnThree, btnFour, btnFive
        , btnSix, btnSeven, btnEight, btnNine, btnZero)

        numberButtonList.forEach{
            it.setOnClickListener(numberClickListener)
        }

        btnCA.setOnClickListener {
            numbers = "0"
            numberList.clear()
            textResult.text = "0"
            plusCheck = false
        }

        btnPlus.setOnClickListener {
            numberList.add(numbers)
//            numbers = "0"
            plusCheck = true
        }

        btnResult.setOnClickListener {
            var resultValue = 0
            numberList.add(numbers)
            for (i in numberList) {
                resultValue += i.toInt()
            }
            textResult.text = resultValue.toString()
            numbers = "0"
            numberList.clear()
            plusCheck = false
        }
    }
}