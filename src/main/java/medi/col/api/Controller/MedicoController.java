package medi.col.api.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<Object> cadasrtoMedico(@RequestBody @Valid dadosCadastroMedico Dados) {
        try{
        repository.save(new Medico(Dados));
        return ResponseEntity.status(HttpStatus.CREATED).body(
            Map.of("success", true, "message", "Cadastro realizado com sucesso")
        );
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("success", false, "message", "Erro ao cadastrar médico")
            );
        }
    }
    @GetMapping("buscar")
    @Operation(summary = "GetMedicos",
    description = "Buscar Medicos",
    responses = {
        @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<Page<DadosListMedico>> GetMedicos(@PageableDefault(size=3,page=0,sort="nome")Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListMedico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("buscar/{id}")
    @Operation(summary = "GetMedicos",
    description = "Buscar Medicos",
    responses = {
        @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<DadosListMedico> GetMedicosByid(@PathVariable long id) {
        Optional<Medico> medicOptional=repository.findById(id);
        if(medicOptional.isPresent())
            return ResponseEntity.ok(new DadosListMedico(medicOptional.get()));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    @PutMapping("atualizar")
    @Transactional
    @Operation(summary = "Atualizar Medico",
            description = "Atualuizar Medico",
            responses = {
                @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
                @ApiResponse(responseCode = "400", description = "Requisição inválida")
            })
    public ResponseEntity<Object> atualizarmedico(@RequestBody @Valid DadosAtualizar dados) {
        var medico=repository.getReferenceById(dados.id());
        medico.atualizarInfos(dados);
        return ResponseEntity.ok().build();

    }
    
    @DeleteMapping("Delete/{id}")
    @Transactional
    @Operation(summary = "Deletar Medico",
            description = "Deletar Medico",
            responses = {
                @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
                @ApiResponse(responseCode = "400", description = "Requisição inválida")
            })
    public ResponseEntity<Object> deletarMedico(@PathVariable Long id){
        var medico=repository.getReferenceById(id);
        medico.Excluir();
        return ResponseEntity.noContent().build();
    }
    
}
