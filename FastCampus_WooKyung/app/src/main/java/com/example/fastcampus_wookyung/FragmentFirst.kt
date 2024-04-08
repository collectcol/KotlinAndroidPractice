package com.example.fastcampus_wookyung

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentFirst : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, // 부모 뷰
        savedInstanceState: Bundle?
    ): View? {
        // inflater : XML을 화면에 사용할 준비를 한다 (XML -> View로 만들어 준다)
        // container : 프라그먼트에서 사용될 XML 의 부모 뷰
        val view = inflater.inflate(R.layout.first_fragment, container, false)
        // acttachToRoot : 루트뷰에 불일지 말지 (X)
//        return super.onCreateView(inflater, container, savedInstanceState)

        // 장착된 상위 Activity의 함수를 호출 하는 방법
        (view.findViewById<TextView>(R.id.call_activity)).setOnClickListener {
            // activity는 말 그대로 activity를 다 가져온거기 때문에 내가 써야할 프래그먼트로 캐스팅한다
            (activity as FragmentActivity).printTestLog()
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 프래그먼트에 전달된 데이터 받기
        val data: String? = arguments?.getString("key")
    }

    fun printTestLog(){
        Log.d("testt", "print test log from fragment")
    }
}