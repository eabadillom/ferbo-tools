package com.ferbo.tools.funtional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ferbo.tools.exception.ToolException;
import com.ferbo.tools.functional.ThrowingConsumer;
import com.ferbo.tools.functional.ThrowingFunction;
import com.ferbo.tools.functional.ThrowingRunnable;
import com.ferbo.tools.functional.ThrowingSupplier;

public class FunctionalTest {

    @Test
    public void testThrowingSupplier() throws Exception {
        ThrowingSupplier<String> supplier = () -> "Hola";
        assertEquals("Hola", supplier.get());
    }

    @Test (expected = ToolException.class)
    public void testThrowingSupplierWrap() {
        ThrowingSupplier<String> supplier = () -> {throw new Exception("Fallo");};
        ThrowingSupplier.wrap(supplier).get();
    }

    @Test
    public void testThrowingRunnable() throws Exception {
        final boolean[] run = {false};
        ThrowingRunnable r = () -> run[0] = true;
        r.run();
        assertTrue(run[0]);
    }

    @Test(expected = ToolException.class)
    public void testThrowingRunnableWrap() {
        ThrowingRunnable r = () -> { throw new Exception("Error"); };
        ThrowingRunnable.wrap(r).run();
    }

    @Test
    public void testThrowingFunction() throws Exception {
        ThrowingFunction<Integer, Integer> f = x -> x * 2;
        assertEquals(Integer.valueOf(6), f.apply(3));
    }

    @Test(expected = ToolException.class)
    public void testThrowingFunctionWrap() {
        ThrowingFunction<Integer, Integer> f = x -> { throw new Exception("Error"); };
        ThrowingFunction.wrap(f).apply(1);
    }

    @Test
    public void testThrowingConsumer() throws Exception {
        final int[] x = {0};
        ThrowingConsumer<Integer> c = i -> x[0] = i;
        c.accept(5);
        assertEquals(5, x[0]);
    }

    @Test(expected = ToolException.class)
    public void testThrowingConsumerWrap() {
        ThrowingConsumer<Integer> c = i -> { throw new Exception("Error"); };
        ThrowingConsumer.wrap(c).accept(1);
    }
}
