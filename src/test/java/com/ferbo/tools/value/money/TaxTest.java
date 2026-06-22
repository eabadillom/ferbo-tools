package com.ferbo.tools.value.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;
import org.junit.Test;

import com.ferbo.tools.exception.ValidationException;

/**
 * Pruebas unitarias para la clase Tax.
 */
public class TaxTest {

    @Test
    public void testTaxCreationAndPercentage() {
        Tax t1 = Tax.ofPercentage(BigDecimal.valueOf(16));
        assertEquals(new BigDecimal("0.16"), t1.getRate());
        assertEquals(new BigDecimal("16.00"), t1.asPercentage());
    }

    @Test
    public void testCalculateAndApply() {
        Money base = Money.of("100", "MXN");
        Tax tax = Tax.ofPercentage(BigDecimal.valueOf(16));

        Money calculated = tax.calculate(base);
        assertEquals(Money.of("16", "MXN"), calculated);

        Money applied = tax.apply(base);
        assertEquals(Money.of("116", "MXN"), applied);
    }

    @Test
    public void testInvalidRatesThrow() {
        assertThrows(ValidationException.class, () -> Tax.ofPercentage(BigDecimal.valueOf(-1)));
        assertThrows(ValidationException.class, () -> Tax.of(new BigDecimal("1.1")));
        assertThrows(ValidationException.class, () -> Tax.of(null));
    }
}
