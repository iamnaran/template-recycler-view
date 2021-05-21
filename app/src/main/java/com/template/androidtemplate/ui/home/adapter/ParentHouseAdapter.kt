package com.template.androidtemplate.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.template.androidtemplate.R
import com.template.androidtemplate.data.model.GameOfThrones
import kotlinx.android.synthetic.main.item_row_parent.view.*


open class ParentHouseAdapter :
    RecyclerView.Adapter<ParentHouseAdapter.DataViewHolder>() {

    var gameOfThronesHouseList: List<GameOfThrones> = ArrayList()


    var onItemClick: ((GameOfThrones) -> Unit)? = null

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(gameOfThronesHouseList[adapterPosition])

            }


        }

        fun bind(result: GameOfThrones) {
            itemView.content_title.text = result.name
            val childMembersAdapter = ChildMembersAdapter(result.members)
            itemView.child_recycler_view.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL,false)
            itemView.child_recycler_view.adapter = childMembersAdapter

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_row_parent, parent,
            false
        )
    )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(gameOfThronesHouseList[position])
    }

    override fun getItemCount(): Int = gameOfThronesHouseList.size


    fun addData(list: List<GameOfThrones>) {
        gameOfThronesHouseList = list
        notifyDataSetChanged()
    }


}