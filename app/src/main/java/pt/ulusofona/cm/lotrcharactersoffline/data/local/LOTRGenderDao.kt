package pt.ulusofona.cm.lotrcharactersoffline.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LOTRGenderDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(gender: LOTRGenderDB)

  @Query("SELECT * FROM genders")
  suspend fun getAll(): List<LOTRGenderDB>

  @Query("SELECT * FROM genders WHERE name = :name")
  suspend fun getGender(name: String): LOTRGenderDB?

  @Query("DELETE FROM genders")
  suspend fun deleteAll()

}