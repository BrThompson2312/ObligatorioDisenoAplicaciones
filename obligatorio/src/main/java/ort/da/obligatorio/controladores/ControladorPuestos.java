package ort.da.obligatorio.controladores;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/puestos")
public class ControladorPuestos {
    
    @GetMapping("/")
    public String saludar() {
        return "Controlador Puestos";
    }
}
