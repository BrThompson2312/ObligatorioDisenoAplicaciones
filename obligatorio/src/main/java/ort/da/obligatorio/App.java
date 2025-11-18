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
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.servicios.Fachada;




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
		Puesto puesto1 = new Puesto("Puesto 1", "Ubicacion 1");
		Puesto puesto2 = new Puesto("Puesto 2", "Ubicacion 2");
		Puesto puesto3 = new Puesto("Puesto 3", "Ubicacion 3");

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
		Propietario p2 = new Propietario("34567890", "123", "Usuario Propietario 2", 1500, 300, estadoDeshabilitado);
		Fachada.getInstancia().agregarPersona(p1);
		Fachada.getInstancia().agregarPersona(p2);
		//Administradores
		Administrador a1 = new Administrador("12345678", "123", "Usuario Administrador");
		Administrador a2 = new Administrador("01234567", "123", "Usuario Administrador 2");
		Fachada.getInstancia().agregarPersona(a1);
		Fachada.getInstancia().agregarPersona(a2);

	}

}
