package com.nossaclinica.api.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Questao01 {

	private static int DIVISOR = 11;
	private String result;
	public static void main(String[] args) {
		String cpf = "010.750.114-77";
		Map<String, Integer> numeros;
		numeros = getNumerosDoCpf(cpf);
		String codigoVerificador = numeros.get("9").toString()+numeros.get("10").toString();
		
		String calcularCpf = verificarCalculoDoCpf(numeros);
		
		System.out.println("válido :" + calcularCpf );
		
		Boolean isValid = calcularCpf.equals(codigoVerificador);
		System.out.println("isValid: " + isValid);
		
	}
	
	private static String verificarCalculoDoCpf(Map<String, Integer> cpf) {
		return getPrimeiroDigitoVerificador(cpf, 1)+getPrimeiroDigitoVerificador(cpf, 0);
	}

private static String getPrimeiroDigitoVerificador(Map<String, Integer> numeros, int p) {
		
		int soma = 0;
		int cont = p == 0 ? 0 : 1;
		for(Entry<String, Integer> numero : numeros.entrySet()) {
			if (cont < 10) {
				soma += Math.multiplyExact(
						Integer.sum(Integer.parseInt(numero.getKey()), p), numero.getValue());
				cont++;
			}				
		}

		return String.valueOf(Math.floorMod(soma, DIVISOR));		
	}

	private static Map<String, Integer> getNumerosDoCpf(String value) {
		value = value.replaceAll("\\D", "");
		char[] digitos = value.toCharArray();
		Map<String, Integer> numeros = new HashMap<String, Integer>();
		int posicao = 0;
		for (char digito : digitos) {
			numeros.put(String.valueOf(posicao), Integer.parseInt(String.valueOf(digito)));
			posicao++;
		}
		return numeros;
	}
	

}
