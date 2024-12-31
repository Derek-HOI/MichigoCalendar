package ai.derek.michigo.view.ui.main.nav

import ai.derek.michigo.model.Team
import ai.derek.michigo.view.ui.main.ScheduleScreen
import ai.derek.michigo.view.viewmodel.MainViewModel
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/**
 * unused..
 */
@Composable
fun MainNavGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    changeCurrentTeam: (Team) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = MainNavItem.Michigo.route,
    ) {
        composable(MainNavItem.Michigo.route) {
//            ScheduleScreen()
        }

        composable(MainNavItem.Diemons.route) {
//            ScheduleScreen()
        }
    }
}
