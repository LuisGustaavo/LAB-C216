package br.inatel.labs.labrest.server.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.inatel.labs.labrest.server.model.Curso;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CursoControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void deveListarCursos() {
		webTestClient.get()
		.uri("/curso")
		.exchange()
		.expectStatus()
			.isOk() //status ok ?
		.expectBody()
			.returnResult(); //retornou resultado?
	}	

	@Test
    void dadoCursoIdValido_quandoGetCursoPeloId_entaoRespondeComCursoValido(){
        Long cursoIdValido = 1L;

        Curso cursoRespondido = webTestClient.get()
                .uri("/curso/" + cursoIdValido)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Curso.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(cursoRespondido);
        assertEquals(cursoRespondido.getId(),cursoIdValido);

	assertThat(cursoRespondido).isNotNull();
	assertThat(cursoIdValido).isEqualTo(cursoRespondido.getId());	
    }

    @Test
    void dadoCursoIdInvalido_quandoGetCursoPeloId_entaoRespondeComStatusNotFound(){
        Long cursoIdInvalido = 99L;

        webTestClient.get()
                .uri("/curso/" + cursoIdInvalido)
                .exchange()
                .expectStatus().isNotFound();
    }
    
    //casa
    
    @Test
    void dadoNovoCursoValido_quandoPostCurso_entaoResponseComStatusCreated(){
        Curso novoCurso = new Curso();
        novoCurso.setDescricao("REST");
        novoCurso.setCargaHoraria(80);

        Curso cursoRespondido = webTestClient.post()
                .uri("/curso")
                .bodyValue(novoCurso)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Curso.class)
                .returnResult().getResponseBody();

        assertThat(cursoRespondido).isNotNull();
        assertThat(cursoRespondido.getId()).isNotNull();
    }

    @Test
    void dadoCursoValido_quandoPutCurso_entaoResponseComStatusAccepted() {
        Curso cursoExistente = new Curso(1L, "REST", 80);

         webTestClient
                .put()
                .uri("/curso")
                .bodyValue(cursoExistente)
                .exchange()
                .expectStatus().isAccepted();
    }

    @Test
	void dadoCursoValido_quandoDeleteCursoPeloId_entaoResponseComStatusDeleted() {
		Long cursoIdValidoRemove = 1L;
	
		Curso cursoRespondido = webTestClient.delete()
			.uri("/curso/" + cursoIdValidoRemove)
			.exchange()
			.expectStatus()
				.isNoContent()
			.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(cursoRespondido).isNull();
	}

    @Test
    void dadoCursoInvalido_quandoDeleteCursoPeloId_respostaComStatusNotFound(){
        Long cursoIdInvalidoRemove = 99L;

        webTestClient
                .delete()
                .uri("/curso/" + cursoIdInvalidoRemove)
                .exchange()
                .expectStatus().isNotFound();
    }
}   


