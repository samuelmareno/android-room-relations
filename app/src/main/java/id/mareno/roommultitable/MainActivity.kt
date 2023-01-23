package id.mareno.roommultitable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import id.mareno.roommultitable.entities.Director
import id.mareno.roommultitable.entities.School
import id.mareno.roommultitable.entities.Student
import id.mareno.roommultitable.entities.Subject
import id.mareno.roommultitable.entities.relations.StudentSubjectCrossRef
import id.mareno.roommultitable.ui.theme.RoomMultiTableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = SchoolDatabase.getInstance(this).schoolDao

        setContent {
            RoomMultiTableTheme {
                // A surface container using the 'background' color from the theme

                LaunchedEffect("") {
                    val schools = listOf(
                        School("Jake Wharton School"),
                        School("Kotlin School"),
                        School("JetBrains School")
                    )
                    val directors = listOf(
                        Director("Mike Litoris", "Jake Wharton School"),
                        Director("Jack Goff", "Kotlin School"),
                        Director("Chris P. Chicken", "JetBrains School")
                    )
                    val subjects = listOf(
                        Subject("Dating for programmers"),
                        Subject("Avoiding depression"),
                        Subject("Bug Fix Meditation"),
                        Subject("Logcat for Newbies"),
                        Subject("How to use Google")
                    )
                    val students = listOf(
                        Student("Beff Jezos", 2, "Kotlin School"),
                        Student("Mark Suckerberg", 5, "Jake Wharton School"),
                        Student("Gill Bates", 8, "Kotlin School"),
                        Student("Donny Jepp", 1, "Kotlin School"),
                        Student("Hom Tanks", 2, "JetBrains School")
                    )
                    val studentSubjectRelations = listOf(
                        StudentSubjectCrossRef("Beff Jezos", "Dating for programmers"),
                        StudentSubjectCrossRef("Beff Jezos", "Avoiding depression"),
                        StudentSubjectCrossRef("Beff Jezos", "Bug Fix Meditation"),
                        StudentSubjectCrossRef("Beff Jezos", "Logcat for Newbies"),
                        StudentSubjectCrossRef("Mark Suckerberg", "Dating for programmers"),
                        StudentSubjectCrossRef("Gill Bates", "How to use Google"),
                        StudentSubjectCrossRef("Donny Jepp", "Logcat for Newbies"),
                        StudentSubjectCrossRef("Hom Tanks", "Avoiding depression"),
                        StudentSubjectCrossRef("Hom Tanks", "Dating for programmers")
                    )

                    directors.forEach { dao.insertDirector(it) }
                    schools.forEach { dao.insertSchool(it) }
                    subjects.forEach { dao.insertSubject(it) }
                    students.forEach { dao.insertStudent(it) }
                    studentSubjectRelations.forEach { dao.insertStudentSubjectCrossRef(it) }

                    dao.getSchoolAndDirectorWithSchoolName("Jake Wharton School").forEach {
                        println("School: ${it.school.schoolName}")
                        println("Director: ${it.director.directorName}")
                    }

                    dao.getSchoolWithStudents("Kotlin School").forEach {
                        println("School: ${it.school.schoolName}")
                        it.students.forEach { student ->
                            println("Student: ${student.studentName}")
                        }
                    }
                    dao.getStudentsOfSubject("Dating for programmers").forEach {
                        println("Subject: ${it.subject.subjectName}")
                        println("Student: ${it.students.map { student -> student.studentName }}")
                    }

                    dao.getSubjectsOfStudent("Beff Jezos").forEach {
                        println("Student: ${it.student.studentName}")
                        println("Subject: ${it.subjects.map { subject -> subject.subjectName }}")
                    }
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Text(text = "Room Relation Database")
                }
            }
        }
    }
}