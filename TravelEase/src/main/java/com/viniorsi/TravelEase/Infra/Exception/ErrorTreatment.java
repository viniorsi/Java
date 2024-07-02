package com.viniorsi.TravelEase.Infra.Exception;

//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class Errortreatment {
//
//
//
//
//        @ExceptionHandler(EntityNotFoundException.class)
//        public ResponseEntity tratarErro404(){
//
//            return ResponseEntity.notFound().build();
//
//        }
//
//        @ExceptionHandler(MethodArgumentNotValidException.class)
//        public ResponseEntity tratarErro400(MethodArgumentNotValidException e){
//
//            var erros = e.getFieldErrors();
//
////     pega um objeto do tipo erros e mapeira para DadosErroValidacao, inicia a variavel e transforma ela em lista
//            return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
//
//        }
//        @ExceptionHandler(ValidacaoException.class)
//        public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException e){
//
//            return ResponseEntity.badRequest().body(e.getMessage());
//
//        }
//
//
//
//        private record DadosErroValidacao(String campo,String mensagem){
//            public DadosErroValidacao(FieldError erro){
//
//                this(erro.getField(), erro.getDefaultMessage());
//
//            }
//        }
//
//
//}
