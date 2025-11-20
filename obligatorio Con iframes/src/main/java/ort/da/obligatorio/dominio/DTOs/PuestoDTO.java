package ort.da.obligatorio.dominio.DTOs;

import java.util.ArrayList;
import java.util.List;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.dominio.Puestos.Tarifa;
import ort.da.obligatorio.dominio.DTOs.TarifaDTO;

public class PuestoDTO {
    private String nombre;
    private List<TarifaDTO> tarifas;

    public PuestoDTO() {
    }

    public PuestoDTO(Puesto puesto) {
        this.nombre = puesto.getNombre();
        this.tarifas = new ArrayList<>();
        
        if (puesto.getTarifas() != null) {
            for (Tarifa tarifa : puesto.getTarifas()) {
                this.tarifas.add(new TarifaDTO(tarifa));
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<TarifaDTO> getTarifas() {
        return tarifas;
    }

    public void setTarifas(List<TarifaDTO> tarifas) {
        this.tarifas = tarifas;
    }

    public static List<PuestoDTO> fromList(List<Puesto> puestos) {
        List<PuestoDTO> puestosDTO = new ArrayList<>();
        if (puestos != null) {
            for (Puesto puesto : puestos) {
                puestosDTO.add(new PuestoDTO(puesto));
            }
        }
        return puestosDTO;
    }
}
