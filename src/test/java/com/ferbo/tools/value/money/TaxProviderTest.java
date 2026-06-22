package com.ferbo.tools.value.money;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.ferbo.tools.exception.ValidationException;

/**
 * Pruebas unitarias para la interface TaxProvider.
 */
public class TaxProviderTest {

    @Test
    public void testDefaultTaxProvider() {
        DefaultTaxProvider provider = new DefaultTaxProvider();

        Tax iva = provider.getTax("MX", "IVA");
        assertEquals(BigDecimal.valueOf(0.16), iva.getRate());

        Tax isr = provider.getTax("MX", "ISR");
        assertEquals(BigDecimal.valueOf(0.10), isr.getRate());
    }

    @Test
    public void testInvalidTaxThrows() {
        DefaultTaxProvider provider = new DefaultTaxProvider();
        assertThrows(ValidationException.class, () -> provider.getTax("MX", "XYZ"));
        assertThrows(ValidationException.class, () -> provider.getTax("US", "IVA"));
    }

    @Test
    void testAddOrUpdateRule() {
        DefaultTaxProvider provider = new DefaultTaxProvider();
        Tax custom = Tax.ofPercentage(BigDecimal.valueOf(5));
        provider.addOrUpdateRule("US", "VAT", custom);

        Tax fetched = provider.getTax("US", "VAT");
        assertEquals(custom.getRate(), fetched.getRate());
    }
}
