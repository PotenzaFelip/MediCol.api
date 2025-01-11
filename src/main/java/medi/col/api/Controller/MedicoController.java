package medi.col.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medi.col.api.Medico.DadosListMedico;
import medi.col.api.Medico.Medico;
import medi.col.api.Medico.MedicoRepository;
import medi.col.api.Medico.dadosCadastroMedico;



@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;
    
    @PostMapping("cadastro")
    @Transactional
    public void cadasrtoMedico(@RequestBody @Valid dadosCadastroMedico Dados) {
        repository.save(new Medico(Dados));
    }
    @GetMapping("buscar")
    public Page<DadosListMedico> GetMedicos(@PageableDefault(size=3,page=0,sort="nome")Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListMedico::new);
    }
    
    
}
