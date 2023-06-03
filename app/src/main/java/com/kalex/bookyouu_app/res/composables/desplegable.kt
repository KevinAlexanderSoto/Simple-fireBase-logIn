package com.kalex.bookyouu_app.res.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kalex.bookyouu_app.R
import com.kalex.bookyouu_app.common.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Drawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController,
) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.imgdesplegable),
            contentDescription = "Bg Image",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp),
        )

        DrawerItem(texto = "Cerrar sección", R.drawable.logout_20) {
            navController.navigate(Constants.MainNavItem) {
                launchSingleTop = true
            }

            scope.launch {
                scaffoldState.drawerState.close()
            }
        }
    }
}

@Composable
fun DrawerItem(
    texto: String,
    imagen: Any,
    onItemClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icono(imagen, 20)
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = texto,
            style = TextStyle(fontSize = 18.sp),

        )
    }
}
