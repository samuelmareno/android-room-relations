package id.mareno.roommultitable.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import id.mareno.roommultitable.entities.School
import id.mareno.roommultitable.entities.Student

data class SchoolWithStudents(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val students: List<Student>
)
