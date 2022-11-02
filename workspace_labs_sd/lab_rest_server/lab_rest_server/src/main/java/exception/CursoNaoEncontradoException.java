package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception que representa um curso nao encontrado pelo id informado
 * @author gusta
 *since 10 set 2022
 */
public class CursoNaoEncontradoException extends ResponseStatusException{
	
	private static final long serialVersionUID = -2503400944594581603L;
	
	public CursoNaoEncontradoException(Long cursoId) {
		super(HttpStatus.NOT_FOUND, String.format("Nenhum curso encontrado com o id [%s]", cursoId));
	}

}
