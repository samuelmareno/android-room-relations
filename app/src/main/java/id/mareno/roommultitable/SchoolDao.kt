package id.mareno.roommultitable

import androidx.room.*
import id.mareno.roommultitable.entities.Director
import id.mareno.roommultitable.entities.School
import id.mareno.roommultitable.entities.relations.SchoolAndDirector

@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)

    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolAndDirectorWithSchoolName(schoolName: String): List<SchoolAndDirector>
}