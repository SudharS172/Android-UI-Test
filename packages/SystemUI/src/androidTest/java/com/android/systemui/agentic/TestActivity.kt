package com.android.systemui.agentic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgenticTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AgenticUICoordinator(this).AgenticHomeScreen()
                }
            }
        }
    }
}
