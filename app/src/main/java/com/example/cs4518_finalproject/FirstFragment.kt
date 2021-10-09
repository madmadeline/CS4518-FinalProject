package com.example.cs4518_finalproject

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.cs4518_finalproject.databinding.FragmentFirstBinding
import java.io.File

private const val TAG = "GameFragment"

class FirstFragment : Fragment() {

    private lateinit var myMoth: Moth
    private lateinit var otherMoth: Moth

    private lateinit var greeting: TextView
    private lateinit var loveMessageButton: Button
    private lateinit var peaceMessageButton: Button
    private lateinit var supportMessageButton: Button
    private lateinit var hugsMessageButton: Button

    private lateinit var otherMothName: TextView
    private lateinit var otherMothLocation: TextView
    private lateinit var otherMothMessage: Button

    private val homeIndex = 0
    private val awayIndex = 1

    private val mothViewModel: MothViewModel by lazy {
        ViewModelProviders.of(this).get(MothViewModel::class.java)
    }

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // binding.buttonFirst.setOnClickListener {
        //    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        //}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}