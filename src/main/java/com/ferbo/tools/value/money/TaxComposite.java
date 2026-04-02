package com.ferbo.tools.value.money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.ferbo.tools.exception.ValidationException;

/**
 * Permite aplicar varios impuestos sobre un mismo monto.
 *
 * Ejemplo:
 * - IVA + ISR
 *
 * Características:
 * - Inmutable
 * - Seguro
 * - Determinista
 */
public final class TaxComposite {

    private final List<Tax> taxes;

    /**
     * Constructor privado para asegurar inmutabilidad.
     */
    private TaxComposite(List<Tax> taxes) {
        if (taxes == null || taxes.isEmpty()) {
            throw new ValidationException("Debe haber al menos un impuesto");
        }

        // Crea lista inmutable defensiva
        this.taxes = Collections.unmodifiableList(new ArrayList<>(taxes));
    }

    /**
     * Factory principal.
     */
    public static TaxComposite of(Tax... taxes) {
        if (taxes == null || taxes.length == 0) {
            throw new ValidationException("Debe proveer al menos un impuesto");
        }
        return new TaxComposite(Arrays.asList(taxes));
    }

    /**
     * Calcula la suma de todos los impuestos sobre la base.
     */
    public Money calculate(Money base) {
        if (base == null) {
            throw new ValidationException("El monto base no puede ser nulo");
        }

        Money totalTax = Money.of(BigDecimal.ZERO, base.getCurrency());
        for (Tax tax : taxes) {
            totalTax = totalTax.add(tax.calculate(base));
        }
        return totalTax;
    }

    /**
     * Aplica todos los impuestos a la base.
     */
    public Money apply(Money base) {
        if (base == null) {
            throw new ValidationException("El monto base no puede ser nulo");
        }
        return base.add(calculate(base));
    }

    /**
     * Devuelve la lista inmutable de impuestos.
     */
    public List<Tax> getTaxes() {
        return taxes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TaxComposite))
            return false;
        TaxComposite that = (TaxComposite) o;
        return taxes.equals(that.taxes);
    }

    @Override
    public int hashCode() {
        return taxes.hashCode();
    }

    @Override
    public String toString() {
        return "TaxComposite" + taxes.toString();
    }
}
