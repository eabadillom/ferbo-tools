package com.ferbo.tools.value.money;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Estrategia de redondeo para operaciones monetarias.
 *
 * Responsabilidades:
 * - Definir cómo se redondean montos según la moneda
 * - Controlar operaciones que requieren precisión (ej: división)
 *
 * Permite múltiples implementaciones:
 * - Estándar (HALF_UP)
 * - Bancario (HALF_EVEN)
 * - Cripto (alta precisión)
 */
public interface RoundingStrategy {

    /**
     * Redondea un monto según la moneda.
     */
    BigDecimal round(BigDecimal amount, Currency currency);

    /**
     * Divide un monto aplicando reglas de precisión y redondeo.
     */
    BigDecimal divide(BigDecimal amount, BigDecimal divisor, Currency currency);
}