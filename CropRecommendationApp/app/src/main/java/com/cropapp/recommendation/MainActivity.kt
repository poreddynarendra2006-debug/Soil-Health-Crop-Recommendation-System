package com.cropapp.recommendation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cropapp.recommendation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CropViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "🌾 Crop Recommendation"

        binding.btnPredict.setOnClickListener {
            if (validateInputs()) {
                val n = binding.etNitrogen.text.toString().toInt()
                val p = binding.etPhosphorus.text.toString().toInt()
                val k = binding.etPotassium.text.toString().toInt()
                val temperature = binding.etTemperature.text.toString().toDouble()
                val humidity = binding.etHumidity.text.toString().toDouble()
                val ph = binding.etPh.text.toString().toDouble()
                val rainfall = binding.etRainfall.text.toString().toDouble()

                viewModel.predictCrop(n, p, k, temperature, humidity, ph, rainfall)
            }
        }

        binding.btnReset.setOnClickListener {
            clearAllFields()
            viewModel.resetState()
        }
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(this) { state ->
            when (state) {
                is UiState.Idle -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnPredict.isEnabled = true
                }
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnPredict.isEnabled = false
                }
                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnPredict.isEnabled = true
                    navigateToResult(state.crop)
                }
                is UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnPredict.isEnabled = true
                    Toast.makeText(this, "Error: ${state.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        val fields = listOf(
            binding.etNitrogen to binding.tilNitrogen,
            binding.etPhosphorus to binding.tilPhosphorus,
            binding.etPotassium to binding.tilPotassium,
            binding.etTemperature to binding.tilTemperature,
            binding.etHumidity to binding.tilHumidity,
            binding.etPh to binding.tilPh,
            binding.etRainfall to binding.tilRainfall
        )

        fields.forEach { (editText, inputLayout) ->
            val text = editText.text.toString().trim()
            if (text.isEmpty()) {
                inputLayout.error = "This field is required"
                isValid = false
            } else {
                inputLayout.error = null
                try {
                    text.toDouble()
                } catch (e: NumberFormatException) {
                    inputLayout.error = "Enter a valid number"
                    isValid = false
                }
            }
        }

        // Specific range validations
        if (isValid) {
            val ph = binding.etPh.text.toString().toDouble()
            if (ph < 0 || ph > 14) {
                binding.tilPh.error = "pH must be between 0 and 14"
                isValid = false
            }

            val humidity = binding.etHumidity.text.toString().toDouble()
            if (humidity < 0 || humidity > 100) {
                binding.tilHumidity.error = "Humidity must be between 0 and 100"
                isValid = false
            }
        }

        return isValid
    }

    private fun clearAllFields() {
        binding.etNitrogen.text?.clear()
        binding.etPhosphorus.text?.clear()
        binding.etPotassium.text?.clear()
        binding.etTemperature.text?.clear()
        binding.etHumidity.text?.clear()
        binding.etPh.text?.clear()
        binding.etRainfall.text?.clear()

        listOf(
            binding.tilNitrogen, binding.tilPhosphorus, binding.tilPotassium,
            binding.tilTemperature, binding.tilHumidity, binding.tilPh, binding.tilRainfall
        ).forEach { it.error = null }
    }

    private fun navigateToResult(crop: String) {
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra(ResultActivity.EXTRA_CROP_NAME, crop)
            putExtra(ResultActivity.EXTRA_N, binding.etNitrogen.text.toString())
            putExtra(ResultActivity.EXTRA_P, binding.etPhosphorus.text.toString())
            putExtra(ResultActivity.EXTRA_K, binding.etPotassium.text.toString())
            putExtra(ResultActivity.EXTRA_TEMP, binding.etTemperature.text.toString())
            putExtra(ResultActivity.EXTRA_HUMIDITY, binding.etHumidity.text.toString())
            putExtra(ResultActivity.EXTRA_PH, binding.etPh.text.toString())
            putExtra(ResultActivity.EXTRA_RAINFALL, binding.etRainfall.text.toString())
        }
        startActivity(intent)
    }
}
