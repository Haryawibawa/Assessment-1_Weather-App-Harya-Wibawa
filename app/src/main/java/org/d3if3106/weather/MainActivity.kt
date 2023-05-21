package org.d3if3106.weather

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.d3if3106.weather.databinding.ActivityMainBinding
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WeatherTask().execute()
    }

    inner class WeatherTask : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            binding.loader.visibility = View.VISIBLE
            binding.mainContainer.visibility = View.GONE
            binding.errorText.visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            val city = "Bandung"
            val api = "f6f37a50028f508d7904b20f69d4ec72"
            var response: String?
            try {
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$api")
                    .readText(Charsets.UTF_8)
            } catch (e: Exception) {
                Log.e("MainActivity", e.toString())
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val updatedAt: Long = jsonObj.getLong("dt")
                val updatedAtText =
                    "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                        Date(updatedAt * 1000)
                    )
                val temp = main.getString("temp") + "°C"
                val weatherDescription = weather.getString("description")

                binding.address.text = "Bandung"
                binding.updatedAt.text = updatedAtText
                binding.status.text = weatherDescription
                binding.temp.text = temp
                binding.tempMin.text = "Min Temp: ${main.getString("temp_min")}"
                binding.tempMax.text = "Max Temp: ${main.getString("temp_max")}"
                binding.sunrise.text = "Sunrise: ${jsonObj.getJSONObject("sys").getString("sunrise")}"
                binding.sunset.text = "Sunset: ${jsonObj.getJSONObject("sys").getString("sunset")}"
                binding.wind.text = "Wind: ${jsonObj.getJSONObject("wind").getString("speed")}"
                binding.pressure.text = "Pressure: ${main.getString("pressure")}"
                binding.humidity.text = "Humidity: ${main.getString("humidity")}"
                binding.about.text = "Created by AndroDocs"

                binding.loader.visibility = View.GONE
                binding.mainContainer.visibility = View.VISIBLE
                binding.errorText.visibility = View.GONE
            } catch (e: Exception) {
                Log.e("MainActivity", e.toString())
                binding.loader.visibility = View.GONE
                binding.mainContainer.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
            }
        }
    }
}
