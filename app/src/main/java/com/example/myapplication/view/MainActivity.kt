package com.example.myapplication.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.view.fragment.SecondFragment
import com.example.myapplication.view.fragment.TimeFragment
import com.example.myapplication.view.fragment.TimeFragment.TextChangeListener


/***
 * This activity uses to test fragment
 */
class MainActivity : AppCompatActivity(), TextChangeListener{
    private lateinit var timeFragment : TimeFragment
    private lateinit var secondFragment: SecondFragment
    private lateinit var binding: ActivityMainBinding
    private var saveData = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bundle = Bundle()
        bundle.putInt("KeyToFragment", 1000)

        //init fragment when view was created
        timeFragment = TimeFragment()
        secondFragment = SecondFragment.newInstance("param1","param2")
        //use FragmentManager to manager fragment
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_right_to_left,  // enter
                R.anim.exit_right_to_left,  // exit
                R.anim.enter_left_to_right,   // popEnter
                R.anim.exit_left_to_right  // popExit
            )
            .setReorderingAllowed(true)
            .replace(R.id.fragment_container_view , timeFragment)
            .replace(R.id.fragment_container_view , secondFragment)
            .commit()

        //when you receive onCLick
        binding.btnFrag1.setOnClickListener {
                timeFragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.enter_right_to_left,  // enter
                        R.anim.exit_right_to_left,  // exit
                        R.anim.enter_left_to_right,   // popEnter
                        R.anim.exit_left_to_right  // popExit
                    )
                    .replace(R.id.fragment_container_view , timeFragment)
                    .addToBackStack(null)
                    .commit()
        }

        binding.btnFrag2.setOnClickListener {

                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.enter_right_to_left,  // enter
                        R.anim.exit_right_to_left,  // exit
                        R.anim.enter_left_to_right,   // popEnter
                        R.anim.exit_left_to_right  // popExit
                    )
                    .replace(R.id.fragment_container_view, secondFragment)
                    .addToBackStack(null)
                    .commit()
        }

//        binding.btnFrag3.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_container_view, ThirdFragment())
//                .commit()
//        }
    }

    override fun onTextChange(newText: String?) {
        secondFragment.updateTextValue(newText)
    }
}