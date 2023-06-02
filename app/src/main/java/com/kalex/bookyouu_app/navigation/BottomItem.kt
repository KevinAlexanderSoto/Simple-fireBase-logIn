package com.kalex.bookyouu_app.navigation

import com.kalex.bookyouu_app.R
import com.kalex.bookyouu_app.common.Constants

sealed class BottomItem(
    val route: String,
    val icon: Int,
    val title: String,
) {
    object AdminCreateUser : BottomItem(Constants.AdminHomeNavItem, R.drawable.ic_add, "Crear")
    object AdminSubject :
        BottomItem(Constants.AdminbottomSubjectNavItem, R.drawable.ic_add_subject_box, "Materias")

    object AdminSearch :
        BottomItem(Constants.AdminbottomSearchNavItem, R.drawable.ic_search, "Consulta")

}
