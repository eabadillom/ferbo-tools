package com.ferbo.tools.value.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;

import com.ferbo.tools.exception.BussinesException;
import com.ferbo.tools.exception.ValidationException;

/**
 * Pruebas unitarias para la clase Money.
 */
public class MoneyTest {

    private Currency mxn;
    private Currency usd;

    @Before
    public void setUp() {
        mxn = Currency.getInstance("MXN");
        usd = Currency.getInstance("USD");
    }

    /**
     * Bebe crear correctamente un objeto Money válido.
     */
    @Test
    public void shouldCreateValidMoney() {
        Money money = new Money(new BigDecimal("100.50"), mxn);

        assertNotNull(money);
        assertEquals(new BigDecimal(("100.50")), money.getAmount());
        assertEquals(mxn, money.getCurrency());
    }

    /**
     * Debe fallar si el monto es nulo.
     */
    @Test(expected = ValidationException.class)
    public void shouldFailWhenAmountIsNull() {
        new Money(null, mxn);
    }

    /**
     * Debe fallar si la moneda es nula.
     */
    @Test(expected = ValidationException.class)
    public void shouldFailWhenCurrencyIsNull() {
        new Money(new BigDecimal("100"), null);
    }

    /**
     * Debe aplicar redondeo correcto segun la momenda
     */
    @Test
    public void shouldApplyRoundingCorrectly() {
        Money money = new Money(new BigDecimal("100.123"), mxn);

        // MXN normalmente usa 2 decimales
        assertEquals(new BigDecimal("100.12"), money.getAmount());
    }

    /**
     * Debe sumar correctamente dos valores con la misma moneda.
     */
    @Test
    public void shouldAddMoneyCurrectly() {
        Money m1 = new Money(new BigDecimal("100.00"), mxn);
        Money m2 = new Money(new BigDecimal("50.00"), mxn);

        Money result = m1.add(m2);

        assertEquals(new BigDecimal("150.00"), result.getAmount());
        assertEquals(mxn, result.getCurrency());
    }

    /**
     * Debe fallar al sumar monedas diferentes.
     */
    @Test (expected = BussinesException.class)
    public void shouldFailWhenAddDifferentCurrencies() {
        Money m1 = new Money(new BigDecimal("100.00"), mxn);
        Money m2 = new Money(new BigDecimal("50.00"), usd);

        m1.add(m2);
    }

    /**
     * Debe restar correctamente.
     */
    @Test
    public void shoulSubtractMoneyCorrectly() {
        Money m1 = new Money(new BigDecimal("100.00"), mxn);
        Money m2 = new Money(new BigDecimal("30.00"), mxn);

        Money result = m1.substract(m2);

        assertEquals(new BigDecimal("70.00"), result.getAmount());
    }

    /**
     * Debe permitir resultados negativos.
     */
    @Test
    public void shouldAllowNegativeResults() {
        Money m1 = new Money(new BigDecimal("50.00"), mxn);
        Money m2 = new Money(new BigDecimal("100.00"), mxn);

        Money result = m1.substract(m2);

        assertTrue(result.isNegative());
        assertEquals(new BigDecimal("-50.00"), result.getAmount());
    }

    /**
     * Debe multiplicar correctamente.
     */
    @Test
    public void shouldMultiplyCorrectly() {
        Money money = new Money(new BigDecimal("100.00"), mxn);

        Money result = money.multiply(new BigDecimal("2"));

        assertEquals(new BigDecimal("200.00"), result.getAmount());
    }

    /**
     * Debe fallar si el factor es nulo.
     */
    @Test(expected = ValidationException.class)
    public void shouldFailWhenFactorIsNull() {
        Money money = new Money(new BigDecimal("100.00"), mxn);

        money.multiply(null);
    }

    /**
     * Debe dividir correctamente.
     */
    @Test
    public void shouldDivividirCorrectly() {
        Money money = new Money(new BigDecimal("100.00"), mxn);

        Money result = money.divide(new BigDecimal("2"));

        assertEquals(new BigDecimal("50.00"), result.getAmount());
    }

    /**
     * Debe fallar al dividir entre cero.
     */
    @Test(expected = BussinesException.class)
    public void shouldFailWhenDivideByZero() {
        Money money = new Money(new BigDecimal("100.00"), mxn);

        money.divide(BigDecimal.ZERO);
    }

    /**
     * Debe identificar correctamente cero.
     */
    @Test
    public void shouldIdentifyZero() {
        Money money = new Money(BigDecimal.ZERO, mxn);

        assertTrue(money.isZero());
    }

    /**
     * Debe identificar correctamente positivo.
     */
    @Test
    public void shouldIdentifyPositive() {
        Money money = new Money(new BigDecimal("10.00"), mxn);

        assertTrue(money.isPositive());
    }

    /**
     * Debe identificar correctamente negativo.
     */
    @Test
    public void shouldIdentifyNegative() {
        Money money = new Money(new BigDecimal("-10.00"), mxn);

        assertTrue(money.isNegative());
    }

    /**
     * Debe comparar correctamente dos montos
     */
    @Test
    public void shouldCompareMoneyCorrectly() {
        Money m1 = new Money(new BigDecimal("100.00"), mxn);
        Money m2 = new Money(new BigDecimal("200.00"), mxn);

        assertTrue(m1.compareTo(m2) < 0);
        assertTrue(m2.compareTo(m1) > 0);
        assertFalse(m1.compareTo(m2) == 0);
    }

    /**
     * equals debe funcionar correctamente.
     */
    @Test
    public void shouldCompareEqualsCorrectly() {
        Money m1 = new Money(new BigDecimal("100.00"), mxn);
        Money m2 = new Money(new BigDecimal("100.00"), mxn);

        assertEquals(m1, m2);
    }

    /**
     * toString debe devolver formato esperado.
     */
    @Test
    public void shouldReturnCorrectToString() {
        Money money = new Money(new BigDecimal("1000.00"), mxn);

        assertEquals("MXN $1,000.00", money.toString());
    }
}
