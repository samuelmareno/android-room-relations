package id.mareno.roommultitable

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.mareno.roommultitable.entities.Director
import id.mareno.roommultitable.entities.School
import id.mareno.roommultitable.entities.Student
import id.mareno.roommultitable.entities.Subject
import id.mareno.roommultitable.entities.relations.StudentSubjectCrossRef

@Database(
    entities = [
        School::class,
        Director::class,
        Student::class,
        Subject::class,
        StudentSubjectCrossRef::class
    ],
    version = 1
)
abstract class SchoolDatabase : RoomDatabase() {

    abstract val schoolDao: SchoolDao

    companion object {
        private const val DATABASE_NAME = "school_db"
        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getInstance(context: Context): SchoolDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SchoolDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
                return instance
            }
        }
    }
}