package com.ferbo.tools.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IdentifiableTest {

    static class TestEntity implements Identifiable<Long>{
        private final Long id;

        TestEntity(Long id) {
            this.id = id;
        }

        @Override
        public Long getId() {
            return id;
        }
        
    }

    @Test
    public void shouldReturnId() {
        TestEntity entity = new TestEntity(1L);
        assertEquals(Long.valueOf(1L), entity.getId());
    }

}
