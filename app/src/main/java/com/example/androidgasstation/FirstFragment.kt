package com.example.androidgasstation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.navigation.fragment.findNavController
import com.example.androidgasstation.adapters.GasOptionAdaper
import com.example.androidgasstation.databinding.FragmentFirstBinding
import com.example.androidgasstation.entities.Gas

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var listView: ListView
    lateinit var mContext: Context

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Select the list UI
        listView = _binding?.root!!.findViewById(R.id.listview_gas_type)
//        Instantiate the Adapter
        val adapter = GasOptionAdaper(mContext, Gas.gasOptions)
        listView.adapter = adapter

//        Navigate to second screen
        listView.setOnItemClickListener { parent, view, position, id ->
            val bundle = Bundle()
//            Add the position of the item selected.
            bundle.putInt("position", position)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}