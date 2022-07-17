package com.example.laundryapp.components.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import com.example.laundryapp.IS_DIALOG_OPEN

@Composable
fun ViewDialogLoading() {

    val context = LocalContext.current

    Dialog(onDismissRequest = { IS_DIALOG_OPEN.value = true }) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}