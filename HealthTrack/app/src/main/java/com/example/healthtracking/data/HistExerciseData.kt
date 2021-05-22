package com.example.healthtracking.data

object HistExerciseData {
    private val typeExercise = arrayOf("biking","running")
    private val timeExercise = arrayOf("05:43 - 07:22","15:43 - 16:22")
    private val paramsExercise = arrayOf("12.3 km","1.4km")
    private val calories = arrayOf("543 cal","130 cal")

    val listData: ArrayList<HistExercise>
        get(){
            val list = arrayListOf<HistExercise>()
            for (position in typeExercise.indices){
                val histExercise = HistExercise()
                histExercise.exerciseType = typeExercise[position]
                histExercise.exerciseTime = timeExercise[position]
                histExercise.exerciseParameter = paramsExercise[position]
                histExercise.exerciseCalories = calories[position]
                list.add(histExercise)
            }
            return list
        }
}