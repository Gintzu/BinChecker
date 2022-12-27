package com.example.binchecker

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.binchecker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var jsonData = ""
    private var cardNumber = ""
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val btnSubmit = findViewById(R.id.submitButton) as Button

        btnSubmit.setOnClickListener {
            val editText = findViewById(R.id.editText) as TextView
            setCardNumber(editText.text.toString())
            Toast.makeText(this@MainActivity, "Search started", Toast.LENGTH_SHORT).show()
            navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        val btn_clear = findViewById(R.id.clearButton) as Button

        btn_clear.setOnClickListener {
            val editText = findViewById(R.id.editText) as TextView
            editText.setText("")
            val txt = findViewById(R.id.result) as TextView
            txt.setText("")
            Toast.makeText(this@MainActivity, "Cleared", Toast.LENGTH_SHORT).show()
        }

    }

    fun getJsonData(): String? {
        return jsonData
    }

    fun setJsonData(data: String) {
        jsonData = data
    }

    fun getCardNumber(): String? {
        return cardNumber
    }

    fun setCardNumber(data: String) {
        cardNumber = data
    }

    fun setTextToTextView(text: String, id: Int) {
        // принимаем текст и айди
        // ищем View по айди, пихаем текст из аргумента
        val foundView = findViewById(id) as TextView
        foundView.text=text
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}