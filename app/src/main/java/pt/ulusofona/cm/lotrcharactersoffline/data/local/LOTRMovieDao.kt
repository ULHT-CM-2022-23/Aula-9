package pt.ulusofona.cm.lotrcharactersoffline.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface LOTRMovieDao {

  @Insert
  suspend fun insert(movie: LOTRMovieDB)

  @Query("SELECT * FROM movies")
  suspend fun getAll(): List<LOTRMovieDB>

  @Query("SELECT * FROM movies WHERE movieId = :id")
  suspend fun getById(id: String): LOTRMovieDB

  @Query("DELETE FROM movies")
  suspend fun deleteAll()

  @Transaction
  @Query("SELECT * FROM movies")
  fun getMovieWithCharacters(): List<LOTRMovieWithCharactersDB>

  @Insert
  suspend fun insertCharacterOnMovie(association: LOTRMovieCharacterDB)

}