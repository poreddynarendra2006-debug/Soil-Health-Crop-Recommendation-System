package com.cropapp.recommendation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cropapp.recommendation.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CROP_NAME = "extra_crop_name"
        const val EXTRA_N = "extra_n"
        const val EXTRA_P = "extra_p"
        const val EXTRA_K = "extra_k"
        const val EXTRA_TEMP = "extra_temp"
        const val EXTRA_HUMIDITY = "extra_humidity"
        const val EXTRA_PH = "extra_ph"
        const val EXTRA_RAINFALL = "extra_rainfall"
    }

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Recommendation Result"

        val cropName = intent.getStringExtra(EXTRA_CROP_NAME) ?: "Unknown"
        displayResults(cropName)
        setupButtons(cropName)
    }

    private fun displayResults(cropName: String) {
        val formattedCrop = cropName.replaceFirstChar { it.uppercase() }
        binding.tvCropName.text = formattedCrop
        binding.tvCropEmoji.text = getCropEmoji(cropName)
        binding.tvCropDescription.text = getCropDescription(cropName)

        // Display input summary
        val n = intent.getStringExtra(EXTRA_N) ?: "–"
        val p = intent.getStringExtra(EXTRA_P) ?: "–"
        val k = intent.getStringExtra(EXTRA_K) ?: "–"
        val temp = intent.getStringExtra(EXTRA_TEMP) ?: "–"
        val humidity = intent.getStringExtra(EXTRA_HUMIDITY) ?: "–"
        val ph = intent.getStringExtra(EXTRA_PH) ?: "–"
        val rainfall = intent.getStringExtra(EXTRA_RAINFALL) ?: "–"

        binding.tvSoilSummary.text = buildString {
            append("🌱 N: $n mg/kg  |  P: $p mg/kg  |  K: $k mg/kg\n")
            append("🌡️ Temperature: $temp °C\n")
            append("💧 Humidity: $humidity %\n")
            append("⚗️ pH: $ph\n")
            append("🌧️ Rainfall: $rainfall mm")
        }

        // Growing tips
        binding.tvGrowingTips.text = getGrowingTips(cropName)
    }

    private fun setupButtons(cropName: String) {
        binding.btnTryAgain.setOnClickListener {
            finish()
        }

        binding.btnShare.setOnClickListener {
            val shareText = "🌾 Crop Recommendation Result\n\n" +
                    "Recommended Crop: ${cropName.replaceFirstChar { it.uppercase() }} ${getCropEmoji(cropName)}\n\n" +
                    "Soil Parameters:\n" +
                    "N: ${intent.getStringExtra(EXTRA_N)} mg/kg\n" +
                    "P: ${intent.getStringExtra(EXTRA_P)} mg/kg\n" +
                    "K: ${intent.getStringExtra(EXTRA_K)} mg/kg\n" +
                    "Temperature: ${intent.getStringExtra(EXTRA_TEMP)} °C\n" +
                    "Humidity: ${intent.getStringExtra(EXTRA_HUMIDITY)} %\n" +
                    "pH: ${intent.getStringExtra(EXTRA_PH)}\n" +
                    "Rainfall: ${intent.getStringExtra(EXTRA_RAINFALL)} mm\n\n" +
                    "Powered by Crop AI 🤖"

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            startActivity(Intent.createChooser(shareIntent, "Share Recommendation"))
        }
    }

    private fun getCropEmoji(crop: String): String {
        return when (crop.lowercase()) {
            "rice" -> "🌾"
            "maize" -> "🌽"
            "chickpea" -> "🫘"
            "kidneybeans" -> "🫘"
            "pigeonpeas" -> "🫘"
            "mothbeans" -> "🫘"
            "mungbean" -> "🫘"
            "blackgram" -> "🫘"
            "lentil" -> "🫘"
            "pomegranate" -> "🍎"
            "banana" -> "🍌"
            "mango" -> "🥭"
            "grapes" -> "🍇"
            "watermelon" -> "🍉"
            "muskmelon" -> "🍈"
            "apple" -> "🍎"
            "orange" -> "🍊"
            "papaya" -> "🍈"
            "coconut" -> "🥥"
            "cotton" -> "🌿"
            "jute" -> "🌿"
            "coffee" -> "☕"
            else -> "🌱"
        }
    }

    private fun getCropDescription(crop: String): String {
        return when (crop.lowercase()) {
            "rice" -> "Rice thrives in warm, humid conditions with abundant water supply. Ideal for your soil composition."
            "maize" -> "Maize (corn) is a versatile crop that grows well in your soil and climate conditions."
            "chickpea" -> "Chickpeas are a nutritious legume that fixes nitrogen in soil, perfect for your conditions."
            "kidneybeans" -> "Kidney beans are protein-rich legumes well-suited for your soil profile."
            "pigeonpeas" -> "Pigeon peas are drought-tolerant legumes ideal for your environment."
            "mothbeans" -> "Moth beans are drought-resistant and thrive in your soil conditions."
            "mungbean" -> "Mung beans grow rapidly and are well-suited for your climatic conditions."
            "blackgram" -> "Black gram (urad dal) is a valuable legume that suits your soil composition."
            "lentil" -> "Lentils are cool-season legumes perfect for your temperature and soil."
            "pomegranate" -> "Pomegranate trees thrive in your semi-arid conditions and soil profile."
            "banana" -> "Bananas love the warm, humid conditions matching your soil data."
            "mango" -> "Mango trees are well-suited for your tropical climate and soil conditions."
            "grapes" -> "Grapes grow excellently in your well-drained soil with your climate."
            "watermelon" -> "Watermelons love warm temperatures and your soil moisture levels."
            "muskmelon" -> "Muskmelons thrive in warm climates matching your environmental data."
            "apple" -> "Apple trees perform well in your cool climate and soil conditions."
            "orange" -> "Oranges thrive in your subtropical climate and well-drained soil."
            "papaya" -> "Papayas grow fast in your tropical climate and are well-matched to your soil."
            "coconut" -> "Coconut palms love your coastal/tropical conditions and soil moisture."
            "cotton" -> "Cotton is ideal for your warm, semi-arid conditions and soil type."
            "jute" -> "Jute flourishes in your warm, humid climate and loamy soil."
            "coffee" -> "Coffee plants thrive in your specific altitude, temperature, and soil pH."
            else -> "Based on your soil and climate data, this crop is highly recommended for optimal yield."
        }
    }

    private fun getGrowingTips(crop: String): String {
        return when (crop.lowercase()) {
            "rice" -> "• Maintain flooded conditions for paddy rice\n• Transplant seedlings at 20–25 days\n• Drain field 2 weeks before harvest"
            "maize" -> "• Plant in rows 60–75 cm apart\n• Water consistently during tasseling\n• Harvest when husks turn brown"
            "chickpea" -> "• Sow 2–3 seeds per hole, 10 cm deep\n• Avoid overwatering — drought-tolerant\n• Harvest 90–100 days after sowing"
            "banana" -> "• Plant suckers in well-drained soil\n• Apply potassium-rich fertilizer\n• Harvest 9–12 months after planting"
            "mango" -> "• Prune after harvest for better yield\n• Avoid waterlogging at all costs\n• Full fruit in 3–5 years from seedling"
            "coffee" -> "• Grow in partial shade\n• Maintain soil moisture, avoid waterlogging\n• First harvest 3–4 years after planting"
            "cotton" -> "• Requires full sun and warm temperatures\n• Control bollworms with timely spray\n• Harvest when bolls open fully"
            else -> "• Monitor soil moisture regularly\n• Apply balanced NPK fertilizer\n• Control pests and diseases early\n• Consult local agricultural extension officer"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
