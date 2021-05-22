package com.example.healthtracking.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.healthtracking.R
import com.example.healthtracking.data.HistExercise

class ExerciseHistoryAdapter(private val listHistExercise: ArrayList<HistExercise>):RecyclerView.Adapter<ExerciseHistoryAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgHistoryExercise: ImageView = itemView.findViewById(R.id.img_type_exercise)
        var tvTimeExercise: TextView = itemView.findViewById(R.id.tv_time_exercise)
        var tvParamsExercise: TextView = itemView.findViewById(R.id.tv_param_exercise)
        var tvCaloriesExercise: TextView = itemView.findViewById(R.id.tv_cal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_history_exercise, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val history = listHistExercise[position]

        if (history.exerciseType == "biking"){
            Glide.with(holder.itemView.context)
                .load(R.drawable.bike)
                .apply(RequestOptions().override(60, 60))
                .into(holder.imgHistoryExercise)
        }
        else if (history.exerciseType == "running"){
            Glide.with(holder.itemView.context)
                .load(R.drawable.running)
                .apply(RequestOptions().override(60, 60))
                .into(holder.imgHistoryExercise)
        }

        holder.tvTimeExercise.text = history.exerciseTime
        holder.tvParamsExercise.text = history.exerciseParameter
        holder.tvCaloriesExercise.text = history.exerciseCalories

    }

    override fun getItemCount(): Int {
        return listHistExercise.size
    }
}