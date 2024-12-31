package ai.derek.michigo.view.ui.main.nav

import ai.derek.michigo.R
import ai.derek.michigo.model.Team
import androidx.annotation.StringRes

sealed class MainNavItem(
    val route: String,
    @StringRes val name: Int,
    val team: Team
) {
    data object Michigo : MainNavItem(
        "main_michigo",
        R.string.michigo,
        Team.MICHIGO
    )

    data object Diemons : MainNavItem(
        "main_diemons",
        R.string.diemons,
        Team.DIEMONS
    )
}
