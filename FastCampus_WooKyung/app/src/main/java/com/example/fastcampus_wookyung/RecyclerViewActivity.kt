package com.example.fastcampus_wookyung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val carList = mutableListOf<Car>()
        for (i in 0..100) {
            carList.add(Car("" + i + "번째 자동차", "" + i + "번째 엔진"))
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = RecyclerViewAdapter(carList, LayoutInflater.from(this))
        // 리사이클러뷰에 레이아웃 메니저 장착 ( 세로로 정렬, false 는 장방향/역방향)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.layoutManager = GridLayoutManager(this, 2)

    }
}

class RecyclerViewAdapter(
    // outer class
    var carList: MutableList<Car>,
    var inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    // RecyclerView.ViewHolder 를 상속받는 이유
    // RecyclerView의 ViewHolder를 상속받아야만 개선된 ViewHolder를 쓸 수 있기 때문
    // ListView는 ViewHolder를 쓰긴 쓰지만 성능적 이슈가 있었다...
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // inner class
        // 아이템 뷰의 상세 뷰 컴포넌트를 홀드한다
        val carImage: ImageView
        val nthCar: TextView
        val nthEngine: TextView

        init {
            carImage = itemView.findViewById(R.id.carImage)
            nthCar = itemView.findViewById(R.id.nthCar)
            nthEngine = itemView.findViewById(R.id.nthEngine)
            itemView.setOnClickListener {
                val position : Int = adapterPosition
                val car = carList[position]
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 아이템뷰를 리턴
        val view = inflater.inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 데이터를 아이템뷰의 뷰컴포넌트와 묶는다(채워준다)
        // 데이터를 넣어주는 작업
        holder.nthCar.text = carList[position].nthCar
        holder.nthEngine.text = carList[position].nthEngine
    }
}