package vcmsa.projects.weatherapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vcmsa.projects.weatherapp.databinding.ActivityMainBinding
import kotlin.concurrent.thread
import com.google.gson.Gson
import vcmsa.projects.weatherapp.WeatherResponse
import java.net.URL


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        thread{
            val rawJson = try{
                buildURLForWeather()?.readText()
            } catch(e: Exception){
                e.printStackTrace()
                return@thread
            }

            val gson = Gson()
            val weatherResponse = gson.fromJson(rawJson, WeatherResponse::class.java)

            val forecastString = buildString{
                weatherResponse.DailyForecasts.forEach { forecast ->
                    val date = forecast.Date.substring(0,10)
                    val min = forecast.Temperature.Minimum.Value
                    val max = forecast.Temperature.Maximum.Value
                    val day = forecast.Day.IconPhrase
                    val night = forecast.Night.IconPhrase

                    append("📅 $date\n🌞 Day: $day | 🌙 Night: $night\n🌡️ $min°C - $max°C\n\n")
                }
            }

            runOnUiThread{ binding.tvWeather.text = forecastString}
        }

    }


}