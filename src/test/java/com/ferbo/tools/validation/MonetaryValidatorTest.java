package com.ferbo.tools.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Test;

import com.ferbo.tools.result.OperationResult;
import com.ferbo.tools.value.money.Money;

public class MonetaryValidatorTest {

    private final Currency eur = Currency.getInstance("EUR");
    private final Currency usd = Currency.getInstance("USD");

    @Test
    public void shouldReturnSuccessForValidMoney() {
        Money money = new Money(new BigDecimal("100.00"), usd);
        MonetaryValidator validator = new MonetaryValidator(true, "USD");

        OperationResult<Money> result = validator.validate(money);

        assertTrue(result.isSuccess());
        assertEquals(money, result.getData());
        assertFalse(result.hasErrors());
    }

    @Test
    public void shouldReturnFailureForNullMoney() {
        MonetaryValidator validator = new MonetaryValidator(true, "USD");

        OperationResult<Money> result = validator.validate(null);

        assertFalse(result.isSuccess());
        assertTrue(result.hasErrors());
        assertEquals("El objeto Money no puede ser nulo", result.getMessages().get(0).getBody());
    }

    @Test
    public void shouldReturnFailureForNegativeMoney() {
        Money money = new Money(new BigDecimal("-50.00"), usd);
        MonetaryValidator validator = new MonetaryValidator(true, "USD");

        OperationResult<Money> result = validator.validate(money);

        assertFalse(result.isSuccess());
        assertTrue(result.hasErrors());
        assertEquals("El valor debe ser positivo", result.getMessages().get(0).getBody());
    }

    @Test
    public void shouldReturnFailureForWrongCurrency() {
        Money money = new Money(new BigDecimal("100.00"), eur);
        MonetaryValidator validator = new MonetaryValidator(true, "USD");

        OperationResult<Money> result = validator.validate(money);

        assertFalse(result.isSuccess());
        assertTrue(result.hasErrors());
        assertEquals("La moneda debe ser USD", result.getMessages().get(0).getBody());
    }

    @Test
    public void shouldReturnSuccessWithoutCurrencyCheck() {
        Money money = new Money(new BigDecimal("50.00"), eur);
        MonetaryValidator validator = new MonetaryValidator(true, null);

        OperationResult<Money> result = validator.validate(money);

        assertTrue(result.isSuccess());
        assertEquals(money, result.getData());
        assertFalse(result.hasErrors());
    }

    @Test
    public void shouldReturnSuccessForZeroOrNegativeWhenNotPositiveOnly() {
        Money money = new Money(new BigDecimal("-10.00"), usd);
        MonetaryValidator validator = new MonetaryValidator(false, "USD");

        OperationResult<Money> result = validator.validate(money);

        assertTrue(result.isSuccess());
        assertEquals(money, result.getData());
        assertFalse(result.hasErrors());
    }
}