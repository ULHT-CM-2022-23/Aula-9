package pt.ulusofona.cm.lotrcharactersoffline.model

abstract class LOTR {
    abstract fun getCharacters(onFinished: (Result<List<LOTRCharacter>>) -> Unit)
    abstract fun getMoviesWithCharacters(onFinished: (Result<List<LOTRMovie>>) -> Unit)
    abstract fun insertCharacterOnMovie(movieId: String, characterId: String, onFinished: () -> Unit)
    abstract fun insertCharacters(characters: List<LOTRCharacter>, onFinished: () -> Unit)
    abstract fun clearAllCharacters(onFinished: () -> Unit)
    abstract fun getMovies(onFinished: (Result<List<LOTRMovie>>) -> Unit)
    abstract fun insertMovies(movies: List<LOTRMovie>, onFinished: () -> Unit)
    abstract fun clearAllMovies(onFinished: () -> Unit)

}