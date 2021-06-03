package com.example.healthtracking

import android.app.Application
import androidx.lifecycle.*
import com.example.healthtracking.db.exercise_data.ExerciseDataDB
import com.example.healthtracking.db.exercise_data.ExerciseDataDao
import com.example.healthtracking.db.exercise_data.ExerciseDataModel
import kotlinx.coroutines.launch

class InsertModelView(val db: ExerciseDataDao, application: Application):AndroidViewModel(application) {

    lateinit var result: LiveData<ExerciseDataModel>

    fun insert(value: ExerciseDataModel) {
        viewModelScope.launch {
            db.insert(value)
        }
    }

    fun delete(){
        viewModelScope.launch {
            db.deleteAll()
        }
    }

    fun getSpecific(word: String){
        viewModelScope.launch {
            result = db.getSpecificValue(word)
        }
    }

}

class ExerciseViewModelFactory(
    private val dataSource: ExerciseDataDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InsertModelView::class.java)) {
            return InsertModelView(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}