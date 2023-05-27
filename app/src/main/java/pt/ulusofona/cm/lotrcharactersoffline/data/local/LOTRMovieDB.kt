package pt.ulusofona.cm.lotrcharactersoffline.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
class LOTRMovieDB(
  @PrimaryKey val movieId: String,
  val name: String,
  @ColumnInfo(name = "budget_in_millions") val budgetInMillions: Int
)