package ai.derek.michigo.view.ui.main

import ai.derek.michigo.model.Team
import ai.derek.michigo.view.ui.main.nav.MainBottomBar
import ai.derek.michigo.view.ui.theme.Background
import ai.derek.michigo.view.ui.theme.MichigoTheme
import ai.derek.michigo.view.viewmodel.MainViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
//    val navController = rememberNavController()
    var currentTeam by remember { mutableStateOf(Team.MICHIGO) }

    LaunchedEffect(currentTeam) {
        mainViewModel.getSchedule(currentTeam)
    }

    MichigoTheme {

        val mainViewModelState by mainViewModel.collectAsState()

        Scaffold(
            modifier = Modifier
                .safeDrawingPadding()
                .background(color = Background)
                .fillMaxSize(),
            topBar = {
                TopDateItem(mainViewModelState.yearMonth) {
                    when (it) {
                        DateButtonType.PrevMonth -> mainViewModel.minusMonth()
                        DateButtonType.NextMonth -> mainViewModel.plusMonth()
                    }
                    mainViewModel.getSchedule(currentTeam)
                }
            },
            bottomBar = {
//                MainBottomNavigationBar(navController = navController)
                MainBottomBar(currentTeam) {
                    currentTeam = it
                }
            },
            contentWindowInsets = WindowInsets.systemBars
        ) { innerPadding ->
//            Surface(
//                modifier = Modifier.padding(innerPadding),
//                color = MaterialTheme.colorScheme.surface,
//            ) {
//                MainNavGraph(
//                    navController = navController,
//                    mainViewModel = mainViewModel
//                ) {
//                    currentTeam = it
//                }
//            }
            ScheduleScreen(
                innerPadding = innerPadding,
                schedules = mainViewModelState.schedules,
                onClick = {
                    //TODO
                }
            )
        }
    }
}
