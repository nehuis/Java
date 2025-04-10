package com.coderhouse;

import com.coderhouse.objetos.Objetos;

public class ProyectoFinal {

	public static void main(String[] args) throws Exception {
		
		Objetos primerPerro = new Objetos();
		
		primerPerro.setRaza("Labrador");
		primerPerro.setColor("Rubio");
		primerPerro.setTamanio("Mediano");
		primerPerro.setEdad(1);
		primerPerro.setPeso(4.5);
		primerPerro.setNombre("Dylan");
		
		Objetos segundoPerro = new Objetos();
		
		segundoPerro.setRaza("Pepe");
		segundoPerro.setColor("Negro");
		segundoPerro.setTamanio("Grande");
		segundoPerro.setEdad(3);
		segundoPerro.setPeso(4.5);
		segundoPerro.setNombre("Tomy");
		
		primerPerro.mostrarAtributos();
		segundoPerro.mostrarAtributos();
		
		System.out.println(primerPerro);
		System.out.println(segundoPerro);
	}

}
