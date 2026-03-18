package com.ferbo.tools.result;

import java.util.Collections;
import java.util.List;

import com.ferbo.tools.exception.ValidationException;

public final class OperationResult {

    private final boolean success;
    private final List<Message> messages;
    private final int affectedCount;
    private final Object data;

    /**
     * Constructor principal. 
     * 
     * @param success indica si la operación fue exitosa
     * @param messages lista de mensajes osocuiados
     * @param affectedCount cantidad de elementos afectados
     * @param data resultado de la operacion (opcional)
     */
    public OperationResult(boolean succes, List<Message> messages, int affectedCount, Object data) {

        if (messages == null) {
            throw new ValidationException("La lista de mensajes no puede ser nula");
        }

        if (affectedCount < 0) {
            throw new ValidationException("El número de elementos afectados no puede ser negativo");
        }

        this.success = succes;
        this.messages = Collections.unmodifiableList(messages);
        this.affectedCount = affectedCount;
        this.data = data;
    }

    /**
     * Indica si la operación fue exitosa.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Devuelve la lista de mensajes.
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Devuelve la cantidad de elementos afectados.
     */
    public int getAffectedCount() {
        return affectedCount;
    }

    /**
     * Devuelve los datos asociados a la operación.
     */
    public Object getData() {
        return data;
    }

    /**
     * Indica si existen errores en los mensajes.
     */
    public boolean hasErrors() {
        return messages.stream().anyMatch(m -> m.getLevel() == MessageLevel.ERROR);
    }

    /**
     * Indica si existen advertencias.
     */
    public boolean hasWarnings() {
        return messages.stream().anyMatch(m -> m.getLevel() == MessageLevel.WARNING);
    }

    /**
     * Representación en testo del resultado.
     */
    @Override
    public String toString() {
        return "OperationResult{" +
               "success=" + success +
               ", affectedCount=" + affectedCount +
               ", messages=" + messages +
               '}';
    }
}
