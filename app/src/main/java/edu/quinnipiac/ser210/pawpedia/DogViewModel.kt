package edu.quinnipiac.ser210.pawpedia

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.quinnipiac.ser210.pawpedia.SQL.Dog
import edu.quinnipiac.ser210.pawpedia.SQL.DogDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogViewModel(application: Application) : AndroidViewModel(application) {
    private val dogDao = DogDatabase.getDatabase(application).dogDao()

    private val _dogs = MutableStateFlow<List<Dog>>(emptyList())
    val dogs: StateFlow<List<Dog>> = _dogs

    fun loadDogBySize(sizeIndex: Int) {
        viewModelScope.launch {
            _dogs.value = dogDao.getDogsBySize(sizeIndex)
        }
    }
}
