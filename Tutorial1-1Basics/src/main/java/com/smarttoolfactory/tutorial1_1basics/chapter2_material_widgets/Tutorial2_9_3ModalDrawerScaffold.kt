package com.smarttoolfactory.tutorial1_1basics.chapter2_material_widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun Tutorial2_9Screen3() {
    TutorialContent()
}

@ExperimentalMaterialApi
@Composable
private fun TutorialContent() {
    ModalDrawerComponent()
}

@ExperimentalMaterialApi
@Composable
private fun ModalDrawerComponent() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val openDrawer: () -> Unit = { coroutineScope.launch { drawerState.open() } }
    val closeDrawer: () -> Unit = { coroutineScope.launch { drawerState.close() } }
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            ModalDrawerTopAppBar(openDrawer)
        },

        ) {
        ModalDrawer(
            drawerElevation = 24.dp,
            drawerShape = CutCornerShape(topEnd = 24.dp),
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerContentHeader()
                Divider()
                ModelDrawerContentBody(
                    selectedIndex,
                    onSelected = {
                        selectedIndex = it
                    },
                    closeDrawer = closeDrawer
                )
            },
            content = {
                Column(modifier = Modifier.fillMaxSize()) {
                    ModalContent(openDrawer)
                }

            }
        )
    }

}