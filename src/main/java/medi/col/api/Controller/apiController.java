package medi.col.api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/api")
public class apiController {
    
    @GetMapping("/index")
    public String Inicial(){
        return "index";
    }
    
}
