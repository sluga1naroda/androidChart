package com.example.rightechiot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DashBoardAdapter() : RecyclerView.Adapter<DashBoardAdapter.DashBoardViewHolder>(){
    private var dashBoardList : List<DashBoardDescription> = emptyList()

    fun setItems(items:List<DashBoardDescription>){
        dashBoardList = items
        notifyDataSetChanged()
    }

    fun addItem(item:DashBoardDescription){
        dashBoardList = dashBoardList.toMutableList().apply { add(item) }
            notifyItemInserted(dashBoardList.size-1)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dashboard_menu,parent,false)
        return DashBoardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        val currentItem = dashBoardList[position]
        holder.dashBoardName.text = currentItem.name
        holder.dashBoardDescription.text = currentItem.description
        holder.dashBoardFavourite.setImageResource(if (currentItem.isFav) R.drawable.ic_vector_1 else R.drawable.ic_vector)
    }

    override fun getItemCount(): Int {
        return dashBoardList.size
    }

    inner class DashBoardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val dashBoardName : TextView = itemView.findViewById(R.id.dasboardName)
        val dashBoardDescription : TextView = itemView.findViewById(R.id.descriptionView)
        val dashBoardFavourite : ImageView = itemView.findViewById(R.id.starView)

        init {
            dashBoardFavourite.setOnClickListener{
                val item = dashBoardList.getOrNull(bindingAdapterPosition)?: return@setOnClickListener
                dashBoardList = dashBoardList.toMutableList().apply{ set(bindingAdapterPosition,item.copy(isFav = !item.isFav)) }
                notifyItemChanged(bindingAdapterPosition)
            }
        }
    }
}