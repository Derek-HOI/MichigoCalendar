package ai.derek.michigo.view.ui.main.nav

import ai.derek.michigo.model.Team
import ai.derek.michigo.view.ui.theme.BottomBarColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hyknowk.presentation.ext.clickableSingle

/**
 * unused..
 */
@Composable
fun MainBottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        MainNavItem.Michigo,
        MainNavItem.Diemons
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            val selected = item.route == currentRoute
            NavigationBarItem(
                selected = selected,
                icon = {
                    Text(
                        text = stringResource(id = item.name),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors().copy(
                    selectedIconColor = Color.Black,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White
                )
            )
        }
    }
}

//@MichigoPreview
//@Composable
//private fun MainBottomNavigationScreenPreview() {
//    val navController = rememberNavController()
//    Scaffold(
//        bottomBar = {
//            MainBottomNavigationBar(navController = navController)
//        }
//    ) {
//        Surface(
//            modifier = Modifier.padding(it),
//            color = MaterialTheme.colorScheme.surface,
//        ) {
//            MainNavGraph(navController = navController)
//        }
//    }
//}

@Composable
fun MainBottomBar(currentTeam: Team, onClick: (Team) -> Unit) {
    val items = listOf(
        MainNavItem.Michigo,
        MainNavItem.Diemons
    )
    Row(
        modifier = Modifier
            .background(color = BottomBarColor)
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        items.forEach { item ->
            val selected = item.team == currentTeam
            MainBottomNavItem(
                selected = selected,
                item = item,
                onClick = {
                    onClick(item.team)
                },
            )
        }
    }
}

@Composable
private fun MainBottomNavItem(
    selected: Boolean,
    item: MainNavItem,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickableSingle(onClick = onClick)
    ) {
        Text(
            text = stringResource(id = item.name),
            style = TextStyle(
                color = if (selected) Color.White else Color.LightGray,
                fontSize = 16.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        )
    }
}
