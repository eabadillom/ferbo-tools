package com.ferbo.tools.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class EntityTest {

    static class TestEntity extends Entity<Long> {

        protected TestEntity(Long id) {
            super(id);
        }
        
    }

    @Test
    public void shouldBeEqualWhenIdsAreSame() {
        TestEntity e1 = new TestEntity(1L);
        TestEntity e2 = new TestEntity(1L);

        assertEquals(e1, e2);
    }

    @Test
    public void shouldNotBeEqualsWhenIdsAreDifferent() {
        TestEntity e1 = new TestEntity(1L);
        TestEntity e2 = new TestEntity(2L);

        assertNotEquals(e1, e2);
    }

    @Test
    public void shouldHaveSameHashCodeWhenIdsSame() {
        TestEntity e1 = new TestEntity(1L);
        TestEntity e2 = new TestEntity(1L);

        assertEquals(e1.hashCode(), e2.hashCode());
    }
}
