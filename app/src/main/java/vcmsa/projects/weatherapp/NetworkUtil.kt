package vcmsa.projects.weatherapp

import android.net.Uri
import android.util.Log
import java.net.MalformedURLException
import java.net.URL

private val WEATHERBASEURL = "https://dataservice.accuweather.com/forecasts/v1/daily/5day/305605"
    private val PARAMMETRIC = "metric"
    private val METRICVALUE = "true"
    private val PARAMAPIKEY = "apikey"
    private val lOGGINGTAG = "URLWECREATED"

    fun buildURLForWeather() : URL? {
        val buildUri: Uri = Uri.parse(WEATHERBASEURL).buildUpon()
            .appendQueryParameter(
                PARAMAPIKEY,
                BuildConfig.ACCUWEATHER_API_KEY
            )
            .appendQueryParameter(
                PARAMMETRIC,
                METRICVALUE
            )
            .build()
        var url: URL? = null
        try{
            url = URL(buildUri.toString())
        }catch (e: MalformedURLException){
            e.printStackTrace()
        }
       Log.i(lOGGINGTAG, "buildURlForWeather: $url")
        return url
    }