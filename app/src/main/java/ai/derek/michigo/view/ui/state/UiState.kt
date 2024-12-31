package ai.derek.michigo.view.ui.state

import ai.derek.michigo.model.CustomException

sealed class UiStatus {
    data object Loading : UiStatus()
    data object Success : UiStatus()
    data class Failed(val e: CustomException) : UiStatus()
}
