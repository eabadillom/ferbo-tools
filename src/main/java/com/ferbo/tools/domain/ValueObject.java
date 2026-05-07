package com.ferbo.tools.domain;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Clase base abstracta para objetos de valor (Value Objects).
 */
public abstract class ValueObject implements Serializable {

    /**
     * Devuelve los atributos relevantes para la comparación de igualdad.
     */
    protected abstract Object[] getAtomicValues();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValueObject that = (ValueObject) o;

        return Arrays.equals(this.getAtomicValues(), that.getAtomicValues());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getAtomicValues());
    }
}
