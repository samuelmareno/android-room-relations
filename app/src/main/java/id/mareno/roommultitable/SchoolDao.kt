package id.mareno.roommultitable

import androidx.room.*
import id.mareno.roommultitable.entities.Director
import id.mareno.roommultitable.entities.School
import id.mareno.roommultitable.entities.Student
import id.mareno.roommultitable.entities.relations.SchoolAndDirector
import id.mareno.roommultitable.entities.relations.SchoolWithStudents

@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolAndDirectorWithSchoolName(schoolName: String): List<SchoolAndDirector>

    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolsWithStudents(schoolName: String): List<SchoolWithStudents>
}