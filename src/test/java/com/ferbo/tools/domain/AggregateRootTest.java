package com.ferbo.tools.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.List;

import org.junit.Test;

public class AggregateRootTest {

    static class TestEvent implements DomainEvent {

        @Override
        public Instant occurredAt() {
            return Instant.now();
        }
    }

    static class TestAggregate extends AggregateRoot<Long> {

        TestAggregate(Long id) {
            super(id);
        }

        public void doSomething() {
            registerEvent(new TestEvent());
        }
    }

    @Test
    public void shouldRegisterDomainEvent() {
        TestAggregate agg = new TestAggregate(1L);

        agg.doSomething();

        List<DomainEvent> events = agg.getDomainEvents();

        assertEquals(1, events.size());
        assertTrue(events.get(0) instanceof TestEvent);
    }

    @Test
    public void shouldClearDomainEvents() {
        TestAggregate agg = new TestAggregate(1L);

        agg.doSomething();
        agg.clearDomainEvents();

        assertTrue(agg.getDomainEvents().isEmpty());
    }
}
