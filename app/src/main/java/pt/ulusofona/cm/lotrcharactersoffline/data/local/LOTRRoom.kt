package pt.ulusofona.cm.lotrcharactersoffline.data.local

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTR
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRMovie

class LOTRRoom(private val charactersDao: LOTRCharacterDao, private val moviesDao: LOTRMovieDao): LOTR() {

  override fun insertCharacters(characters: List<LOTRCharacter>, onFinished: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      characters.map {
        LOTRCharacterDB(it.id, it.birth, it.death, it.gender, it.name)
      }.forEach {
        charactersDao.insert(it)
        Log.i("APP", "Inserted ${it.name} in DB")
      }
      onFinished()
    }
  }

  override fun getCharacters(onFinished: (Result<List<LOTRCharacter>>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      val characters = charactersDao.getAll().map {
        LOTRCharacter(it.characterId, it.birth, it.death, it.gender, it.name)
      }
      onFinished(Result.success(characters))
    }
  }

  override fun getMoviesWithCharacters(onFinished: (Result<List<LOTRMovie>>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      val movies = moviesDao.getMovieWithCharacters().map {
        val characters = it.characters.map { LOTRCharacter(
          it.characterId, it.birth, it.death, it.gender, it.name
        )}
        LOTRMovie(
          it.movie.movieId, it.movie.name, it.movie.budgetInMillions, characters
        )
      }
      onFinished(Result.success(movies))
    }
  }

  override fun insertCharacterOnMovie(movieId: String, characterId: String, onFinished: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      val association = LOTRMovieCharacterDB(characterId, movieId)
      moviesDao.insertCharacterOnMovie(association)
      onFinished()
    }
  }

  override fun clearAllCharacters(onFinished: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      charactersDao.deleteAll()
      onFinished()
    }
  }

  override fun getMovies(onFinished: (Result<List<LOTRMovie>>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      val characters = moviesDao.getAll().map {
        LOTRMovie(
          it.movieId, it.name, it.budgetInMillions
        )
      }
      onFinished(Result.success(characters))
    }
  }

  override fun insertMovies(movies: List<LOTRMovie>, onFinished: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      movies.map {
        LOTRMovieDB(it.id, it.name, it.budgetInMillions)
      }.forEach {
        moviesDao.insert(it)
        Log.i("APP", "Inserted ${it.name} in DB")
      }
      onFinished()
    }
  }

  override fun clearAllMovies(onFinished: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      moviesDao.deleteAll()
      onFinished()
    }
  }
}