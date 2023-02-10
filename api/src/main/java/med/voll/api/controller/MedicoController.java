package med.voll.api.controller;

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

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.model.DadosAtualizacaoMedico;
import med.voll.api.medico.model.DadosCadastroMedico;
import med.voll.api.medico.model.DadosListagemMedico;
import med.voll.api.medico.model.Medico;
import med.voll.api.medico.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar (@RequestBody @Valid DadosCadastroMedico dados) {
		repository.save(new Medico(dados));
		//System.out.println(dados);
	}
	
	
	@GetMapping
	public Page<DadosListagemMedico> listar( @PageableDefault(size = 10, sort = {"nome"}) Pageable pagina) {
		return repository.findAll(pagina).map(DadosListagemMedico::new);
	}
	
	@PutMapping
	@Transactional
	public void Atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void Excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
