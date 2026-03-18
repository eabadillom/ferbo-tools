package com.ferbo.tools.result;

import java.util.ArrayList;
import java.util.List;

import com.ferbo.tools.exception.ValidationException;

public class ResultBuilder {

    private boolean success;
    private List<Message> messages = new ArrayList<>();
    private int affectedCount;
    private Object data;

    /**
     * Constructor privado para forzar uso de métodos estáticos.
     */
    private ResultBuilder(boolean success) {
        this.success = success; 
    }

    /**
     * Crea un builder para operación exitosa.
     */
    public static ResultBuilder success() {
        return new ResultBuilder(true);
    }

    /**
     * Crea un builder para operación fallida.
     */
    public static ResultBuilder failure() {
        return new ResultBuilder(false);
    }

    /**
     * Agrega un mensaje.
     */
    public ResultBuilder message(MessageLevel level, String title, String body) {
        this.messages.add(new Message(level, title, body));
        return this;
    }

    /**
     * Agrega un mensaje ya construido.
     */
    public ResultBuilder message(Message message) {
        if (message == null) {
            throw new ValidationException("El mensaje no puede ser nulo");
        }
        this.messages.add(message);
        return this;
    }

    /**
     * Define la cantidad de elementos afectados.
     */
    public ResultBuilder affectedCount(int count) {
        if (count < 0) {
            throw new ValidationException("Los elementos afectados no puede ser negativo");
        }
        this.affectedCount = count;
        return this;
    }

    /**
     * Define los datos de la operación.
     */
    public ResultBuilder data(Object data) {
        this.data = data;
        return this;
    }

    /**
     * Construye el OperationResult final.
     */
    public OperationResult build() {
        return new OperationResult(success, messages, affectedCount, data);
    }
}
