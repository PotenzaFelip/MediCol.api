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
import medi.col.api.Paciente.DadosAtualizarPacientes;
import medi.col.api.Paciente.DadosListPaciente;
import medi.col.api.Paciente.Paciente;
import medi.col.api.Paciente.PacienteRepository;
import medi.col.api.Paciente.dadosCadastroPaciente;




@RestController
@RequestMapping("/paciente")
@CrossOrigin(origins = "http://localhost:4200")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping("cadastro")
    @Transactional
    @Operation(summary = "CadastroPaciente",
            description = "Cadastrar Paciente",
            responses = {
                @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
                @ApiResponse(responseCode = "400", description = "Requisição inválida")
            })
    public ResponseEntity<Object> Cadpaciente(@RequestBody @Valid dadosCadastroPaciente dados) {
        try{
            repository.save(new Paciente(dados));
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("success", false, "message", "Erro ao cadastrar Paciente"));
        }
        
    }

    @GetMapping("buscar")
    @Operation(summary = "GetPacientes",
    description = "Buscar Pacientes",
    responses = {
        @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<Page<DadosListPaciente>> GetPaciente(@PageableDefault(size=3,page=0,sort="nome")Pageable paginacao) {
        var page= repository.findAllByAtivoTrue(paginacao).map(DadosListPaciente::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("buscar/{id}")
    @Operation(summary = "GetPacientes",
    description = "Buscar Pacientes",
    responses = {
        @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<DadosListPaciente> GetPacienteById(@PathVariable long id) {
        Optional<Paciente> PaciOptional = repository.findById(id);
        if(PaciOptional.isPresent())
            return ResponseEntity.ok(new DadosListPaciente(PaciOptional.get()));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    @PutMapping("atualizar")
    @Transactional
    @Operation(summary = "GetPacientes",
    description = "Buscar Pacientes",
    responses = {
        @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<Object> AtualizarPaciente(@RequestBody @Valid DadosAtualizarPacientes dados) {
        try{
            var paciente=repository.getReferenceById(dados.id());
            paciente.AtualizarPaciente(dados);
        
            return ResponseEntity.ok(dados);
        }
        catch(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("Delete/{id}")
    @Transactional
    @Operation(summary = "GetPacientes",
    description = "Buscar Pacientes",
    responses = {
        @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<Object> DelPaciente(@PathVariable long id){
        try{
            var paciente=repository.getReferenceById(id);
            paciente.Excluir();
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    
}
