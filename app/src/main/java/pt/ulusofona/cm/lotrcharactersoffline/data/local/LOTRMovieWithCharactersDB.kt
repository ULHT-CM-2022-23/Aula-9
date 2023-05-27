package pt.ulusofona.cm.lotrcharactersoffline.data.local

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class LOTRMovieWithCharactersDB(
  @Embedded val movie: LOTRMovieDB,
  @Relation(
    parentColumn = "movieId",
    entityColumn = "characterId",
    associateBy = Junction(LOTRMovieCharacterDB::class)
  )
  val characters: List<LOTRCharacterDB>
)