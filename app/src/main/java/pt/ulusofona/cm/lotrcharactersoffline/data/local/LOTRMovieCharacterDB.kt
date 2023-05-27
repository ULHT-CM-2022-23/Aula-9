package pt.ulusofona.cm.lotrcharactersoffline.data.local

import androidx.room.Entity

@Entity(tableName = "characters_movies", primaryKeys = ["characterId", "movieId"])
class LOTRMovieCharacterDB(
  val characterId: String,
  val movieId: String
)