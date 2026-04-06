package com.example.automarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Screen 3 — Success/completion screen shown after payment.
 * "Go To First Page" pops the entire back stack to return to ShopFragment.
 */
class CompletionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_completion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnGoToFirst = view.findViewById<Button>(R.id.btnGoToFirst)

        // Pop entire back stack to return to the first fragment (ShopFragment)
        btnGoToFirst.setOnClickListener {
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}
