package br.com.alura.adopet;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoPetDisponivel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

	@InjectMocks
	ValidacaoPetDisponivel validacao;

	@Mock
	private PetRepository petRepository;

	@Mock
	private Pet pet;

	@Mock
	private SolicitacaoAdocaoDto dto;

	@Test
	void deveriaPermitirSolicitacaoDeAdocaoPet() {

		// ARRANGE
		BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
		BDDMockito.given(pet.getAdotado()).willReturn(false);


		//ASSERT + ACT
		Assertions.assertDoesNotThrow(() -> validacao.validar(dto));
	}

	@Test
	void naoDeveriaPermitirSolicitacaoDeAdocaoPet() {

		// ARRANGE
		BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
		BDDMockito.given(pet.getAdotado()).willReturn(true);


		//ASSERT + ACT
		Assertions.assertThrows(ValidacaoException.class,() -> validacao.validar(dto));
	}
}
