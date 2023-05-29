package pt.ulusofona.cm.lotrcharactersoffline.data.local

import androidx.room.*

@Dao
interface LOTRCharacterDao {

  @Insert
  fun insert(character: LOTRCharacterDB)

  @Query("SELECT * FROM characters")
  fun getAll(): List<LOTRCharacterDB>

  @Query("SELECT * FROM characters WHERE characterId = :id")
  suspend fun getById(id: String): LOTRCharacterDB

  @Query("DELETE FROM characters")
  suspend fun deleteAll()

}