package com.gerenciador.tarefas.exceptions;

import com.gerenciador.tarefas.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExcecoesHandler {

    @ExceptionHandler(naoPermitirExcluirException.class)
    public ResponseEntity<ErrorResponse> naoPermitirExcluirExceptionHandler(naoPermitirExcluirException naoPermitirExcluirException){

        Map<String,String> response = new HashMap<>();
        response.put("codigo", ErrosEnum.NAO_PERMITIDO_EXCLUIR.toString());
        response.put("mensagem", "Não é permitido excluir uma tarefa com o status diferente de CRIADA");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .errors(Collections.singletonList(response))
                .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NaoPermitirAlterarStatusException.class)
    public ResponseEntity<ErrorResponse> naoPermitirAlterarStatusExceptionHandler(NaoPermitirAlterarStatusException naoPermitirAlterarStatus){

        Map<String,String> response = new HashMap<>();
        response.put("codigo", ErrosEnum.NAO_PERMITIR_ALTERAR_STATUS.toString());
        response.put("mensagem", naoPermitirAlterarStatus.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .errors(Collections.singletonList(response))
                .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.UNPROCESSABLE_ENTITY);
    }




    @ExceptionHandler(TarefaExistenteException.class)
    public ResponseEntity<ErrorResponse> tarefaExistenteExceptionHandler(TarefaExistenteException naoPermitirExcluirException){

        Map<String,String> response = new HashMap<>();
        response.put("codigo", ErrosEnum.TAREFA_EXISTENTE.toString());
        response.put("mensagem", naoPermitirExcluirException.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .errors(Collections.singletonList(response))
                .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
