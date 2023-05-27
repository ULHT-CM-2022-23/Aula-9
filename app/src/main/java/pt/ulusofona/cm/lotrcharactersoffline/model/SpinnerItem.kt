package pt.ulusofona.cm.lotrcharactersoffline.model

data class SpinnerItem(
  val id: String,
  val content: String
) {

  override fun toString(): String {
    return content
  }

}