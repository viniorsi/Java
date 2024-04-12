package exemplo2SIA;

import java.util.Scanner;

public class exercicio_vetor {

	public static void main(String[] args) {
		
		int [] v = new int [5];
		preencher (v);
		int maior = maiorValor(v);
		int menor = menorValor(v);
		
		System.out.println("maior valor = " +maior);
		System.out.println("menor valor = " +menor);
	}

	public static void preencher (int[] v) {
		
		Scanner teclado = new Scanner (System.in);
		for (int i =0; i<v.length;i++) {
			System.out.print("valor: ");
			v[i] = teclado.nextInt();
		}
		teclado.close();
		
	}
	
	public static int maiorValor (int[] v) {
		int maior = v[0];
		for(int i = 0; i<v.length;i++) {
			if (v[i]> maior) {
				maior = v[i];
			}
		}
		return maior;
	}
	
	public static int menorValor (int[] v) {
		int menor = v[0];
		for(int i = 0; i<v.length;i++) {
			if(v[i]<menor) {
				menor = v[i];
			}
		}
		return menor;
	}
}
