package medi.col.api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class apiController {
    
    @GetMapping("/index")
    public String Inicial(){
        return "index";
    }
    @GetMapping("/medicos")
    public String medicos(){
        return "medicos";
    }
    @GetMapping("/pacientes")
    public String pacientes(){
        return "pacientes";
    }

}
