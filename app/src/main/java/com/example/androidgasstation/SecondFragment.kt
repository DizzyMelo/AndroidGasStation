package com.example.androidgasstation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.FileUriExposedException
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidgasstation.databinding.FragmentSecondBinding
import com.example.androidgasstation.entities.Gas
import com.example.androidgasstation.utils.Metrics

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private var fueling: Boolean = false

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var total = 0.0

    lateinit var mContext: Context

    var mHandler = Handler(Looper.getMainLooper())

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        print(arguments?.getInt("position"))
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt("position")
        val gas = Gas.gasOptions[position!!]
        val mAction: Runnable = object : Runnable {
            override fun run() {
                (mContext as Activity).runOnUiThread {
//                    Update the UI in a another thread
                    total += Metrics.AMOUNT_SPEED_RATIO
                    binding.textviewGallons.text = String.format("%.2f", ( total)) + " gal"
                    binding.textviewPrice.text   = "$" + String.format("%.2f", (gas.price * total))
                }
                mHandler.postDelayed(this, Metrics.PUMPER_SPEED.toLong())
            }
        }

        binding.textviewGas.text = gas.name

        binding.buttonSecond.setOnClickListener {
//            Invert the value of the fueling variable
            fueling = !fueling

            if (fueling) {
//                change the button text
                binding.buttonSecond.text = "STOP"
                mHandler.postDelayed(mAction, 150)
            }else{
//                change the button text
                binding.buttonSecond.text = "START"
                mHandler.removeCallbacksAndMessages(null)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}