package com.example.automarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

/**
 * Screen 1 — Displays a 2x2 grid of car cards.
 * Each card click navigates to PaymentFragment with car details passed via Bundle.
 */
class ShopFragment : Fragment() {

    companion object {
        const val KEY_CAR_NAME = "KEY_CAR_NAME"
        const val KEY_CAR_PRICE = "KEY_CAR_PRICE"
        const val KEY_CAR_IMAGE = "KEY_CAR_IMAGE"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCarCard(view)
    }

    /**
     * Attaches click listeners to each car card in the grid.
     */
    private fun setupCarCard(view: View) {
        val cardBmw = view.findViewById<LinearLayout>(R.id.cardBmw)
        val cardMercedes = view.findViewById<LinearLayout>(R.id.cardMercedes)
        val cardPorsche = view.findViewById<LinearLayout>(R.id.cardPorsche)
        val cardFerrari = view.findViewById<LinearLayout>(R.id.cardFerrari)

        cardBmw.setOnClickListener {
            navigateToPayment("BMW M3 (F80 generation)", 38000.0, R.drawable.bg_car_bmw)
        }

        cardMercedes.setOnClickListener {
            navigateToPayment("Mercedes-Benz CLA-Class (Second Generation)", 46400.0, R.drawable.bg_car_mercedes)
        }

        cardPorsche.setOnClickListener {
            navigateToPayment("Porsche 911 GT3 RS (991.1 Generation)", 189000.0, R.drawable.bg_car_porsche)
        }

        cardFerrari.setOnClickListener {
            navigateToPayment("Ferrari 488 Spider", 260000.0, R.drawable.bg_car_ferrari)
        }
    }

    /**
     * Navigates to PaymentFragment, passing car name, price, and image resource via Bundle.
     */
    private fun navigateToPayment(carName: String, carPrice: Double, imageResId: Int) {
        val bundle = Bundle().apply {
            putString(KEY_CAR_NAME, carName)
            putDouble(KEY_CAR_PRICE, carPrice)
            putInt(KEY_CAR_IMAGE, imageResId)
        }

        val paymentFragment = PaymentFragment().apply {
            arguments = bundle
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, paymentFragment)
            .addToBackStack(null)
            .commit()
    }
}
