package pt.ulusofona.cm.lotrcharactersoffline.model

data class LOTRMovie(
  val id: String,
  val name: String,
  val budgetInMillions: Int,
  val characters: List<LOTRCharacter> = listOf()
)