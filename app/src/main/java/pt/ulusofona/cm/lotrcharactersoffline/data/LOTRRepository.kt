package pt.ulusofona.cm.lotrcharactersoffline.data

import android.content.Context
import android.util.Log
import pt.ulusofona.cm.lotrcharactersoffline.ConnectivityUtil
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTR
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter

// O LOTRRepository herda de LOTR e recebe dois models do tipo LOTR
// Um para a base de dados local (exercício) e outro para o web service
class LOTRRepository(
  private val context: Context,
  private val local: LOTR,
  private val remote: LOTR
) : LOTR() {

  override fun insertCharacters(characters: List<LOTRCharacter>, onFinished: () -> Unit) {
    TODO("Not yet implemented")
  }

  override fun clearAllCharacters(onFinished: () -> Unit) {
    TODO("Not yet implemented")
  }

  override fun getOrInsertGender(gender: LOTRCharacter.Gender, onFinished: (Result<LOTRCharacter.Gender>) -> Unit) {
    TODO("Not yet implemented")
  }

  override fun getGenders(onFinished: (Result<List<LOTRCharacter.Gender>>) -> Unit) {
    TODO("Not yet implemented")
  }

  override fun getCharacters(onFinished: (Result<List<LOTRCharacter>>) -> Unit) {
    if (ConnectivityUtil.isOnline(context)) {
      // Se tenho acesso à Internet, vou buscar os registos ao web service
      // e atualizo a base de dados com os novos registos eliminando os
      // antigos, porque podem ter eliminado personagens do web service
      remote.getCharacters { result ->
        if (result.isSuccess) {
          result.getOrNull()
            ?.let { characters ->
              // Se tiver personagens para apresentar entra aqui
              Log.i("APP", "Got ${characters.size} characters from the server")
              // Retirar esta linha quando forem fazer o exercício 1 da ficha
              onFinished(Result.success(characters))
              local.clearAllCharacters {
                Log.i("APP", "Cleared DB")
                local.insertCharacters(characters) {
                  onFinished(Result.success(characters))
                }
              }
            }
        } else {
          Log.w("APP", "Error getting characters from server")
          onFinished(result)  // propagate the remote failure
        }
      }

      // Continua no próximo quadro
    } else {
      // O que fazer se não houver Internet?
      // Devolver os personagens que estão guardados na base de dados
      Log.i("APP", "App is offline. Getting characters from the database")
      local.getCharacters(onFinished)
    }
  }

  // Adicionar no final da classe LOTRRepository
  companion object {

    private var instance: LOTRRepository? = null

    // Temos de executar o init antes do getInstance
    fun init(context: Context, local: LOTR, remote: LOTR) {
      if (instance == null) {
        instance = LOTRRepository(context, local, remote)
      }
    }

    fun getInstance(): LOTRRepository {
      if (instance == null) {
        // Primeiro temos de invocar o init, se não lança esta Exception
        throw IllegalStateException("singleton not initialized")
      }
      return instance as LOTRRepository
    }

  }
}
