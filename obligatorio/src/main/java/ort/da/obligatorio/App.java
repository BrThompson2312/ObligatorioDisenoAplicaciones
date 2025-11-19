package ort.da.obligatorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;
import ort.da.obligatorio.dominio.EstadosPropietario.EstadoPropietarioAbstracto;
import ort.da.obligatorio.dominio.EstadosPropietario.Habilitado;
import ort.da.obligatorio.dominio.EstadosPropietario.Penalizado;
import ort.da.obligatorio.dominio.Bonificaciones.Exonerados;
import ort.da.obligatorio.dominio.Bonificaciones.Frecuentes;
import ort.da.obligatorio.dominio.Bonificaciones.Trabajadores;
import ort.da.obligatorio.dominio.EstadosPropietario.Deshabilitado;
import ort.da.obligatorio.dominio.EstadosPropietario.Suspendido;
import ort.da.obligatorio.dominio.Personas.Administrador;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Personas.Vehiculo;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.servicios.Fachada;
import ort.da.obligatorio.dominio.Puestos.Transito;
import ort.da.obligatorio.dominio.Puestos.Tarifa;
import ort.da.obligatorio.dominio.Personas.CategoriaDeVehiculo;





@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		cargarDatosDePrueba();
	}

	private static void cargarDatosDePrueba() {
		//ServicioPuestos
		//Estrategias de Bonificacion
		Exonerados exonerados = new Exonerados();
		Frecuentes frecuentes = new Frecuentes();
		Trabajadores trabajadores = new Trabajadores();
		Fachada.getInstancia().agregarEstrategiaBonificacion(exonerados);
		Fachada.getInstancia().agregarEstrategiaBonificacion(frecuentes);
		Fachada.getInstancia().agregarEstrategiaBonificacion(trabajadores);
		//Puestos
		Puesto puesto1 = new Puesto("Puesto 101", "Ubicacion 1");
		puesto1.agregarTarifa(new Tarifa(100.0,new CategoriaDeVehiculo(CategoriaDeVehiculo.TipoVehiculo.AUTO)));
		puesto1.agregarTarifa(new Tarifa(50.0,new CategoriaDeVehiculo(CategoriaDeVehiculo.TipoVehiculo.MOTO)));
		puesto1.agregarTarifa(new Tarifa(150.0,new CategoriaDeVehiculo(CategoriaDeVehiculo.TipoVehiculo.CAMIONETA)));
		puesto1.agregarTarifa(new Tarifa(200.0,new CategoriaDeVehiculo(CategoriaDeVehiculo.TipoVehiculo.CAMION)));
		Puesto puesto2 = new Puesto("Puesto 102", "Ubicacion 2");
		puesto2 .agregarTarifa(new Tarifa(120.0,new CategoriaDeVehiculo(CategoriaDeVehiculo.TipoVehiculo.AUTO)));
		puesto2 .agregarTarifa(new Tarifa(60.0,new CategoriaDeVehiculo(CategoriaDeVehiculo.TipoVehiculo.MOTO)));
		puesto2 .agregarTarifa(new Tarifa(180.0,new CategoriaDeVehiculo(CategoriaDeVehiculo.TipoVehiculo.CAMIONETA)));
		puesto2 .agregarTarifa(new Tarifa(220.0,new CategoriaDeVehiculo(CategoriaDeVehiculo.TipoVehiculo.CAMION)));
		Puesto puesto3 = new Puesto("Puesto 103", "Ubicacion 3");
		puesto3.agregarTarifa(new Tarifa(110.0,new CategoriaDeVehiculo(CategoriaDeVehiculo.TipoVehiculo.AUTO)));
		puesto3.agregarTarifa(new Tarifa(55.0,new CategoriaDeVehiculo(CategoriaDeVehiculo.TipoVehiculo.MOTO)));
		puesto3.agregarTarifa(new Tarifa(160.0,new CategoriaDeVehiculo(CategoriaDeVehiculo.TipoVehiculo.CAMIONETA)));
		puesto3.agregarTarifa(new Tarifa(210.0,new CategoriaDeVehiculo(CategoriaDeVehiculo.TipoVehiculo.CAMION)));
		Fachada.getInstancia().agregarPuesto(puesto1);
		Fachada.getInstancia().agregarPuesto(puesto2);
		Fachada.getInstancia().agregarPuesto(puesto3);
		

		//ServicioPersonas
		//Estados
		EstadoPropietario estadoHabilitado = new Habilitado();
		EstadoPropietario estadoDeshabilitado = new Deshabilitado();
		EstadoPropietario estadoSuspendido = new Suspendido();
		EstadoPropietario estadoPenalizado = new Penalizado();
		Fachada.getInstancia().agregarEstado(estadoHabilitado);
		Fachada.getInstancia().agregarEstado(estadoDeshabilitado);
		Fachada.getInstancia().agregarEstado(estadoSuspendido);
		Fachada.getInstancia().agregarEstado(estadoPenalizado);
		//Propietarios
		Propietario p1 = new Propietario("23456789", "123", "Usuario Propietario", 2000, 500, estadoHabilitado);
			try {
				Fachada.getInstancia().asignarBonificacion(exonerados,puesto1,p1);
				Fachada.getInstancia().asignarBonificacion(frecuentes,puesto2,p1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Vehiculos
			Vehiculo v1 = new Vehiculo("ABC123", "Rojo", "Toyota", 
					new ort.da.obligatorio.dominio.Personas.CategoriaDeVehiculo(
							ort.da.obligatorio.dominio.Personas.CategoriaDeVehiculo.TipoVehiculo.AUTO));
			Vehiculo v2 = new Vehiculo("DEF456", "Azul", "Honda", 
					new ort.da.obligatorio.dominio.Personas.CategoriaDeVehiculo(
							ort.da.obligatorio.dominio.Personas.CategoriaDeVehiculo.TipoVehiculo.MOTO));
			Vehiculo v3 = new Vehiculo("GHI789", "Blanco", "Ford", 
					new ort.da.obligatorio.dominio.Personas.CategoriaDeVehiculo(
							ort.da.obligatorio.dominio.Personas.CategoriaDeVehiculo.TipoVehiculo.CAMIONETA));
			p1.agregarVehiculo(v1);
			p1.agregarVehiculo(v2);
			p1.agregarVehiculo(v3);
		
		

		Propietario p2 = new Propietario("34567890", "123", "Usuario Propietario 2", 1500, 300, estadoDeshabilitado);
		Fachada.getInstancia().agregarPersona(p1);
		Fachada.getInstancia().agregarPersona(p2);
		//Transitos
		try {
			Fachada.getInstancia().emularTransito(p1, puesto1, v1);
			Fachada.getInstancia().emularTransito(p1, puesto2, v2);
			Fachada.getInstancia().emularTransito(p1, puesto3, v3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Administradores
		Administrador a1 = new Administrador("12345678", "123", "Usuario Administrador");
		Administrador a2 = new Administrador("01234567", "123", "Usuario Administrador 2");
		Fachada.getInstancia().agregarPersona(a1);
		Fachada.getInstancia().agregarPersona(a2);

	}

}
