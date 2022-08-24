package com.example.userdatamvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    lateinit var recyclerListData:MutableLiveData<RecyclerList>
    lateinit var recyclerViewAdapter:RecyclerViewAdapter

    init {
        recyclerListData= MutableLiveData()
        recyclerViewAdapter= RecyclerViewAdapter()
    }
    fun getAdapter():RecyclerViewAdapter{
        return recyclerViewAdapter
    }
    fun setAdapterData(data:ArrayList<RecyclerData>){
         recyclerViewAdapter.setDataList(data)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    fun getRecyclerListDataObserver():MutableLiveData<RecyclerList>{
        return recyclerListData
    }
    fun makeAPICall(input:String){
        val retro=RetroInstance.getRetroInstance().create(RetrofitService::class.java)
        val call=retro.getDataFromAPI(input)

        call.enqueue(object :Callback<RecyclerList>{
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if(response.isSuccessful){
                    recyclerListData.postValue(response.body())
                }else{
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

        })
    }
}