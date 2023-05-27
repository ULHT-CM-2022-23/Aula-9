package pt.ulusofona.cm.lotrcharactersoffline.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LOTRCharacterDB::class, LOTRMovieDB::class, LOTRMovieCharacterDB::class], version = 1)
abstract class LOTRDatabase: RoomDatabase() {

  abstract fun charactersDao(): LOTRCharacterDao
  abstract fun moviesDao(): LOTRMovieDao

  companion object {
    private var instance: LOTRDatabase? = null

    fun getInstance(applicationContext: Context): LOTRDatabase {
      synchronized(this) {
        if (instance == null) {
          instance = Room.databaseBuilder(
            applicationContext,
            LOTRDatabase::class.java,
            "lotr_db"
          ).build()
        }
        return instance as LOTRDatabase
      }
    }
  }
}