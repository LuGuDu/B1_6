package practica.utilidades;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Esta clase se encarga de filtrar a los JFileChooser la visualización
 * de archivos .json
 * 
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 15/11/2020
 */

public class ImageFilter extends FileFilter {

	@Override
	public boolean accept(File f) {

		boolean aceptar = f.isDirectory();
		if (!aceptar) {
			String extension = getExtension(f);
			if (extension != null)
				aceptar = extension.equals("json");
		}
		return aceptar;
	}

	@Override
	public String getDescription() {
		return "Fichero de imagen (*.json)";
	}

	private String getExtension(File f) {
		String s = f.getPath();
		String extension = null;
		int i = s.lastIndexOf('.');
		if (i > 0 && i < s.length() - 1)
			extension = s.substring(i + 1).toLowerCase();
		return extension;
	}
}
