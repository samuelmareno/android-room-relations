package id.mareno.roommultitable.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Director(
    @PrimaryKey(autoGenerate = false)
    val directorName: String,
    val schoolName: String
)
