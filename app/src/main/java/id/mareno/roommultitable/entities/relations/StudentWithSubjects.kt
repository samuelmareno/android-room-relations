package id.mareno.roommultitable.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import id.mareno.roommultitable.entities.Student
import id.mareno.roommultitable.entities.Subject

data class StudentWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentName",
        entityColumn = "subjectName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjects: List<Subject>
)
