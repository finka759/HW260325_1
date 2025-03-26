import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun getTax_MasercardInExemption() {
        val curentAmount: Int = 73000
        val prevAmountsInMounth: Int = 1_000
        val cardsType: String = "Mastercard"

        val result = getTax(cardsType, prevAmountsInMounth, curentAmount)

        assertEquals(0, result)
    }

    @Test
    fun getTax_MasercrdMasercardNotInExemption() {
        val curentAmount: Int = 76_000
        val prevAmountsInMounth: Int = 60_000
        val cardsType: String = "Mastercard"

        val result = getTax(cardsType, prevAmountsInMounth, curentAmount)

        assertEquals(386, result)
    }

    @Test
    fun getTax_Mir() {
        val curentAmount: Int = 76_000
        val prevAmountsInMounth: Int = 60_000
        val cardsType: String = "Мир" //"Visa" "Мир"

        val result = getTax(cardsType, prevAmountsInMounth, curentAmount)

        assertEquals(0, result)
    }

    @Test
    fun getTax_VisaMinTax() {
        val curentAmount: Int = 4_666
        val prevAmountsInMounth: Int = 60_000
        val cardsType: String = "Visa"

        val result = getTax(cardsType, prevAmountsInMounth, curentAmount)

        assertEquals(35, result)
    }

    @Test
    fun getTax_VisaFullTax() {
        val curentAmount: Int = 4_801
        val prevAmountsInMounth: Int = 60_000
        val cardsType: String = "Visa"

        val result = getTax(cardsType, prevAmountsInMounth, curentAmount)

        assertEquals(36, result)
    }

    @Test
    fun getTax_DataError() {
        val curentAmount: Int = 4_801
        val prevAmountsInMounth: Int = 60_000
        val cardsType: String = "Мирcard"

        val result = getTax(cardsType, prevAmountsInMounth, curentAmount)

        assertEquals(-1, result)
    }

    @Test
    fun getTax_Default() {
        val curentAmount: Int = 4_801
        val result = getTax(curentAmount = curentAmount)
        assertEquals(0, result)
    }

    @Test
    fun getTax_MastercardDefault() {
        val curentAmount: Int = 4_801
        val cardsType: String = "Mastercard"
        val result = getTax(cardsType, curentAmount = curentAmount)
        assertEquals(0, result)
    }

    @Test
    fun getTaxMastercard_Default() {
        val curentAmount: Int = 4_801
        val cardsType: String = "Mastercard"
        val result = getTaxMastercard(curentAmount = curentAmount)
        assertEquals(0, result)
    }

    @Test
    fun makeTransfer_Normal() {
        val limitDay: Int = 150_000
        val limitMonth: Int = 600_000
        val curentAmount: Int = 4801
        val prevAmountsInDay: Int = 15_000
        val prevAmountsInMounth: Int = 60_000
        val cardsType: String = "Visa" //"Visa" "Мир"

        val result = makeTransfer(
            limitDay,
            limitMonth,
            curentAmount,
            prevAmountsInDay,
            prevAmountsInMounth,
            cardsType
        )
        assertEquals("Комиссия составит: 36", result)
    }

    @Test
    fun makeTransfer_OverLimitsDay() {
        val limitDay: Int = 150_000
        val limitMonth: Int = 600_000
        val curentAmount: Int = 1000
        val prevAmountsInDay: Int = 150_000
        val prevAmountsInMounth: Int = 60_000
        val cardsType: String = "Visa" //"Visa" "Мир"

        val result = makeTransfer(
            limitDay,
            limitMonth,
            curentAmount,
            prevAmountsInDay,
            prevAmountsInMounth,
            cardsType
        )
        assertEquals("Превышены лимиты. Операция отклонена!", result)
    }

    @Test
    fun makeTransfer_OverLimitsMonth() {
        val limitDay: Int = 150_000
        val limitMonth: Int = 600_000
        val curentAmount: Int = 1000
        val prevAmountsInDay: Int = 15_000
        val prevAmountsInMounth: Int = 600_000
        val cardsType: String = "Visa" //"Visa" "Мир"

        val result = makeTransfer(
            limitDay,
            limitMonth,
            curentAmount,
            prevAmountsInDay,
            prevAmountsInMounth,
            cardsType
        )
        assertEquals("Превышены лимиты. Операция отклонена!", result)
    }

    @Test
    fun makeTransfer_DataMistake() {
        val limitDay: Int = 150_000
        val limitMonth: Int = 600_000
        val curentAmount: Int = 1000
        val prevAmountsInDay: Int = 15_000
        val prevAmountsInMounth: Int = 60_000
        val cardsType: String = "Vis" //"Visa" "Мир"

        val result = makeTransfer(
            limitDay,
            limitMonth,
            curentAmount,
            prevAmountsInDay,
            prevAmountsInMounth,
            cardsType
        )
        assertEquals("Ошибка данных. Операция отклонена!", result)
    }


}