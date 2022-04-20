package zoopluscodingtask.zoopluscodingtask.nbp

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class NBPService {
    private val NBP_API_EXCHANGE_RATE_IN_TIME_INTERVAL =
        "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/"
    private val NBP_API_GOLD_PRICE = "http://api.nbp.pl/api/cenyzlota/last/14"

    fun exchangeRatesForCurrency(currencyCode: String): Rates {
        val currentDate = LocalDateTime.now()
        val dateFormat = DateTimeFormatter.ofPattern("YYYY-MM-dd")
        val url = NBP_API_EXCHANGE_RATE_IN_TIME_INTERVAL.format(
            currencyCode,
            currentDate.minusDays(5).format(dateFormat),
            currentDate.format(dateFormat)
        )
        val restTemplate = RestTemplate()
        return restTemplate.getForObject(url, Rates::class)
    }

    fun averageGoldPrice(): Double {
        val url = NBP_API_GOLD_PRICE
        val restTemplate = RestTemplate()
        val goldPrices: Array<GoldPrice> = restTemplate.getForObject(url, Array<GoldPrice>::class)
        return goldPrices.map { goldPrice -> goldPrice.cena }.average()
    }
}

