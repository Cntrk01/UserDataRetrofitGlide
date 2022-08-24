package com.example.userdatamvvm

import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userdatamvvm.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val viewModel=makeApiCall()

        setupBinding(viewModel)
    }

    fun setupBinding(viewModel:MainActivityViewModel){
        val binding:ActivityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.setVariable(BR.viewModel,viewModel)
        binding.executePendingBindings()
        recyclerView.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            val dec=DividerItemDecoration(this@MainActivity,VERTICAL)
            addItemDecoration(dec)
        }
    }

    fun makeApiCall() : MainActivityViewModel{
        val viewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, Observer {
            progressBar.visibility= View.GONE
            if(it!=null){
                viewModel.setAdapterData(it.items)
            }else{
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeAPICall("newyork")

        return viewModel
    }
}