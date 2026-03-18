package com.ferbo.tools.result;

import com.ferbo.tools.exception.ValidationException;

/**
 * Value Object que representa un mensaje dentro de un resultado de operación.
 * Cada mensaje tiene un nivel (SUCCESS, INFO, WARNING, ERROR), titulo y cuerpo (opcional).
 * Es inmutable y seguro.
 */
public final class Message {

    private final MessageLevel level;
    private final String title;
    private final String body;          //opcional

    /**
     * Constructor principal.
     * 
     * @param level Nivel del mensaje (SUCCESS, INFO, WARNING, ERROR)
     * @param title Titulo breve del mensaje
     * @param body Cuerpo o detalle del mensaje (opcional, pueder ser null)
     */
    public Message(MessageLevel level, String title, String body) {
        if (level == null) {
            throw new ValidationException("El nivel del mensaje no puede ser nulo");
        }

        if (title == null || title.trim().isEmpty()) {
            throw new ValidationException("El titulo del mensaje no puede ser nulo");
        }

        this.level = level;
        this.title = title.trim();
        this.body = (body == null || body.trim().isEmpty()) ? null : body.trim();
    }

    /**
     * Devuelve el nivel del mensaje.
     */
    public MessageLevel getLevel() {
        return level;
    }

    /**
     * Devuelve título del mensaje.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Devuelve el cuerpo del mensaje, puede ser null.
     */
    public String getBody() {
        return body;
    }

    /**
     * equals basado en nivel, título y cuerpo.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message m = (Message) o;
        return level == m.level &&
               title.equals(m.title) &&
               ((body == null && m.body == null) || (body != null && body.equals(m.body)));
    }

    /**
     * hashCode consistente con equals.
     */
    @Override
    public int hashCode() {
        int result = level.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    /**
     * Respresentación en texto del mensaje.
     */
    @Override
    public String toString() {
        return "[" + level + "] " + title + (body != null ? ": " + body : "");
    }
}
