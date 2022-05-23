package alireza.nezami.coffeeit_assessment.presentation.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(

) :ViewModel(){

    protected val _error: MutableStateFlow<String> = MutableStateFlow("")
    val error = _error.value
}