package com.ferbo.tools.value.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import com.ferbo.tools.exception.ToolException;

/**
 * Clase de utilidades para manejo seguro de monedas.
 * Provee redondeo consistente, formateo legible y helpers para Money Y Tax.
 * Todos los métodos son estáticos y la clase no se instancia.
 */
public final class CurrencyUtils {

    // Constructor privado para evitar instanciación
    private CurrencyUtils() {
        throw new UnsupportedOperationException("CurrencyUtils no se instancia");
    }

    /**
     * Devuelve la cantidad de decimales (escala) que utiliza la moneda. 
     * Ejemplo: USD -> 2, JPY -> 0
     * 
     * @param currency la moneda a consultar
     * @return la escala de la moneda
     */
    public static int getScale(Currency currency) {
        if (currency == null) {
            throw new ToolException("La moneda no puede ser nula");
        }
        return Math.max(currency.getDefaultFractionDigits(), 0);
    }

    /**
     * Redondea un BigDecimal a la escala correcta de la moneda usando HALF_UP. 
     * 
     * @param amount monto a redondear
     * @param currency moneda que determina la escala
     * @return monto redondeado
     */
    public static BigDecimal round(BigDecimal amount, Currency currency) {
        if (amount == null) {
            throw new ToolException("El monto no puede ser nulo.");
        }
        int scale = getScale(currency);
        return amount.setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * Devuelve un string legible para el Money. 
     * Ejemplo: "MXN 1,000.50"
     * 
     * @param money objeto Money a formatear
     * @return string legible
     */
    public static String format(Money money) {
        if (money == null) {
            throw new ToolException("El objeto Money no puede ser nulo.");
        }

        Currency currency = money.getCurrency();
        BigDecimal amount = money.getAmount();

        NumberFormat formatter =  NumberFormat.getCurrencyInstance(Locale.getDefault());
        formatter.setCurrency(currency);
        formatter.setMinimumFractionDigits(currency.getDefaultFractionDigits());
        formatter.setMaximumFractionDigits(currency.getDefaultFractionDigits());

        return currency.getCurrencyCode() + " " + formatter.format(amount);
    }

    /**
     * Crea un objeto Money seguro: valida, redondea y devuelve.
     * 
     * @param amount monto a usar
     * @param currency moneda del Money
     * @return Money seguro
     */
    public static Money safeMoney(BigDecimal amount, Currency currency) {
        // Solo delega al constructor, que ya valida y redondea
        return new Money(amount, currency);
    }
}
