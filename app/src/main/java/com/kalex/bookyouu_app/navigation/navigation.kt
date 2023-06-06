package com.kalex.bookyouu_app.navigation


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.kalex.bookyouu_app.common.Constants
import com.kalex.bookyouu_app.presentation.ui.*
import com.kalex.bookyouu_app.presentation.ui.admin.adminBottomSearchHome
import com.kalex.bookyouu_app.presentation.ui.admin.adminBottomSubjectHome
import com.kalex.bookyouu_app.presentation.ui.admin.adminCreate
import com.kalex.bookyouu_app.res.theme.blanco
import com.kalex.bookyouu_app.res.theme.bookYouuPrimary

@ExperimentalPermissionsApi
@Composable
fun Navegacion() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Constants.MainNavItem,
    ) // TODO : change the start destination
    {
        val navigation_item = listOf(
            BottomItem.AdminCreateUser,
            BottomItem.AdminSubject,
            BottomItem.AdminSearch,
        )

        composable(Constants.MainNavItem) {
            SingIn(navController)
        }
// ----------------Admin Section -------------------
        composable(
            Constants.AdminHomeNavItem,
        ) { backStackEntry ->
            val scaffoldState = rememberScaffoldState(
                drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
            )
            Scaffold(
                scaffoldState = scaffoldState,
                bottomBar = { bottomNavigationScaffold(navController, navigation_item) },

            ) { _ ->
                adminCreate(navController)
            }
        }
        composable(Constants.AdminSendStudentNavItem) {

        }
        composable(Constants.AdminSendProfesorNavItem) {

        }
        composable(Constants.AdminSendSubjectNavItem) {

        }
        composable(Constants.AdminbottomSubjectNavItem) {
            val scaffoldState = rememberScaffoldState(
                drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
            )
            Scaffold(
                scaffoldState = scaffoldState,
                bottomBar = { bottomNavigationScaffold(navController, navigation_item) },

            ) { _ ->
                adminBottomSubjectHome(navController)
            }
        }

        composable(Constants.AdminbottomSearchNavItem) {
            val scaffoldState = rememberScaffoldState(
                drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
            )
            Scaffold(
                scaffoldState = scaffoldState,
                bottomBar = { bottomNavigationScaffold(navController, navigation_item) },

            ) { _ ->

                adminBottomSearchHome(navController)
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route
}

@Composable
fun bottomNavigationScaffold(navController: NavHostController, navigation_item: List<BottomItem>) {
    BottomNavigation(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
        backgroundColor = bookYouuPrimary,
        contentColor = blanco,
        elevation = 3.dp,
    ) {
        navigation_item.forEach { bottomItem ->
            val actualRoute = currentRoute(navController = navController)
            BottomNavigationItem(
                selected = actualRoute == bottomItem.route,
                onClick = { navController.navigate(bottomItem.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = bottomItem.icon),
                        contentDescription = bottomItem.title,
                    )
                },
                label = {
                    Text(text = bottomItem.title)
                },
            )
        }
    }
}
