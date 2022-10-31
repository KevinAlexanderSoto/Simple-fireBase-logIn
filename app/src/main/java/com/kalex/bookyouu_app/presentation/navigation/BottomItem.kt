package com.kalex.bookyouu_app.presentation.navigation

import com.kalex.bookyouu_app.R
import com.kalex.bookyouu_app.common.Constants

sealed class BottomItem(
    val route : String,
    val icon :Int,
    val title : String
){
    object AdminCreateUser : BottomItem(Constants.AdminHomeNavItem, R.drawable.ic_add,"Crear")
    object AdminSubject : BottomItem(Constants.AdminbottomSubjectNavItem, R.drawable.ic_add_subject_box,"Materias")
    object AdminSearch : BottomItem(Constants.AdminbottomSearchNavItem, R.drawable.ic_search,"Consulta")

    object ProfesorCreateUser : BottomItem("ruta1", R.drawable.upload_file_24,"ruta1")

    object StudentCreateUser : BottomItem("ruta1", R.drawable.upload_file_24,"ruta1")
    object StudentCreateUser1 : BottomItem("ruta1", R.drawable.upload_file_24,"ruta1")
    object StudentCreateUser2: BottomItem("ruta1", R.drawable.upload_file_24,"ruta1")
}
