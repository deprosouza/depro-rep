package br.com.depro.mugetsu.model.cenum;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 08.06.2012
 */
public enum Alfabeto {

	NUM ("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "!", ".", "(", "#"),
	A ("A"),
	B ("B"),
	C ("C"),
	D ("D"),
	E ("E"),
	F ("F"),
	G ("G"),
	I ("I"),
	H ("H"),
	J ("J"),
	K ("K"),
	L ("L"),
	M ("M"),
	N ("N"),
	O ("O"),
	P ("P"),
	Q ("Q"),
	R ("R"),
	S ("S"),
	T ("T"),
	U ("U"),
	V ("V"),
	X ("X"),
	Y ("Y"),
	W ("W"),
	Z ("Z");
	
	private String[] significados;
	
	Alfabeto (String... letras) {
		this.significados = letras;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLabel() {
		String retorno = "";
		if (significados.length > 1) {
			retorno = significados[significados.length - 1];
		} else {
			retorno = significados[0];
		}
		return retorno;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getValores() {
		return Arrays.asList(significados);
	}
}
