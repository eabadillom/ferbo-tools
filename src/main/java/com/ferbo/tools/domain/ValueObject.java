package com.ferbo.tools.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase base abstracta para objetos de valor (Value Objects).
 * 
 * <p>
 * Un value Object no tiene identidad propia y se define únicamente por el valor
 * de sus atributos. Debe ser inmutable.
 * </p>
 * 
 * <p>
 * Dos Value Objects son iguales si todos sus atributos son iguales.
 * </p>
 */
public abstract class ValueObject implements Serializable {

    /**
     * Devuelve los atributos relevantes para la comparación de igualdad.
     * 
     * <p>
     * Las clases hijas deben implementar este método retornando un arreglo
     * con los valores que definen el estado del objeto.
     * </p>
     * 
     * @return atributos del objeto 
     */
    protected abstract Object[] getAtomicValues();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValueObject that = (ValueObject) o;

        return java.util.Arrays.equals(this.getAtomicValues(), that.getAtomicValues());
    }

    @Override
    public int hashCode() {
        return Objects.hash((Object[]) getAtomicValues());
    }
}
