package com.example.binchecker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.binchecker.databinding.FragmentSecondBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.json.JSONTokener

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    // распихать значения внутрь таблицы
    val renderData: (String) -> () -> Unit = { txt: String ->
        {

            val activity: MainActivity? = activity as MainActivity?

            val jsonObject = JSONTokener(txt).nextValue() as JSONObject

            val number = jsonObject.getJSONObject("number")

            val length = number.getString("length")
            println(length)
            activity?.setTextToTextView(length, R.id.length_title)

            val luhn = number.getString("luhn")
            println(luhn)
            activity?.setTextToTextView(luhn, R.id.luhn_title)

            val scheme = jsonObject.getString("scheme")
            println(scheme)
            activity?.setTextToTextView(scheme, R.id.scheme_title)

            val type = jsonObject.getString("type")
            println(type)
            activity?.setTextToTextView(type, R.id.type_title)

            val brand = jsonObject.getString("brand")
            println(brand)
            activity?.setTextToTextView(brand, R.id.brand_title)

            val prepaid = jsonObject.getString("prepaid")
            println(prepaid)
            activity?.setTextToTextView(prepaid, R.id.prepaid_title)

            val country = jsonObject.getJSONObject("country")

            val numeric = country.getString("numeric")
            println(numeric)
            activity?.setTextToTextView(numeric, R.id.numeric_title)

            val alpha2 = country.getString("alpha2")
            println(alpha2)
            activity?.setTextToTextView(alpha2, R.id.alpha2_title)

            val countryName = country.getString("name")
            println(countryName)
            activity?.setTextToTextView(countryName, R.id.country_title)

            val emoji = country.getString("emoji")
            println(emoji)
            activity?.setTextToTextView(emoji, R.id.flag_title)

            val currency = country.getString("currency")
            println(currency)
            activity?.setTextToTextView(currency, R.id.currency_title)

            val latitude = country.getString("latitude")
            println(latitude)
            activity?.setTextToTextView(latitude, R.id.latitude_title)

            val longitude = country.getString("longitude")
            println(longitude)
            activity?.setTextToTextView(longitude, R.id.longitude_title)

            val bank = jsonObject.getJSONObject("bank")

            val bankName = bank.getString("name")
            println(bankName)
            activity?.setTextToTextView(bankName, R.id.bank_title)

            val url = bank.getString("url")
            println(url)
            activity?.setTextToTextView(url, R.id.url_title)

            val phone = bank.getString("phone")
            println(phone)
            activity?.setTextToTextView(phone, R.id.phone_title)

            val city = bank.getString("city")
            println(city)
            activity?.setTextToTextView(city, R.id.city_title)
        }
    }

    fun fetchBinData(arg: String) {
        GlobalScope.launch { ApiHelper.getData(arg, renderData) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val activity: MainActivity? = activity as MainActivity?
        val cardNumber: String? = activity?.getCardNumber()
        if (cardNumber != null) {
            fetchBinData(cardNumber)
        }

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}