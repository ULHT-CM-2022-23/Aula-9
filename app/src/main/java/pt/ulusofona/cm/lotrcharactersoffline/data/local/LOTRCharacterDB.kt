package pt.ulusofona.cm.lotrcharactersoffline.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class LOTRCharacterDB(
  @PrimaryKey val characterId: String,
  val birth: String,
  val death: String,
  val name: String,
  val gender: String  // gender is optional
)