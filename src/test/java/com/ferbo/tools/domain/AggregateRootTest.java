package com.ferbo.tools.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class AggregateRootTest {

    static class TestAggregate extends AggregateRoot<Long> {

        TestAggregate(Long id) {
            super(id);
        }

        public void doSomething() {
            registerEvent("EVENT");
        }
    }

    @Test
    public void shouldRegisterDomainEvent() {
        TestAggregate agg = new TestAggregate(1L);

        agg.doSomething();

        List<Object> events = agg.getDomainEvents();

        assertEquals(1, events.size());
        assertEquals("EVENT", events.get(0));
    }

    @Test
    public void shouldClearDomainEvents() {
        TestAggregate agg = new TestAggregate(1L);

        agg.doSomething();
        agg.clearDomainEvents();

        assertTrue(agg.getDomainEvents().isEmpty());
    }
}
