package id.mareno.roommultitable.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import id.mareno.roommultitable.entities.Director
import id.mareno.roommultitable.entities.School

data class SchoolAndDirector(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val director: Director
)
