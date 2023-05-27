package pt.ulusofona.cm.lotrcharactersoffline

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pt.ulusofona.cm.lotrcharactersoffline.fragments.ListFragment
import pt.ulusofona.cm.lotrcharactersoffline.fragments.HomeFragment
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter

object NavigationManager {

  private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
    val transition = fm.beginTransaction()
    transition.replace(R.id.frame, fragment)
    transition.addToBackStack(null)
    transition.commit()
  }

  fun goToHomeFragment(fm: FragmentManager) {
    placeFragment(fm, HomeFragment())
  }

  fun goToListFragment(fm: FragmentManager, items: List<String>) {
    placeFragment(fm, ListFragment.newInstance(items))
  }

}