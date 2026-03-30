package com.ferbo.tools.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;

import com.ferbo.tools.exception.ValidationException;
import com.ferbo.tools.value.money.Money;

/**
 * Pruebas unitarias para ObjectValidatorBuilder usando JUnit 5.
 * 
 * <p>
 * Este archivo cubre los escenarios más importantes:
 * 1. Validación exitosa (todos los campos correctos)
 * 2. Objeto nulo
 * 3. Campos inválidos
 * 4. Lanzamiento de excepción al validar con errores
 * 5. Validación de objetos anidados (nested)
 * </p>
 * 
 * <p>
 * Todas las pruebas utilizan lambdas y method references para aprovechar Java 8
 * al máximo.
 * </p>
 */
public class ObjectValidatorBuilderTest {

    // Moneda de ejemplo para pruebas
    private final Currency currency = Currency.getInstance("MXN");

    // Valor monetario válido
    private final Money moneyValido = new Money(new BigDecimal("6000.00"), currency);

    // --------------------------------------
    // CLASES AUXILIARES PARA LOS TESTS
    // --------------------------------------

    /**
     * Formulario de ejemplo a validar.
     */
    private static class DummyForm {
        private String unTexto;
        private Integer unEntero;
        private Money unDinero;
        private Direccion direccion; // Para nested validation

        public DummyForm(String unTexto, Integer unEntero, Money unDinero) {
            this.unTexto = unTexto;
            this.unEntero = unEntero;
            this.unDinero = unDinero;
        }

        public String getUnTexto() {
            return unTexto;
        }

        public Integer getUnEntero() {
            return unEntero;
        }

        public Money getUnDinero() {
            return unDinero;
        }

        public Direccion getDireccion() {
            return direccion;
        }

        public void setDireccion(Direccion direccion) {
            this.direccion = direccion;
        }
    }

    /**
     * Objeto anidado para pruebas de nested validation.
     */
    private static class Direccion {
        private String calle;

        public Direccion(String calle) {
            this.calle = calle;
        }

        public String getCalle() {
            return calle;
        }
    }

    // --------------------------------------
    // TEST VALIDACIÓN CORRECTA
    // --------------------------------------
    @Test
    public void shouldPassWhenValid() {
        DummyForm dummyForm = new DummyForm("Juan", 30, moneyValido);

        // Validación fluida usando ObjectValidatorBuilder
        // No se espera que lance ninguna excepción
        assertDoesNotThrow(() -> new ObjectValidatorBuilder<>("formulario", dummyForm)
                .validateObject() // Valida que dummyForm no sea nulo
                .texto("nombre", DummyForm::getUnTexto)
                .integer("edad", DummyForm::getUnEntero, 0, 120)
                .monetary("saldo", DummyForm::getUnDinero, true, currency)
                .validateOrThrow() // Lanza excepción solo si hay errores
        );
    }

    // --------------------------------------
    // TEST OBJETO NULO
    // --------------------------------------
    @Test
    public void shouldFailWhenObjectIsNull() {
        DummyForm dummyForm = null;

        ObjectValidatorBuilder<DummyForm> builder = new ObjectValidatorBuilder<>("formulario", dummyForm)
                .validateObject();

        Notification notification = builder.getNotification();

        // Se espera un error indicando que el objeto es nulo
        assertTrue(notification.hasErrors());
        assertEquals(1, notification.getErrors().size());
        assertEquals("formulario no debe ser nulo", notification.getErrors().get(0));
    }

    // --------------------------------------
    // TEST CAMPOS INVÁLIDOS
    // --------------------------------------
    @Test
    public void shouldFailWhenFieldsAreInvalid() {
        DummyForm dummyForm = new DummyForm(
                "", // nombre vacío
                200, // edad fuera de rango
                new Money(new BigDecimal("-1000.00"), currency) // saldo negativo
        );

        ObjectValidatorBuilder<DummyForm> builder = new ObjectValidatorBuilder<>("formulario", dummyForm)
                .validateObject()
                .texto("nombre", DummyForm::getUnTexto)
                .integer("edad", DummyForm::getUnEntero, 0, 120)
                .monetary("saldo", DummyForm::getUnDinero, true, currency);

        Notification notification = builder.getNotification();

        // Se esperan errores para todos los campos inválidos
        assertTrue(notification.hasErrors());

        // Validamos el nombre
        assertTrue(notification.getErrors().stream()
                .anyMatch(msg -> msg.contains("nombre") && msg.contains("vacío")));

        // Validamos la edad fuera de rango (mínimo o máximo)
        assertTrue(notification.getErrors().stream()
                .anyMatch(msg -> msg.contains("edad") && msg.contains("menor") || msg.contains("mayor")));

        // Validamos saldo negativo
        assertTrue(notification.getErrors().stream()
                .anyMatch(msg -> msg.contains("saldo") && msg.contains("positivo")));
    }

    // --------------------------------------
    // TEST validateOrThrow LANZA EXCEPCIÓN
    // --------------------------------------
    @Test
    public void shouldThrowExceptionWhenInvalid() {
        DummyForm dummyForm = new DummyForm("", -1, new Money(new BigDecimal("-1000"), currency));

        ObjectValidatorBuilder<DummyForm> builder = new ObjectValidatorBuilder<>("formulario", dummyForm)
                .validateObject()
                .texto("nombre", DummyForm::getUnTexto)
                .integer("edad", DummyForm::getUnEntero, 0, 120)
                .monetary("saldo", DummyForm::getUnDinero, true, currency);

        // Se espera que la excepción NotificationException se lance debido a múltiples
        // errores
        assertThrows(ValidationException.class, builder::validateOrThrow);
    }

    // --------------------------------------
    // TEST VALIDACIÓN ANIDADA (NESTED)
    // --------------------------------------
    @Test
    public void shouldValidateNestedObject() {
        DummyForm dummyForm = new DummyForm("Juan", 30, moneyValido);
        Direccion direccion = new Direccion(""); // calle vacía
        dummyForm.setDireccion(direccion);

        ObjectValidatorBuilder<DummyForm> builder = new ObjectValidatorBuilder<>("formulario", dummyForm)
                .validateObject()
                .validateNested("direccion", DummyForm::getDireccion, dv -> dv.texto("calle", Direccion::getCalle) // Valida
                                                                                                                   // campo
                                                                                                                   // calle
                                                                                                                   // del
                                                                                                                   // objeto
                                                                                                                   // anidado
                );

        Notification notification = builder.getNotification();

        // Se espera un error indicando la calle vacía en el objeto nested
        assertTrue(notification.hasErrors());
        assertEquals(1, notification.getErrors().size());
        assertTrue(notification.getErrors().contains(
                "formulario.direccion.calle: El texto no puede ser vacío o nulo"));
    }

}