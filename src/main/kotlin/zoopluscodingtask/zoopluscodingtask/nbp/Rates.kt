package zoopluscodingtask.zoopluscodingtask.nbp

data class Rates(
    val table: String,
    val currency: String,
    val code: String,
    val rates: Array<Rate>
)

data class Rate(
    val no: String,
    val effectiveDate: String,
    val mid: Double
)