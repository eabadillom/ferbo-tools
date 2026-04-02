package com.ferbo.tools.value.money;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.ferbo.tools.exception.ValidationException;

/**
 * Implementación por defecto de TaxProvider.
 *
 * Características:
 * - Basada en reglas estáticas internas
 * - Fáciles de extender
 * - Seguro y determinista
 */
public final class DefaultTaxProvider implements TaxProvider {

    private final Map<String, Map<String, Tax>> rules;

    public DefaultTaxProvider() {
        rules = new HashMap<>();

        // Reglas para México (ejemplo)
        Map<String, Tax> mxRules = new HashMap<>();
        mxRules.put("IVA", Tax.ofPercentage(BigDecimal.valueOf(16)));
        mxRules.put("ISR", Tax.ofPercentage(BigDecimal.valueOf(10)));
        rules.put("MX", mxRules);

        // Puedes agregar más países aquí
    }

    @Override
    public Tax getTax(String countryCode, String taxType) {
        if (countryCode == null || countryCode.isEmpty()) {
            throw new ValidationException("El código de país no puede ser nulo o vacío");
        }
        if (taxType == null || taxType.isEmpty()) {
            throw new ValidationException("El tipo de impuesto no puede ser nulo o vacío");
        }

        Map<String, Tax> countryRules = rules.get(countryCode.toUpperCase());
        if (countryRules == null || !countryRules.containsKey(taxType.toUpperCase())) {
            throw new ValidationException("No existe la regla de impuesto: " + taxType + " en " + countryCode);
        }

        return countryRules.get(taxType.toUpperCase());
    }

    /**
     * Permite agregar o actualizar reglas dinámicamente.
     */
    public void addOrUpdateRule(String countryCode, String taxType, Tax tax) {
        if (countryCode == null || taxType == null || tax == null) {
            throw new ValidationException("Parámetros no pueden ser nulos");
        }
        rules.computeIfAbsent(countryCode.toUpperCase(), k -> new HashMap<>())
                .put(taxType.toUpperCase(), tax);
    }
}