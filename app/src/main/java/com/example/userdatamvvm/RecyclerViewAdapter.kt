package com.example.userdatamvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userdatamvvm.databinding.RecyclerRowBinding

class RecyclerViewAdapter:RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var items=ArrayList<RecyclerData>()

    fun setDataList(data:ArrayList<RecyclerData>){
        this.items=data
    }
    class MyViewHolder(val binding:RecyclerRowBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data: RecyclerData){
            binding.recyclerData=data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerViewAdapter.MyViewHolder{
        val view=LayoutInflater.from(parent.context)
        val binding=RecyclerRowBinding.inflate(view)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    companion object{
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(thubmImage:ImageView,url:String){
                Glide.with(thubmImage)
                    .load(url).circleCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(thubmImage)
        }
    }

}