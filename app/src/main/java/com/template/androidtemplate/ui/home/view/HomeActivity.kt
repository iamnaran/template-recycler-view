package com.template.androidtemplate.ui.home.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.template.androidtemplate.R
import com.template.androidtemplate.data.model.GameOfThrones
import com.template.androidtemplate.ui.home.adapter.ParentHouseAdapter
import com.template.androidtemplate.ui.home.viewmodel.HomeViewModel
import com.template.androidtemplate.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var parentHouseAdapter: ParentHouseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpViews()
        doObserveWork()
    }

    private fun setUpViews() {

        parent_recycler_view.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        parentHouseAdapter = ParentHouseAdapter()
        parent_recycler_view.adapter = parentHouseAdapter

    }

    private fun doObserveWork() {


        homeViewModel.progressBarVisibility.observe(this, Observer {

        })

        homeViewModel.getGameOfThronesData().observe(this, Observer {

            when (it.status) {

                Status.SUCCESS -> {
                    val gson: Gson = Gson()
                    Log.e( "doObserveWork: ",gson.toJson(it.data) )
                    renderGameOfThronesList(it.data!!)

                }

                Status.ERROR -> {


                }

                Status.LOADING -> {


                }


            }

        })

    }

    private fun renderGameOfThronesList(gameOfThrones: List<GameOfThrones>) {
        parentHouseAdapter.addData(gameOfThrones)
        parentHouseAdapter.notifyDataSetChanged()
    }


}