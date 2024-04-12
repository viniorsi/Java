package TGDI.API.Entities;


import TGDI.API.DTOS.Empresa.CadastroEmpresaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.InputMismatchException;

@Table(name = "empresas")
@Entity(name = "Empresa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String CNPJ;
    private String nome;
    private String email;
    private Double saldo;
    private Double taxa;

    public Empresa(CadastroEmpresaDTO dados) {

        this.CNPJ = dados.CNPJ();
        this.nome = dados.nome();
        this.email = dados.email();
        this.saldo = dados.saldo();
        this.taxa = dados.taxa();




    }


    public String validaCnpj(String cnpj){

        cnpj = cnpj.replaceAll("[^0-9]", "");

        char dig1,dig2 = 0;
        int soma,resto,peso = 2;



        try{
            soma = 0;
            resto = 0;


            for(int i = 11; i >= 0; i--){
                soma += (cnpj.charAt(i) - 48) * peso;
                peso +=  1;

                if(peso == 10)
                    peso =2;
            }

            resto = soma % 11;
            if((resto == 0)|| (resto==1)){
                dig1 = '0';

            }else {
                dig1 = (char)((11-resto) + 48);
            }


            soma = 0;
            peso = 2;

            for(int i = 12; i >= 0; i--){

                soma += (cnpj.charAt(i) - 48) * peso;

                peso += 1;

                if(peso == 10)
                    peso =2;

            }

            resto = soma % 11;

            if((resto == 0)|| (resto==1)){
                dig2 = '0';
            }else{
                dig2 = (char) ((11-resto) + 48);
            }

            if((dig1 == cnpj.charAt(12)) && (dig2 == cnpj.charAt(13))){
                return cnpj;
            }else{
                return null;
            }


        }catch (InputMismatchException e){
            return (null);
        }
        
    }

    public void receberDeposito(double valor) {
            this.saldo += valor;
    }

    public void mandarSaque(double valor) {
        this.saldo -= valor;
    }
}
