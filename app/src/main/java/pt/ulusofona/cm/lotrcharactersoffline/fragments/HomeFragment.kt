package pt.ulusofona.cm.lotrcharactersoffline.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.lotrcharactersoffline.NavigationManager
import pt.ulusofona.cm.lotrcharactersoffline.R
import pt.ulusofona.cm.lotrcharactersoffline.data.LOTRRepository
import pt.ulusofona.cm.lotrcharactersoffline.databinding.FragmentHomeBinding
import pt.ulusofona.cm.lotrcharactersoffline.model.SpinnerItem

class HomeFragment : Fragment() {

  private lateinit var binding: FragmentHomeBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_home, container, false)
    binding = FragmentHomeBinding.bind(view)
    return binding.root
  }

  override fun onStart() {
    super.onStart()
    binding.getCharactersBtn.setOnClickListener {
      LOTRRepository.getInstance().getCharacters { result ->
        if(result.isSuccess) {
          val names = result.getOrDefault(mutableListOf()).map { it.name }
          NavigationManager.goToListFragment(parentFragmentManager, names)
        } else {
          Toast.makeText(requireContext(), result.exceptionOrNull()?.message, Toast.LENGTH_LONG).show()
        }
      }
    }
    /*
    binding.getMoviesBtn.setOnClickListener {
      LOTRRepository.getInstance().getMovies { result ->
        if(result.isSuccess) {
          val names = result.getOrDefault(mutableListOf()).map { it.name }
          NavigationManager.goToListFragment(parentFragmentManager, names)
        } else {
          Toast.makeText(requireContext(), result.exceptionOrNull()?.message, Toast.LENGTH_LONG).show()
        }
      }
    }
    binding.getMoviesWithCharacters.setOnClickListener {
      LOTRRepository.getInstance().getMoviesWithCharacters { result ->
        if(result.isSuccess) {
          val names = result.getOrDefault(mutableListOf()).map {
            val sb = StringBuilder()
            it.characters.forEach{ sb.append("  - ${it.name}\n") }
            "${it.name}\n${sb}"
          }
          NavigationManager.goToListFragment(parentFragmentManager, names)
        } else {
          Toast.makeText(requireContext(), result.exceptionOrNull()?.message, Toast.LENGTH_LONG).show()
        }
      }
    }
    binding.associateBtn.setOnClickListener {
      val character = binding.charactersSp.selectedItem as SpinnerItem
      val movie = binding.moviesSp.selectedItem as SpinnerItem
      Log.i("APP", "character selected: $character")
      Log.i("APP", "movie selected: $character")
      LOTRRepository.getInstance().insertCharacterOnMovie(movie.id, character.id) {
        Log.i("APP", "$character is now registered on $movie")
      }
    }
     */
  }

  override fun onResume() {
    super.onResume()
    LOTRRepository.getInstance().getCharacters { result ->
      if(result.isSuccess) {
        CoroutineScope(Dispatchers.Main).launch {
          val items = result.getOrDefault(mutableListOf()).map { SpinnerItem(it.id, it.name) }
          Log.i("APP", "${items.size} characters to be loaded on the spinner")
          val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
          adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
          binding.charactersSp.adapter = adapter
        }
      }
    }
    /*
    LOTRRepository.getInstance().getMovies { result ->
      if(result.isSuccess) {
        CoroutineScope(Dispatchers.Main).launch {
          val items = result.getOrDefault(mutableListOf()).map { SpinnerItem(it.id, it.name) }
          Log.i("APP", "${items.size} movies to be loaded on the spinner")
          val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
          adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
          binding.moviesSp.adapter = adapter
        }
      }
    }
     */
  }

}