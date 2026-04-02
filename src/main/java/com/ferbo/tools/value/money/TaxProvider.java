package com.ferbo.tools.value.money;

/**
 * Interfaz que provee tasas de impuestos según país y tipo de impuesto.
 *
 * Responsabilidades:
 * - Abstracción de reglas fiscales
 * - Permite múltiples implementaciones
 */
public interface TaxProvider {

    /**
     * Devuelve la tasa de impuesto para un país y tipo específico.
     *
     * @param countryCode código ISO del país (ej: "MX")
     * @param taxType tipo de impuesto (ej: "IVA", "ISR")
     * @return Tax correspondiente
     * @throws ValidationException si no existe la regla
     */
    Tax getTax(String countryCode, String taxType);
}