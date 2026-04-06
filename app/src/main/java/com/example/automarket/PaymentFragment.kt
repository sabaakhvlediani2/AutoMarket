package com.example.automarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.NumberFormat
import java.util.Locale

/**
 * Screen 2 — Payment screen showing item details, discount, shipping options, and total.
 */
class PaymentFragment : Fragment() {

    companion object {
        const val DISCOUNT_RATE = 0.05
        const val EXPRESS_FEE = 1700.0
    }

    private var carName: String = ""
    private var carPrice: Double = 0.0
    private var carImage: Int = 0

    private var discountAmount: Double = 0.0
    private var discountedPrice: Double = 0.0
    private var shippingCost: Double = 0.0
    private var totalPrice: Double = 0.0

    private lateinit var tvTotalAmount: TextView
    private lateinit var radioStandard: RadioButton
    private lateinit var radioExpress: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve car data from Bundle
        arguments?.let {
            carName = it.getString(ShopFragment.KEY_CAR_NAME, "")
            carPrice = it.getDouble(ShopFragment.KEY_CAR_PRICE, 0.0)
            carImage = it.getInt(ShopFragment.KEY_CAR_IMAGE, 0)
        }

        // Calculate discount: 5% off the original price
        discountAmount = carPrice * DISCOUNT_RATE
        discountedPrice = carPrice * (1 - DISCOUNT_RATE)

        // Bind views
        val tvItemName = view.findViewById<TextView>(R.id.tvItemName)
        val tvItemPrice = view.findViewById<TextView>(R.id.tvItemPrice)
        val ivCarImage = view.findViewById<ImageView>(R.id.ivCarImage)
        tvTotalAmount = view.findViewById(R.id.tvTotalAmount)
        radioStandard = view.findViewById(R.id.radioStandard)
        radioExpress = view.findViewById(R.id.radioExpress)
        val btnPay = view.findViewById<Button>(R.id.btnPay)

        // Populate item details — price shown is the DISCOUNTED price
        tvItemName.text = carName
        tvItemPrice.text = formatCurrency(discountedPrice)
        ivCarImage.setImageResource(carImage)

        // Standard shipping is selected by default, so initial shipping cost is $0
        shippingCost = 0.0
        calculateTotal()

        // Manual radio toggle since RadioButtons are nested inside LinearLayouts
        radioStandard.setOnClickListener {
            radioStandard.isChecked = true
            radioExpress.isChecked = false
            shippingCost = 0.0
            calculateTotal()
        }

        radioExpress.setOnClickListener {
            radioExpress.isChecked = true
            radioStandard.isChecked = false
            shippingCost = EXPRESS_FEE
            calculateTotal()
        }

        // Navigate to CompletionFragment on Pay click
        btnPay.setOnClickListener {
            navigateToCompletion()
        }
    }

    /**
     * Calculates total price = discountedPrice + shippingCost and updates the UI.
     */
    private fun calculateTotal() {
        totalPrice = discountedPrice + shippingCost
        tvTotalAmount.text = formatCurrency(totalPrice)
    }

    /**
     * Formats a Double as US currency with no decimal places (e.g., "$38,000").
     */
    private fun formatCurrency(amount: Double): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale.US)
        formatter.maximumFractionDigits = 0
        return formatter.format(amount)
    }

    /**
     * Navigates to CompletionFragment (success screen).
     */
    private fun navigateToCompletion() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CompletionFragment())
            .addToBackStack(null)
            .commit()
    }
}
