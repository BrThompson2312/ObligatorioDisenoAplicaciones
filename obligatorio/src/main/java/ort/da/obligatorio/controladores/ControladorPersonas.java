package ort.da.obligatorio.controladores;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
public class ControladorPersonas {

    @GetMapping("/saludar")
    public String saludar() {
        return "Controlador Personas";
    }
    
}
