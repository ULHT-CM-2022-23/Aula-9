package pt.ulusofona.cm.lotrcharactersoffline

import android.app.Application
import android.util.Log
import okhttp3.OkHttpClient
import pt.ulusofona.cm.lotrcharactersoffline.data.local.LOTRDatabase
import pt.ulusofona.cm.lotrcharactersoffline.data.remote.LOTROkHttp
import pt.ulusofona.cm.lotrcharactersoffline.data.LOTRRepository
import pt.ulusofona.cm.lotrcharactersoffline.data.local.LOTRRoom

class LOTRApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    LOTRRepository.init(this, initLOTRRoom(), initLOTROkHttp())
    Log.i("APP", "Initialized repository")
  }

  private fun initLOTROkHttp(): LOTROkHttp {
    return LOTROkHttp(
      "https://the-one-api.dev/v2/",
      "F0CbZchv85z1vep9Nr1G",
      OkHttpClient()
    )
  }

  // TODO substituir aqui a inicialização do Room
  private fun initLOTRRoom(): LOTRRoom {
    return LOTRRoom(
      LOTRDatabase.getInstance(applicationContext).charactersDao(),
      LOTRDatabase.getInstance(applicationContext).gendersDao()
    )
  }

}
