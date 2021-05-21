package com.template.androidtemplate.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.template.androidtemplate.R
import com.template.androidtemplate.data.model.GameOfThrones
import kotlinx.android.synthetic.main.item_row_child.view.*

open class ChildMembersAdapter(var memberData: List<GameOfThrones.Member>) :
    RecyclerView.Adapter<ChildMembersAdapter.DataViewHolder>() {

    private var membersList: List<GameOfThrones.Member> = ArrayList()

    init {
        this.membersList = memberData
    }

    var onItemClick: ((String) -> Unit)? = null

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(membersList[adapterPosition].name)
            }
        }

        fun bind(result: GameOfThrones.Member) {
            itemView.name.text = result.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_row_child, parent,
            false
        )
    )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(membersList[position])
    }

    override fun getItemCount(): Int = membersList.size


}