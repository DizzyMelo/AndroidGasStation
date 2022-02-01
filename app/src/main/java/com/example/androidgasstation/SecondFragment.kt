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
    val gas = Gas(1, "Unleaded", 3.50)

    var fuel = Thread(Runnable {
        while (true) {

        }
    })

    lateinit var rootView: View
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

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mAction: Runnable = object : Runnable {
            override fun run() {
                (mContext as Activity).runOnUiThread {
                    Thread.sleep(Metrics.PUMPER_SPEED.toLong())
                    total += Metrics.AMOUNT_SPEED_RATIO

                    println(String.format("%.2f", ( total)) + " gal" + " - $" + String.format("%.2f", (gas.price * total)))

                    binding.textviewGallons.text = String.format("%.2f", ( total)) + " gal"
                    binding.textviewPrice.text   = " - $" + String.format("%.2f", (gas.price * total))
                }
                mHandler.postDelayed(this, 500)
            }
        }

        binding.buttonSecond.setOnClickListener {
            fueling = !fueling

            if (fueling) {
                mHandler.postDelayed(mAction, 150)
            }else{
                mHandler.removeCallbacksAndMessages(null)
            }

        }

//        binding.buttonSecond.setOnTouchListener { v, event ->
//
//
//            when (event?.action) {
//                MotionEvent.ACTION_DOWN -> mHandler.postDelayed(mAction, 150)
//                MotionEvent.ACTION_UP -> {
//                    mHandler.removeCallbacks(mAction)
//                }
//            }
//
//            v?.onTouchEvent(event) ?: true
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}