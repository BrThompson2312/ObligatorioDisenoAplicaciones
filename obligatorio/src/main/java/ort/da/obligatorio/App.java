package ort.da.obligatorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import ort.da.obligatorio.dominio.EstadosPropietario.EstadoPropietarioAbstracto;
import ort.da.obligatorio.dominio.EstadosPropietario.Habilitado;
import ort.da.obligatorio.dominio.EstadosPropietario.Penalizado;
import ort.da.obligatorio.dominio.EstadosPropietario.Deshabilitado;
import ort.da.obligatorio.dominio.EstadosPropietario.Suspendido;
import ort.da.obligatorio.dominio.Personas.Administrador;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.servicios.Fachada;


@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		cargarDatosDePrueba();
	}

	private static void cargarDatosDePrueba() {
		//Estados
		EstadoPropietarioAbstracto estadoHabilitado = new Habilitado();
		EstadoPropietarioAbstracto estadoDeshabilitado = new Deshabilitado();
		EstadoPropietarioAbstracto estadoSuspendido = new Suspendido();
		EstadoPropietarioAbstracto estadoPenalizado = new Penalizado();
		Fachada.getInstancia().agregarEstado(estadoHabilitado);
		Fachada.getInstancia().agregarEstado(estadoDeshabilitado);
		Fachada.getInstancia().agregarEstado(estadoSuspendido);
		Fachada.getInstancia().agregarEstado(estadoPenalizado);
		//Propietarios
		Propietario p1 = new Propietario("23456789", "123", "Usuario Propietario", 2000, 500, estadoHabilitado);
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
