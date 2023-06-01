package com.kalex.bookyouu_app.presentation.navigation

import AdminCreateProfesor
import AdminCreateSubject
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
import com.kalex.bookyouu_app.presentation.theme.blanco
import com.kalex.bookyouu_app.presentation.theme.bookYouuPrimary
import com.kalex.bookyouu_app.presentation.ui.*

@ExperimentalPermissionsApi
@Composable
fun Navegacion(onfiger: () -> Unit) {
    val navController = rememberNavController()
    // TODO:add the context change
    var appContext by remember {
        mutableStateOf("default")
    }
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

            ) {_->
                // TODO: PU HERE THE CONTEX val nombre= backStackEntry.arguments?.getString("nombre")
                val nombre = appContext
                requireNotNull(nombre)
                adminCreate(navController, nombre)
            }
        }
        composable(Constants.AdminSendStudentNavItem) {
            AdminCreateStudent(navController)
        }
        composable(Constants.AdminSendProfesorNavItem) {
            AdminCreateProfesor(navController)
        }
        composable(Constants.AdminSendSubjectNavItem) {
            AdminCreateSubject(navController)
        }
        composable(Constants.AdminbottomSubjectNavItem) {
            val scaffoldState = rememberScaffoldState(
                drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
            )
            Scaffold(
                scaffoldState = scaffoldState,
                bottomBar = { bottomNavigationScaffold(navController, navigation_item) },

            ) {_->
                // TODO: PU HERE THE CONTEX val nombre= backStackEntry.arguments?.getString("nombre")
                val nombre = appContext
                requireNotNull(nombre)
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

            ) {_ ->
                // TODO: PU HERE THE CONTEX val nombre= backStackEntry.arguments?.getString("nombre")
                val nombre = appContext
                requireNotNull(nombre)
                adminBottomSearchHome(navController)
            }
        }
// -----------------Student section ---------------------------------
        composable(Constants.StudentHomeNavItem) {
        }
        composable(Constants.getDocDetailNavItem) { backStackEntry ->
            val idRegistro = backStackEntry.arguments?.getString("idRegistro")
            requireNotNull(idRegistro)
        }
        composable(Constants.oficesNavItem) {
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
