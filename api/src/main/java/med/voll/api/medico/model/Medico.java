package med.voll.api.medico.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.model.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
	
	public Medico() {
		
	}

	public Medico(DadosCadastroMedico dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.setTelefone(dados.telefone());
		this.crm = dados.crm();
		this.especialidade = dados.especialidade();
		this.endereco = new Endereco(dados.endereco());
	}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String crm; 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@Embedded
	private Endereco endereco;

	public void atualizarInformacoes(@Valid DadosAtualizacaoMedico dados) {
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
		if (dados.telefone() != null) {
			this.telefone = dados.telefone();
		}
		if (dados.endereco() != null) {
			this.endereco.atualizarInformacoes(dados.endereco());
		}
		
	}
	
	

}
