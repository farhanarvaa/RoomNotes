package com.example.roomnotes // Pastikan packagenya ini, BUKAN .viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

// Import file dari folder lain
import com.example.roomnotes.data.AppDatabase
import com.example.roomnotes.data.NoteRepository
import com.example.roomnotes.viewmodel.NoteViewModel
import com.example.roomnotes.viewmodel.NoteViewModelFactory
import com.example.roomnotes.ui.theme.RoomNotesTheme // Pastikan folder ui.theme kamu ada

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(applicationContext)
        val repo = NoteRepository(db.noteDao())
        val factory = NoteViewModelFactory(repo)

        setContent {
            RoomNotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val vm: NoteViewModel = viewModel(factory = factory)
                    NoteScreen(vm)
                }
            }
        }
    }
}