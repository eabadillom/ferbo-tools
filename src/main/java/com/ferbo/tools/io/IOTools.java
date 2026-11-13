package com.ferbo.tools.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase de utilidad con métodos estáticos para el manejo y cierre seguro de flujos de entrada y salida (I/O).
 */
public class IOTools {
	
	private static final Logger log = LogManager.getLogger(IOTools.class);

	/**
	 * Finaliza y cierra de forma segura un {@link ZipOutputStream}, ignorando cualquier excepción.
	 *
	 * @param output el flujo ZIP a cerrar; puede ser nulo.
	 */
	public static synchronized void close(ZipOutputStream output) {

		try {
			if (output != null) {
				output.finish();
				output.close();
			}
		} catch (Exception ex) {
			log.error("Error al cerrar el objeto ZipOutputStream...", ex);
		} finally {
			output = null;
		}
	}

	/**
	 * Cierra de forma segura un {@link OutputStream}, ignorando cualquier excepción.
	 *
	 * @param output el flujo de salida a cerrar; puede ser nulo.
	 */
	public static synchronized void close(OutputStream output) {

		try {
			if (output != null) {
				output.close();
			}
		} catch (Exception ex) {
			log.error("Error al cerrar el objeto OutputStream...", ex);
		} finally {
			output = null;
		}
	}

	/**
	 * Vacía el búfer (flush) y cierra de forma segura un {@link Writer}, ignorando cualquier excepción.
	 *
	 * @param writer el escritor a cerrar; puede ser nulo.
	 */
	public static synchronized void close(Writer writer) {
		try {
			if (writer != null) {
				writer.flush();
			}
		} catch (Exception ex) {
			log.error("Error al limpiar el objeto Writer...", ex);
		}

		try {
			if (writer != null) {
				writer.close();
			}
		} catch (Exception ex) {
			log.error("Error al cerrar el objeto Writer...", ex);
		} finally {
			writer = null;
		}
	}

	/**
	 * Cierra de forma segura un {@link InputStream}, ignorando cualquier excepción.
	 *
	 * @param input el flujo de entrada a cerrar; puede ser nulo.
	 */
	public static synchronized void close(InputStream input) {

		try {
			if (input != null) {
				input.close();
			}
		} catch (Exception ex) {
			log.error("Error al cerrar el objeto InputStream", ex);
		} finally {
			input = null;
		}
	}

	/**
	 * Cierra de forma segura un {@link BufferedReader}, ignorando cualquier excepción.
	 *
	 * @param reader el lector a cerrar; puede ser nulo.
	 */
	public static synchronized void close(BufferedReader reader) {
		try {
			if (reader != null)
				reader.close();
		} catch (Exception ex) {
			log.error("Error al cerrar el objeto BufferedReader...", ex);
		} finally {
			reader = null;
		}

	}

	/**
	 * Lee todo el contenido de un {@link InputStream} y lo devuelve como un arreglo de bytes.
	 *
	 * @param input el flujo de entrada desde el cual se leerán los datos.
	 * @return un arreglo de bytes con todo el contenido leído.
	 * @throws IOException si ocurre un error de entrada/salida durante la lectura.
	 */
	public static synchronized byte[] read(InputStream input) throws IOException {
		byte[] resultado = null;
		byte[] buffer = new byte[1024];

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int nRead;

		while ((nRead = input.read(buffer, 0, buffer.length)) != -1) {
			output.write(buffer, 0, nRead);
		}

		resultado = output.toByteArray();
		return resultado;
	}
	
	/**
	 * Convierte un arreglo de bytes en un flujo de entrada ({@link InputStream}).
	 *
	 * @param contenido el arreglo de bytes a convertir; no debe ser nulo.
	 * @return un {@link ByteArrayInputStream} que permite leer los datos del arreglo.
	 * @throws IllegalArgumentException si {@code contenido} es {@code null}.
	 */
	public static synchronized InputStream toInputStream(byte[] contenido) {
		if(contenido == null)
			throw new IllegalArgumentException("El parámetro 'contenido' no puede ser nulo.");
		return new ByteArrayInputStream(contenido);
	}

	/**
	 * Convierte un String en un InputStream utilizando UTF-8 por defecto.
	 *
	 * @param contenido El texto a convertir.
	 * @return Un ByteArrayInputStream con los bytes del texto.
	 * @throws IllegalArgumentException si el contenido es nulo.
	 */
	public static synchronized InputStream toInputStream(String contenido) {
		return toInputStream(contenido, StandardCharsets.UTF_8);
	}

	/**
	 * Convierte un String en un InputStream utilizando una codificación específica.
	 *
	 * @param contenido El texto a convertir.
	 * @param charset   La codificación de caracteres a utilizar (ej.
	 *                  StandardCharsets.ISO_8859_1).
	 * @return Un ByteArrayInputStream con los bytes del texto.
	 * @throws IllegalArgumentException si el contenido o el charset son nulos.
	 */
	public static synchronized InputStream toInputStream(String contenido, Charset charset) {
		if (contenido == null) {
			throw new IllegalArgumentException("El parámetro 'contenido' no puede ser nulo.");
		}
		if (charset == null) {
			throw new IllegalArgumentException("El parámetro 'charset' no puede ser nulo.");
		}

		byte[] bytes = contenido.getBytes(charset);
		return toInputStream(bytes);
	}
}
