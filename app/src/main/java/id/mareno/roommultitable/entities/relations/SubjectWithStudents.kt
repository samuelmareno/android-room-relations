package id.mareno.roommultitable.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import id.mareno.roommultitable.entities.Student
import id.mareno.roommultitable.entities.Subject

data class SubjectWithStudents(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectName",
        entityColumn = "studentName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val students: List<Student>
)
