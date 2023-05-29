package pt.ulusofona.cm.lotrcharactersoffline.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genders")
class LOTRGenderDB(
  @PrimaryKey val name: String
)