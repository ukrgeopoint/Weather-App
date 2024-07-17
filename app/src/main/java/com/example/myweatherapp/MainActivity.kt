package com.example.myweatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // define view
        val cityEnter: EditText = findViewById(R.id.cityEnter)
        val cityName: TextView = findViewById(R.id.cityName)
        val temperatureInfo: TextView = findViewById(R.id.temperatureInfo)
        val windInfo: TextView = findViewById(R.id.windInfo)
        val descriptionInfo: TextView = findViewById(R.id.tvDescription)

        val forecast: Button = findViewById(R.id.btnForecast)

        // request
        forecast.setOnClickListener {
            val city = cityEnter.text.toString()
            cityName.text = city
            ApiWeather.request.create(WeatherInterface::class.java)
                .getWeatherInfo(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    temperatureInfo.text = "${it.temperature}"
                    windInfo.text = "${it.wind}"
                    descriptionInfo.text = "${it.description}"
                }, {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                })
        }
    }
}

// data class
data class WeatherResponse(
    val temperature: String,
    val wind: String,
    val description: String
)