package ai.derek.michigo.view.ui.main

import ai.derek.michigo.model.Team
import ai.derek.michigo.view.ui.dialog.WebViewDialog
import ai.derek.michigo.view.ui.main.nav.MainBottomBar
import ai.derek.michigo.view.ui.theme.Background
import ai.derek.michigo.view.ui.theme.MichigoTheme
import ai.derek.michigo.view.viewmodel.MainSideEffect
import ai.derek.michigo.view.viewmodel.MainViewModel
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
//    val navController = rememberNavController()
    var currentTeam by remember { mutableStateOf(Team.MICHIGO) }
    var webViewUrl by remember { mutableStateOf("") }
    val context = LocalContext.current
    val showToast = { msg: String ->
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    LaunchedEffect(currentTeam) {
        mainViewModel.getSchedule(currentTeam)
    }

    MichigoTheme {

        val mainViewModelState by mainViewModel.collectAsState()
        mainViewModel.collectSideEffect {
            when (it) {
                is MainSideEffect.Toast -> {
                    showToast(it.text)
                }

                is MainSideEffect.ShowWebView -> {
                    webViewUrl = it.url
                }
            }
        }

        Scaffold(
            modifier = Modifier
                .safeDrawingPadding()
                .background(color = Background)
                .fillMaxSize(),
            topBar = {
                TopDateBar(mainViewModelState.yearMonth) {
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
                    mainViewModel.showWebView(it)
                }
            )
        }
        if (webViewUrl.isNotBlank()) {
            // show web view
            WebViewDialog(webViewUrl) {
                webViewUrl = ""
            }
        }
    }
}
