package pt.ulusofona.cm.lotrcharactersoffline.data.local

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTR
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter

class LOTRRoom(private val charactersDao: LOTRCharacterDao, private val genderDao: LOTRGenderDao): LOTR() {

  override fun insertCharacters(characters: List<LOTRCharacter>, onFinished: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      characters.forEach {
        getOrInsertGender(it.gender) { result ->
          if(result.isSuccess) {
            val character = LOTRCharacterDB(it.id, it.birth, it.death, it.name,
              result.getOrDefault(LOTRCharacter.Gender.UNKNOWN).name
            )
            charactersDao.insert(character)
            Log.i("APP", "Inserted ${it} in DB")
          }
        }
      }
      onFinished()
    }
  }

  override fun getCharacters(onFinished: (Result<List<LOTRCharacter>>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      val characters = mutableListOf<LOTRCharacter>()
      val roomCharacters = charactersDao.getAll()
      roomCharacters.forEach {
        val gender = genderDao.getGender(it.gender)
        val character = LOTRCharacter(
          it.characterId, it.birth, it.death,
          LOTRCharacter.Gender.valueOf(gender!!.name), it.name
        )
        characters.add(character)
      }
      onFinished(Result.success(characters))
    }
  }

  override fun clearAllCharacters(onFinished: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      charactersDao.deleteAll()
      onFinished()
    }
  }

  override fun getOrInsertGender(gender: LOTRCharacter.Gender, onFinished: (Result<LOTRCharacter.Gender>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      val genderFetched = genderDao.getGender(gender.name)
      if(genderFetched == null) genderDao.insert(LOTRGenderDB(gender.name))
      onFinished(Result.success(gender))
    }
  }

  override fun getGenders(onFinished: (Result<List<LOTRCharacter.Gender>>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      val roomGenders = genderDao.getAll()
      val genders = roomGenders.map { LOTRCharacter.Gender.valueOf(it.name.uppercase()) }
      onFinished(Result.success(genders))
    }
  }

}