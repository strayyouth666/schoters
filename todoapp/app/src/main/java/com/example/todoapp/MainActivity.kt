package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        getDataFromApi()
    }

    private fun setupRecyclerView(){
        mainAdapter = MainAdapter(arrayOfListOf(), object : MainAdapter.OnAdapterListener{

        }
        )
        recyclerView.apply {this.RecyclerView!
        LayoutManager = LinearLayoutManager(applicationContext)
        adapter =  mainAdapter
        }
    }

    private fun getDataFromApi(){

        ApiService.endpoint.getData()
            .enqueue(object : Callback<MainModel> {
                override fun onFailure(call: Call<MainModel>, t: Throwable){
                    printLog("onFailure: $t")
                }

                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ){
                    if (response.isSuccesfull) {
                        showData(response.body()!!)
                    }
                }
            })
    }

    private fun printLog(message: String){
        Log.d(TAG, message)
    }

    private fun showData(data: MainModel){
        val results = data.result
        mainAdapter.setData(results)
    }
}