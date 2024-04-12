package TGDI.API.Entities;


import TGDI.API.DTOS.Cliente.CadastroClienteDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.InputMismatchException;

@Table(name = "clientes")
@Entity(name = "Cliente")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Long id;
    private String CPF;
    private String nome;
    private String email;
    private Double saldo;
    private Long id_Empresa;

    public Cliente(CadastroClienteDTO dados) {

        this.CPF = dados.CPF();
        this.nome = dados.nome();
        this.email = dados.email();
        this.saldo = dados.saldo();
        this.id_Empresa = dados.id_Empresa();

    }


    public String validaCpf(String cpf){

        cpf = cpf.replaceAll("[^0-9]", "");

        char dig1,dig2;
        int soma,resto,peso;
        try{

            soma= 0;
            peso = 10;

            for(int i = 0; i < 9; i++){
                soma += (cpf.charAt(i)-48) * peso;
                peso -= 1;
            }

            resto = 11 - (soma % 11);

            if((resto==10) || (resto==11)){
                dig1 = '0';
            }else{
                dig1 = (char)(resto+48);
            }

            soma = 0;
            peso = 11;

            for(int i = 0; i < 10; i++){
                soma += (cpf.charAt(i)-48) * peso;
                peso -= 1;
            }

            resto = 11 - (soma % 11);

            if((resto==10) || (resto==11)){
                dig2 = '0';
            }else{
                dig2 = (char)(resto+48);
            }

            if ((dig1 == cpf.charAt(9)) && (dig2 == cpf.charAt(10))){
                return cpf;
            }else {
                return null;
            }

        }catch (InputMismatchException e){
            return null;
        }


    }

    public void deposito(Double valor){

        this.saldo -=  valor;

    }

    public void saque(Double valor){
        this.saldo += valor;
    }
}
