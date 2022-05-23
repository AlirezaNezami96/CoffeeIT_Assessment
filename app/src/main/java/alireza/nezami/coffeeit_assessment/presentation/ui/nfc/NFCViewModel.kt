package alireza.nezami.coffeeit_assessment.presentation.ui.nfc

import alireza.nezami.coffeeit_assessment.data.local.lastChoice.LocalCoffeeMachineRepository
import alireza.nezami.coffeeit_assessment.data.local.lastChoice.model.LastChoice
import alireza.nezami.coffeeit_assessment.data.remote.coffeeMachine.repository.RemoteCoffeeMachineRepository
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeMachine
import alireza.nezami.coffeeit_assessment.presentation.base.BaseViewModel
import alireza.nezami.coffeeit_assessment.utils.Constants
import alireza.nezami.coffeeit_assessment.utils.Result
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NFCViewModel @Inject constructor(
    private val localRepository: LocalCoffeeMachineRepository,
    private val remoteRepository: RemoteCoffeeMachineRepository
) : BaseViewModel() {

    private val _showLastChoice: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showLastChoiceButton = _showLastChoice.value

    private val _coffeeMachineData: MutableLiveData<CoffeeMachine> = MutableLiveData()
    val coffeeMachineData = Transformations.map(_coffeeMachineData) {
        it
    }

    private val _lastChoice: MutableLiveData<LastChoice> = MutableLiveData()
    val lastChoice = Transformations.map(_lastChoice) {
        _showLastChoice.value = it != null
        return@map it
    }


    fun getCoffeeMachineFromServer() {
        viewModelScope.launch {
            val result = remoteRepository.getCoffeeMachine()
            if (result is Result.Success) {
                _coffeeMachineData.value = result.value.toDomainModel()
            } else if (result is Result.Error) {
                _error.value = result.error ?: Constants.DEFAULT_ERROR_MESSAGE
            }
        }
    }

    fun getLastChoiceFromDb() {
        viewModelScope.launch {
            _lastChoice.value = localRepository.getLastChoice()
        }
    }


}