package com.ferbo.tools.value.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Test;

public class CurrencyUtilsTest {

    /**
     * Prueba que la escala de USD sea 2 decimales.
     */
    @Test
    public void testGetScaleUSD() {
        Currency usd = Currency.getInstance("USD");

        int scale = CurrencyUtils.getScale(usd);

        assertEquals(2, scale);
    }

    /**
     * Prueba que la escala de JPY sea 0 decimales.
     */
    @Test
    public void testGetScaleJPY() {
        Currency jpy = Currency.getInstance("JPY");

        int scale = CurrencyUtils.getScale(jpy);

        assertEquals(0, scale);
    }

    /**
     * Prueba el redondeo correcto usando HALF_UP.
     */
    @Test
    public void testRound() {
        Currency mxn = Currency.getInstance("MXN");
        BigDecimal amount = new BigDecimal("10.005");

        BigDecimal result = CurrencyUtils.round(amount, mxn);

        assertEquals(new BigDecimal("10.01"), result);
    }

    /**
     * Prueba que el formateo genere un string no vacío y consistente.
     */
    @Test
    public void testFormat() {
        Currency usd = Currency.getInstance("USD");
        Money money = new Money(new BigDecimal("1000.50"), usd);

        String formatted = CurrencyUtils.format(money);

        assertNotNull(formatted);
        assertTrue(formatted.contains("1,000") || formatted.contains("1000"));
    }

    /**
     * Prueba la creación segura de Money (redondeo incluido).
     */
    @Test
    public void testSafeMoney() {
        Currency mxn = Currency.getInstance("MXN");
        BigDecimal amount = new BigDecimal("20.999");

        Money money = CurrencyUtils.safeMoney(amount, mxn);

        assertEquals(new BigDecimal("21.00"), money.getAmount());
        assertEquals(mxn, money.getCurrency());
    }

    /** 
     * Prueba que lance excepción si el monto es nulo.
     */
    @Test(expected = RuntimeException.class) 
    public void testRoundNullAmount() {
        Currency usd = Currency.getInstance("USD");

        CurrencyUtils.round(null, usd);
    }

    /**
     * Prueba que lance excepción si la moneda es nulo.
     */
    @Test(expected = RuntimeException.class)
    public void testGetScaleNullCurrency() {
        CurrencyUtils.getScale(null);
    }
}
