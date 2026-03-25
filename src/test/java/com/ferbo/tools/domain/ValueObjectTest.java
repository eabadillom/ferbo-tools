package com.ferbo.tools.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class ValueObjectTest {

    static class TestValueObject extends ValueObject {

        private final String value;

        public TestValueObject (String value) {
            this.value = value;
        }

        @Override
        protected Object[] getAtomicValues() {
            return new Object[]{value};
        }
        
    }

    @Test
    public void shouldBeEqualWhenValuesAreSame() {
        TestValueObject v1 = new TestValueObject("test");
        TestValueObject v2 = new TestValueObject("test");

        assertEquals(v1, v2);
    }

    @Test
    public void shouldNotBeEqualWhenValuesAreDifferent() {
        TestValueObject v1 = new TestValueObject("test1");
        TestValueObject v2 = new TestValueObject("test2");

        assertNotEquals(v1, v2);
    }

    @Test
    public void shouldHaveSameHashCodeWhenValuesAreSame() {
        TestValueObject v1 = new TestValueObject("test");
        TestValueObject v2 = new TestValueObject("test");

        assertEquals(v1.hashCode(), v2.hashCode());
    }

}
