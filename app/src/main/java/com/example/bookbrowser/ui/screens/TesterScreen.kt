package com.example.bookbrowser.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookbrowser.BuildConfigHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TesterScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Экран тестировщика") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Режим отладки: ${BuildConfigHelper.IS_DEBUG}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Текущий URL сервера:",
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = BuildConfigHelper.API_BASE_URL,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {  },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Сбросить кэш приложения (Демо)")
            }
        }
    }
}