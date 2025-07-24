package vcmsa.projects.weatherapp



data class WeatherResponse(
    val DailyForecasts: List<DailyForecast>
)

data class DailyForecast(
    val Date: String,
    val Temperature: Temperature,
    val Day: DayNight,
    val Night: DayNight
)
data class Temperature(
    val Minimum: TempValue,
    val Maximum: TempValue
)
data class TempValue(
    val Value: Double,
    val Unit: String

)data class DayNight(
    val IconPhrase: String
)