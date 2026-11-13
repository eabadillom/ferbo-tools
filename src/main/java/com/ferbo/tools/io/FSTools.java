package com.ferbo.tools.io;

import java.io.File;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase utilitaria (Singleton) para la gestión y obtención de recursos ubicados
 * dentro del classpath del proyecto.
 */
public final class FSTools {
	private static final Logger log = LogManager.getLogger(FSTools.class);

	/**
	 * Constructor privado para evitar la instanciación externa.
	 */
	private FSTools() {
	}

	/**
	 * Clase interna estática encargada de mantener la única instancia de
	 * {@link FSTools}. La JVM garantiza que esta clase no se cargará en memoria
	 * hasta que se invoque {@link #getInstance()}.
	 */
	private static class Holder {
		private static final FSTools INSTANCE = new FSTools();
	}

	/**
	 * Obtiene la única instancia disponible de {@link FSTools}.
	 *
	 * @return La instancia Singleton de {@link FSTools}.
	 */
	public static FSTools getInstance() {
		return Holder.INSTANCE;
	}

	/**
	 * Obtiene la ruta absoluta en el sistema de archivos de un recurso ubicado en
	 * el classpath.
	 *
	 * @param resource La ruta relativa del recurso (ej. "/config/app.properties").
	 * @return La ruta completa del archivo como {@link String}, o {@code null} si
	 *         el recurso no se encuentra.
	 */
	public String getResourcePath(String resource) {
		String resourcePath = null;
		File fileResourcePath = null;
		
		try {
			fileResourcePath = new File(getClass().getResource(resource).getFile());
			resourcePath = fileResourcePath.getPath();
		} catch (Exception e) {
			log.error("Error al cargar el recurso: {}", resource, e);
		}

		return resourcePath;
	}

	/**
	 * Obtiene un flujo de entrada ({@link InputStream}) para leer un recurso
	 * ubicado en el classpath.
	 * <p>
	 * Este método es recomendado cuando la aplicación se ejecuta empaquetada (por
	 * ejemplo, dentro de un archivo JAR), donde los recursos no pueden accederse
	 * directamente como un archivo físico del sistema.
	 * </p>
	 *
	 * @param resource La ruta relativa del recurso dentro del classpath (con barra
	 *                 inicial, ej. "/config/app.properties").
	 * @return El {@link InputStream} del recurso solicitado, o {@code null} si no
	 *         se encuentra o ocurre un error.
	 */
	public InputStream getResourceStream(String resource) {
		InputStream input = null;

		try {
			input = getClass().getClassLoader().getResourceAsStream(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return input;
	}
}
