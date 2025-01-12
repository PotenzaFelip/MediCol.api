package medi.col.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medi.col.api.Medico.DadosAtualizar;
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
    @Operation(summary = "CadastroMedico",
            description = "Cadastrar medico",
            responses = {
                @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
                @ApiResponse(responseCode = "400", description = "Requisição inválida")
            })
    public void cadasrtoMedico(@RequestBody @Valid dadosCadastroMedico Dados) {
        repository.save(new Medico(Dados));
    }
    @GetMapping("buscar")
    @Operation(summary = "GetMedicos",
    description = "Buscar Medicos",
    responses = {
        @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public Page<DadosListMedico> GetMedicos(@PageableDefault(size=3,page=0,sort="nome")Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListMedico::new);
    }
    
    @PutMapping("atualizar")
    @Transactional
    @Operation(summary = "Atualizar Medico",
            description = "Atualuizar Medico",
            responses = {
                @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
                @ApiResponse(responseCode = "400", description = "Requisição inválida")
            })
    public void atualizarmedico(@RequestBody @Valid DadosAtualizar dados) {
        var medico=repository.getReferenceById(dados.id());
        medico.atualizarInfos(dados);

    }
    
    @DeleteMapping("Delete/{id}")
    @Transactional
    @Operation(summary = "Deletar Medico",
            description = "Deletar Medico",
            responses = {
                @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
                @ApiResponse(responseCode = "400", description = "Requisição inválida")
            })
    public void deletarMedico(@PathVariable Long id){
        var medico=repository.getReferenceById(id);
        medico.Excluir();
    }
    
}
