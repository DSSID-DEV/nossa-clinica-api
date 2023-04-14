package com.nossaclinica.api.validations;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nossaclinica.api.validations.anotations.CPF;

public class CpfValidation implements ConstraintValidator<CPF, String>{
	
	private static final Integer DIVISOR = 11;

	private Map<String, Integer> numeros;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		numeros = getNumerosDoCpf(value);
		String codigoVerificador = numeros.get("9").toString()+numeros.get("10").toString();
		
		String digitosVerificadores = verificarCalculoDoCpf(numeros);
		return digitosVerificadores.equals(codigoVerificador);
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

		Map<String, Integer> numeros = new HashMap<String, Integer>();
		
		value = value.replaceAll("\\D", "");
		char[] digitos = value.toCharArray();
		
		int posicao = 0;
		
		for (char digito : digitos) {
			numeros.put(String.valueOf(posicao), Integer.parseInt(String.valueOf(digito)));
			posicao++;
		}
		return numeros;
	}
}


