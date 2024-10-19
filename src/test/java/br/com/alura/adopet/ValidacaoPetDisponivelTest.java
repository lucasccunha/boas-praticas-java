package br.com.alura.adopet;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.validacoes.ValidacaoPetDisponivel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidacaoPetDisponivelTest {

	@Test
	void deveriaPermitirSolicitacaoDeAdocaoPet() {

		// ARRANGE
		SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(
				7l,
				2l,
				"Motivo qualquer"
		);

		ValidacaoPetDisponivel validacao = new ValidacaoPetDisponivel();

		//ASSERT + ACT
		Assertions.assertDoesNotThrow(() -> validacao.validar(dto));
	}
}