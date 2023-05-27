package pt.ulusofona.cm.lotrcharactersoffline.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ulusofona.cm.lotrcharactersoffline.SimpleListAdapter
import pt.ulusofona.cm.lotrcharactersoffline.R
import pt.ulusofona.cm.lotrcharactersoffline.databinding.FragmentCharactersListBinding
import pt.ulusofona.cm.lotrcharactersoffline.model.LOTRCharacter

class ListFragment : Fragment() {

    private lateinit var binding: FragmentCharactersListBinding
    private lateinit var items: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getStringArrayList("items")?.let {
            items = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_characters_list, container, false)
        binding = FragmentCharactersListBinding.bind(view)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.charactersListRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersListRv.adapter = SimpleListAdapter(items)
        binding.charactersListRv.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
    }

    companion object {

        fun newInstance(items: List<String>): ListFragment {
            return ListFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList("items", ArrayList(items))
                }
            }
        }

    }

}