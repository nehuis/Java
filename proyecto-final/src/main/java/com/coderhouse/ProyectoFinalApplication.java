package com.coderhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coderhouse.dao.DaoFactory;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;

@SpringBootApplication
public class ProyectoFinalApplication implements CommandLineRunner {
	
	@Autowired
	private DaoFactory dao;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		try {
			
			Producto producto1 = new Producto("Celular", 250);
			Producto producto2 = new Producto("Laptop", 500);
			Producto producto3 = new Producto("PlayStation", 650);
			Producto producto4 = new Producto("TV", 400);
			Producto producto5 = new Producto("Tablet", 300);
			
			Cliente cliente1 = new Cliente("Nehuel", "Caraballo", 45905243, "nehuel@gmail.com");
			Cliente cliente2 = new Cliente("Marcos", "De Azcarate", 45905244, "marcos@gmail.com");
			Cliente cliente3 = new Cliente("Manuel", "Pereyra", 45905245, "manuel@gmail.com");
			Cliente cliente4 = new Cliente("Joel", "Zevallos", 45905246, "joel@gmail.com");
			Cliente cliente5 = new Cliente("Bautista", "Martinez", 45905247, "bautista@gmail.com");
		
			dao.persistirCliente(cliente1);
			dao.persistirCliente(cliente2);
			dao.persistirCliente(cliente3);
			dao.persistirCliente(cliente4);
			dao.persistirCliente(cliente5);
			
			dao.persistirProducto(producto1);
			dao.persistirProducto(producto2); 
			dao.persistirProducto(producto3);
			dao.persistirProducto(producto4);
			dao.persistirProducto(producto5);
			
			System.out.println(producto1);
			
		}catch(Exception err) {
			err.printStackTrace();
		}
		
	}
	
}
