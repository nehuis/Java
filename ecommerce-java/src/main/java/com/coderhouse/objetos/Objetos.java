package com.coderhouse.objetos;

public class Objetos {
	
	private static final int EDAD_MAXIMA = 16;
	
	String mensajeError = "El perro no vive más de 16 años";
	
		public String nombre;
		public String raza;
		public String color;
		public String tamanio;
		public int edad;
		public double peso;
		
		//metodos
		
		public void ladrar() {
			System.out.println("El perro está ladrando");
		}
		
		//getters y setters

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getRaza() {
			return raza;
		}

		public void setRaza(String raza) {
			this.raza = raza;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String getTamanio() {
			return tamanio;
		}

		public void setTamanio(String tamanio) {
			this.tamanio = tamanio;
		}

		public int getEdad() {
			return edad;
		}

		public void setEdad(int edad) throws Exception{
			if (edad <= 0 || edad > EDAD_MAXIMA) {
				throw new Exception(mensajeError);
			} else {				
				this.edad = edad;
			}
		}

		public double getPeso() {
			return peso;
		}

		public void setPeso(double peso) {
			this.peso = peso;
		}
		
		public void mostrarAtributos() {
			System.out.println("Mi perro " + this.getNombre() + " es un " + this.getRaza() + 
					" y tiene " + this.getEdad() + " año");
		}

		@Override
		public String toString() {
			return "Objetos [nombre=" + nombre + ", raza=" + raza + ", color=" + color + ", tamanio=" + tamanio
					+ ", edad=" + edad + ", peso=" + peso + "]";
		}
	
		

}
