package com.ferbo.tools.io;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IOToolsTest {

	@Test
	@DisplayName("close(ZipOutputStream) debe finalizar y cerrar el flujo sin lanzar errores")
	void testCloseZipOutputStream() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		zos.putNextEntry(new ZipEntry("prueba.txt"));
		zos.write("contenido".getBytes(StandardCharsets.UTF_8));

		assertDoesNotThrow(() -> IOTools.close(zos));
		assertTrue(baos.size() > 0, "El flujo ZIP debe contener datos tras cerrarse");
		assertDoesNotThrow(() -> IOTools.close((ZipOutputStream) null));
	}

	@Test
	@DisplayName("close(OutputStream) debe cerrar el flujo y capturar excepciones")
	void testCloseOutputStream() {
		AtomicBoolean cerrado = new AtomicBoolean(false);
		OutputStream streamConError = new OutputStream() {
			@Override
			public void write(int b) {
			}

			@Override
			public void close() throws IOException {
				cerrado.set(true);
				throw new IOException("Error simulado al cerrar");
			}
		};

		assertDoesNotThrow(() -> IOTools.close(streamConError));
		assertTrue(cerrado.get());
		assertDoesNotThrow(() -> IOTools.close((OutputStream) null));
	}

	@Test
	@DisplayName("close(Writer) debe hacer flush y cerrar el escritor sin lanzar errores")
	void testCloseWriter() {
		StringWriter sw = new StringWriter();
		sw.write("Texto de prueba");

		assertDoesNotThrow(() -> IOTools.close(sw));
		assertEquals("Texto de prueba", sw.toString());

		Writer writerConError = new Writer() {
			@Override
			public void write(char[] cbuf, int off, int len) {
			}

			@Override
			public void flush() throws IOException {
				throw new IOException("Error en flush");
			}

			@Override
			public void close() throws IOException {
				throw new IOException("Error en close");
			}
		};

		assertDoesNotThrow(() -> IOTools.close(writerConError));
		assertDoesNotThrow(() -> IOTools.close((Writer) null));
	}

	@Test
	@DisplayName("close(InputStream) debe cerrar el flujo y capturar excepciones")
	void testCloseInputStream() {
		AtomicBoolean cerrado = new AtomicBoolean(false);
		InputStream streamConError = new InputStream() {
			@Override
			public int read() {
				return -1;
			}

			@Override
			public void close() throws IOException {
				cerrado.set(true);
				throw new IOException("Error simulado al cerrar");
			}
		};

		assertDoesNotThrow(() -> IOTools.close(streamConError));
		assertTrue(cerrado.get());
		assertDoesNotThrow(() -> IOTools.close((InputStream) null));
	}

	@Test
	@DisplayName("close(BufferedReader) debe cerrar el lector y capturar excepciones")
	void testCloseBufferedReader() {
		BufferedReader reader = new BufferedReader(new StringReader("línea"));
		assertDoesNotThrow(() -> IOTools.close(reader));
		assertThrows(IOException.class, reader::read, "El reader debería estar cerrado");
		assertDoesNotThrow(() -> IOTools.close((BufferedReader) null));
	}

	@Test
	@DisplayName("read(InputStream) debe leer todos los bytes correctamente")
	void testReadInputStream() throws IOException {
		byte[] esperado = "Mensaje largo de prueba con acentos: áéíóú ñ".getBytes(StandardCharsets.UTF_8);
		ByteArrayInputStream input = new ByteArrayInputStream(esperado);

		byte[] resultado = IOTools.read(input);

		assertArrayEquals(esperado, resultado);
	}

	@Test
	@DisplayName("read(InputStream) con flujo vacío debe devolver un arreglo vacío")
	void testReadInputStreamVacio() throws IOException {
		ByteArrayInputStream input = new ByteArrayInputStream(new byte[0]);
		byte[] resultado = IOTools.read(input);

		assertNotNull(resultado);
		assertEquals(0, resultado.length);
	}

	@Test
	@DisplayName("toInputStream(byte[]) debe convertir el arreglo o lanzar excepción si es nulo")
	void testToInputStreamBytes() throws IOException {
		byte[] datos = { 10, 20, 30, 40 };
		InputStream is = IOTools.toInputStream(datos);

		assertNotNull(is);
		assertArrayEquals(datos, IOTools.read(is));

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> IOTools.toInputStream((byte[]) null));
		assertEquals("El parámetro 'contenido' no puede ser nulo.", ex.getMessage());
	}

	@Test
	@DisplayName("toInputStream(String) debe convertir usando UTF-8 o lanzar excepción si es nulo")
	void testToInputStreamStringDefault() throws IOException {
		String texto = "Hola mundo UTF-8: ñ á é";
		InputStream is = IOTools.toInputStream(texto);

		byte[] leido = IOTools.read(is);
		assertEquals(texto, new String(leido, StandardCharsets.UTF_8));

		assertThrows(IllegalArgumentException.class, () -> IOTools.toInputStream((String) null));
	}

	@Test
	@DisplayName("toInputStream(String, Charset) debe respetar la codificación y validar nulos")
	void testToInputStreamStringCharset() throws IOException {
		String texto = "Canción";
		Charset iso = StandardCharsets.ISO_8859_1;

		InputStream is = IOTools.toInputStream(texto, iso);
		byte[] leido = IOTools.read(is);

		assertArrayEquals(texto.getBytes(iso), leido);
		assertEquals(texto, new String(leido, iso));

		assertThrows(IllegalArgumentException.class, () -> IOTools.toInputStream(null, iso));
		assertThrows(IllegalArgumentException.class, () -> IOTools.toInputStream(texto, null));
	}
}
