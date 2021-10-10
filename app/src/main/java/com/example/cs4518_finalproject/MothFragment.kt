package com.example.cs4518_finalproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cs4518_finalproject.databinding.FragmentFirstBinding

private const val TAG = "FirstFragment"
private const val USER_MESSAGE = "User Message"
//private const val OTHER_USER_MESSAGE = "Other User Message"
private const val USER_COUNTRY = "Narnia"
private var OTHER_USERNAME = "Arthur"
//private const val ARG_GAME_ID = "game_id"
//private const val ARG_LAT = "lat"
//private const val ARG_LON = "lon"
//private const val REQUEST_PHOTO = 2

class MothFragment : Fragment() {

    private lateinit var myMoth: Moth
    private lateinit var otherMoth: Moth

    private lateinit var greeting: TextView
    private lateinit var loveMessageButton: Button
    private lateinit var peaceMessageButton: Button
    private lateinit var supportMessageButton: Button
    private lateinit var hopeMessageButton: Button

    private lateinit var otherMothName: TextView
    private lateinit var otherMothLocation: TextView
    private lateinit var otherMothMessage: Button

    /*
     * INDICES
     */
    private val userIndex = 0
    private val otherUserIndex = 1
    private val loveIndex = 0
    private val peaceIndex = 1
    private val supportIndex = 2
    private val hopeIndex = 3


    private val mothViewModel: MothViewModel by lazy {
        ViewModelProviders.of(this).get(MothViewModel::class.java)
    }

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var names = arrayOf("Annie", "Bjorn", "Choi", "Dmitriy", "Eiko", "Filio")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        myMoth = Moth()
//        otherMoth = Moth()
    }

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.moth_screen, container, false)



        /*
         UPDATE USER MOTH
         */
        val userMessage = savedInstanceState?.getInt(USER_MESSAGE, 0) ?: 0
        mothViewModel.mothsDatabase[userIndex].message = mothViewModel.messagesDatabase[userMessage]

        /*
         UPDATE OTHER MOTH WITH RANDOM DATA
         */
        val randomMessage = mothViewModel.messagesDatabase[(0..3).random()]
        val randomName =names[(0..5).random()]
        OTHER_USERNAME = randomName
        mothViewModel.mothsDatabase[otherUserIndex].message = randomMessage
//        mothViewModel.mothsDatabase[otherUserIndex].username = randomName

        /*
         GET THE VIEWMODEL
         */
        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val mothViewModel = provider.get(MothViewModel::class.java)
        Log.d(TAG, "Got a MothViewModel: $mothViewModel")

        /*
         WIRING UP WIDGETS
         */
        greeting = view.findViewById(R.id.textView_youSent)
        loveMessageButton = view.findViewById(R.id.button_message)
        peaceMessageButton = view.findViewById(R.id.button_peace)
        supportMessageButton = view.findViewById(R.id.button_support)
        hopeMessageButton = view.findViewById(R.id.button_hope)
        otherMothName = view.findViewById(R.id.textView_otherUser)
        otherMothLocation = view.findViewById(R.id.textView_otherLoc)
        otherMothMessage = view.findViewById(R.id.button_otherMessage)

        /*
         SHOW OTHER USER MESSAGE
         */
        val otherUserMessage = mothViewModel.mothsDatabase[otherUserIndex].message
        otherMothMessage.setText(otherUserMessage.text)
//        Log.d(TAG, "message: " + otherUserMessage.text)
        Log.d(TAG, "random message: " + randomMessage.text)
//        Log.d(TAG, "color: " + otherUserMessage.color)
//        Log.d(TAG, ""+ Color.parseColor("#22aadd"))
//        Log.d(TAG, "parsing color: " + "#8bc34a️".toColorInt())

        Log.d(TAG, "random name: " + randomName)
        otherMothMessage.setBackgroundColor(otherUserMessage.color)

        otherMothName.setText(randomName)

        Log.d(TAG, "name changed? " + mothViewModel.mothsDatabase[otherUserIndex].username)

        /*
         LISTENERS
         */
        loveMessageButton.setOnClickListener { view: View ->
            mothViewModel.mothsDatabase[userIndex].updateMessage(
                mothViewModel.messagesDatabase[loveIndex]
            )
            Log.d(TAG, "sending love...")
            // todo toast or separate activity
            // go to SendActivity and send intent 'love'
            val intent = SendActivity.newIntent(context, "Love", OTHER_USERNAME)
            startActivity(intent)
        }

        peaceMessageButton.setOnClickListener { view: View ->
            mothViewModel.mothsDatabase[userIndex].updateMessage(
                mothViewModel.messagesDatabase[peaceIndex]
            )
            Log.d(TAG, "sending peace...")
            // todo toast or separate activity
            // go to SendActivity and send intent 'peace'
            val intent = SendActivity.newIntent(context, "Peace", OTHER_USERNAME)
            startActivity(intent)
        }

        supportMessageButton.setOnClickListener { view: View ->
            mothViewModel.mothsDatabase[userIndex].updateMessage(
                mothViewModel.messagesDatabase[supportIndex]
            )
            Log.d(TAG, "sending support...")
            // todo toast or separate activity
            // go to SendActivity and send intent 'support'
            val intent = SendActivity.newIntent(context, "Support", OTHER_USERNAME)
            startActivity(intent)
        }

        hopeMessageButton.setOnClickListener { view: View ->
            mothViewModel.mothsDatabase[userIndex].updateMessage(
                mothViewModel.messagesDatabase[hopeIndex]
            )
            Log.d(TAG, "sending hope...")
            // todo toast or separate activity
            // go to SendActivity and send intent 'hope'
            val intent = SendActivity.newIntent(context, "Hope", OTHER_USERNAME)
            startActivity(intent)
        }

//        return binding.root
        return view
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

    override fun onSaveInstanceState(savedInstanceState: Bundle){
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(USER_MESSAGE,
            mothViewModel.messagesDatabase.indexOf(mothViewModel.messagesDatabase[userIndex]))
    }
}