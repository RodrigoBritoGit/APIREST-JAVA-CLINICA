package med.voll.api.medico.model;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.model.DadosEndereco;

public record DadosAtualizacaoMedico(
		@NotNull
		Long id,
		
		String nome,
		String telefone,
		DadosEndereco endereco) {

}
