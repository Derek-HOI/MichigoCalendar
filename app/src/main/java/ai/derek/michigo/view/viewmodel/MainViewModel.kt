package ai.derek.michigo.view.viewmodel

import ai.derek.michigo.business.ScheduleRepository
import ai.derek.michigo.model.CustomException
import ai.derek.michigo.model.Team
import ai.derek.michigo.view.BaseViewModel
import ai.derek.michigo.view.ui.state.UiStatus
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val repo: ScheduleRepository
) : BaseViewModel<MainState, MainSideEffect>() {

    override val tag: String
        get() = "MainViewModel"

    override val container: Container<MainState, MainSideEffect> =
        container(
            initialState = MainState(),
            buildSettings = {
                this.exceptionHandler = CoroutineExceptionHandler { context, throwable ->
                    intent {
                        // exception handling
                        if (throwable is CustomException) {
                            reduce {
                                state.copy(uiStatus = UiStatus.Failed(throwable))
                            }
                        } else {
                            postSideEffect(
                                MainSideEffect.Toast(
                                    throwable.message ?: "empty message."
                                )
                            )
                        }
                    }
                }
            }
        ) {
            Log.i(tag, "container onCreate, state= $state")
        }

    fun plusMonth() {
        intent {
            reduce {
                state.copy(yearMonth = state.yearMonth.plusMonths(1))
            }
        }
    }

    fun minusMonth() {
        intent {
            reduce {
                state.copy(yearMonth = state.yearMonth.minusMonths(1))
            }
        }
    }

    fun getSchedule(team: Team) {
        intent {
            reduce {
                state.copy(uiStatus = UiStatus.Loading)
            }
            val result = repo.getSchedule(team, state.yearMonth)
            reduce {
                state.copy(
                    uiStatus = UiStatus.Success,
                    schedules = result
                )
            }
        }
    }
}
