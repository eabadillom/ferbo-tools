package com.ferbo.tools.value.money;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.ferbo.tools.exception.ValidationException;

/**
 * Pruebas unitarias para la clase TaxComposite.
 */
public class TaxCompositeTest {

    @Test
    public void testCalculateAndApplyMultipleTaxes() {
        Tax iva = Tax.ofPercentage(BigDecimal.valueOf(16));
        Tax isr = Tax.ofPercentage(BigDecimal.valueOf(10));
        TaxComposite composite = TaxComposite.of(iva, isr);

        Money base = Money.of("100", "MXN");
        Money totalTax = composite.calculate(base);
        assertEquals(Money.of("26", "MXN"), totalTax);

        Money applied = composite.apply(base);
        assertEquals(Money.of("126", "MXN"), applied);
    }

    @Test
    public void testEmptyOrNullThrows() {
        assertThrows(ValidationException.class, () -> TaxComposite.of());
        assertThrows(ValidationException.class, () -> TaxComposite.of(null));
    }
}
