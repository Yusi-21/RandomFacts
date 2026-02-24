package com.mirea.randomfacts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirea.randomfacts.data.FactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FactsUiState(
    val fact: String = "",
    val isLoading: Boolean = false,
    val isFirstLaunch: Boolean = true
)

class FactsViewModel (
    private val repository: FactsRepository = FactsRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(FactsUiState())
    val uiState: StateFlow<FactsUiState> = _uiState.asStateFlow()

    fun getRandomFact(): Flow<String> = repository.getRandomFact()

    fun loadNewFact() { _uiState.update { it.copy(isLoading = true, isFirstLaunch = false) }

        viewModelScope.launch {
            getRandomFact().collect { fact ->
                if (fact.isNotEmpty()) {
                    _uiState.update {
                        it.copy(fact = fact, isLoading = false)
                    }
                }
            }
        }
    }
}