package com.ferbo.tools.value.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;

import com.ferbo.tools.exception.ValidationException;

/**
 * Pruebas unitarias para la clase Tax.
 */
public class TaxTest {

    private Currency mxn;

    @Before
    public void setUp() {
        mxn = Currency.getInstance("MXN");
    }

    /**
     * Debe crear correctamente un impuesto válido.
     */
    @Test
    public void shouldCreateValidTax() {
        Tax tax = new Tax(new BigDecimal("0.16"));
        
        assertNotNull(tax);
        assertEquals(new BigDecimal("0.16"), tax.getRate());
    }

    /**
     * Debe fallar si la tasa es nula.
     */
    @Test(expected = ValidationException.class)
    public void shouldFailWhenRateIsNull() {
        new Tax(null);
    }

    /**
     * Debe fallar si la tasa es negativa.
     */
    @Test(expected = ValidationException.class)
    public void shouldFailWhenRateIsNegative() {
        new Tax(new BigDecimal("-0.10"));
    }

    /**
     * Debe fallar si la tasa es mayo a 1.
     */
    @Test(expected = ValidationException.class) 
    public void shouldFailWhenRateIsGreaterThanOne() {
        new Tax(new BigDecimal("1.10"));
    }

    /**
     * Debe calcular correctamente el impuesto.
     */
    @Test
    public void shouldCalculateTaxCorrectly() {
        Money base = new Money(new BigDecimal("100.00"), mxn);
        Tax tax = new Tax(new BigDecimal("0.16"));

        Money result = tax.calculate(base);

        assertEquals(new BigDecimal("16.00"), result.getAmount());
        assertEquals(mxn, result.getCurrency());
    }

    /**
     * Debe aplicar correctamente el impuesto (base + impuesto).
     */
    @Test
    public void shouldApplyTaxCorrectly() {
        Money base = new Money(new BigDecimal("100.00"), mxn);
        Tax tax = new Tax(new BigDecimal("0.16"));

        Money result = tax.apply(base);

        assertEquals(new BigDecimal("116.00"), result.getAmount());
    }

    /**
     * Debe fallar si el monto base es nulo al calcular.
     */
    @Test(expected = ValidationException.class)
    public void shouldFailWhenBaseIsNullInCalculate() {
        Tax tax = new Tax(new BigDecimal("0.16"));

        tax.calculate(null);
    }

    /**
     * Debe fallar si el monto base es nulo al aplicar.
     */
    @Test(expected = ValidationException.class)
    public void shouldFailWhenBaseIsNullInApply() {
        Tax tax = new Tax(new BigDecimal("0.16"));

        tax.apply(null);
    }

    /**
     * equals debe funcionar correctamente.
     */
    @Test
    public void shouldCompareEqualsCorrectly() {
        Tax t1 = new Tax(new BigDecimal("0.16"));
        Tax t2 = new Tax(new BigDecimal("0.16"));

        assertEquals(t1, t2);
    }

    @Test
    public void shouldConvertRateToPercentage() {
        Tax tax = new Tax(new BigDecimal("0.25"));
        BigDecimal result = tax.asPercentage();
        assertEquals(new BigDecimal("25.00"), result);
    }

    /**
     * toString debe devolver información útil.
     */
    @Test
    public void shouldReturnCorrectToString() {
        Tax tax = new Tax(new BigDecimal("0.16"));

        assertTrue(tax.toString().contains("0.16"));
    }
}
