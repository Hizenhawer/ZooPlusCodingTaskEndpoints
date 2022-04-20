package zoopluscodingtask.zoopluscodingtask.nbp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class NBPController(@Autowired val nbpService: NBPService) {

    @GetMapping(path = ["/api/exchange-rates/{currencyCode}"])
    fun exchangeRatesForCurrency(@PathVariable currencyCode: String): List<Rate>{
        return nbpService.exchangeRatesForCurrency(currencyCode).rates.toList()
    }

    @GetMapping(path = ["/api/gold-price/avarage"])
    fun averageGoldPrice():Double{
        return nbpService.averageGoldPrice()
    }
}