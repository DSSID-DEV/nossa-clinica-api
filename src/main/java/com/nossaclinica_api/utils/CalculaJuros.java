package com.nossaclinica_api.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.function.DoubleToLongFunction;

public class CalculaJuros {

    public static void main(String[] args) {

//        calcularJuros(BigDecimal.valueOf(48.576));
//        calcularIdade(LocalDate.parse("1981-01-07"));
//        calcularPercentagem(BigDecimal.valueOf(170.00), 71);

        adicionarZeros("12125");
    }

    private static void adicionarZeros(String value) {
        int maxLenght = 5;
        String zeros = "00000".substring(0, maxLenght - value.length());
        System.out.println(zeros.concat(value));
    }

    private static void calcularPercentagem(BigDecimal valor, int i) {
        Double percentagem = String.valueOf(i).length() > 2 ? i / 1000.0 : i / 100.0;
        var resultado = valor.multiply(BigDecimal.valueOf(percentagem));
        var casasDecimais = valor.remainder(BigDecimal.ONE);
        if(casasDecimais.multiply(BigDecimal.TEN).compareTo(BigDecimal.TEN) < 0) {
            resultado = resultado.setScale(0, RoundingMode.DOWN);
        }
        System.out.println(percentagem + " x " + valor + " = " + resultado);
      //  System.out.println(resultado);
    }

    private static void calcularIdade(LocalDate nascimento) {
        var hoje = LocalDate.now();
        int idade = hoje.compareTo(nascimento);
        if(compararMes(hoje, nascimento) ||
        compararDia(hoje, nascimento)) {
            idade --;
        }
        System.out.println("Minha idade é: " + idade);
    }

    private static boolean compararDia(LocalDate hoje, LocalDate nascimento) {
        return (hoje.getMonthValue() == nascimento.getMonthValue() &&
                hoje.getDayOfMonth() < nascimento.getDayOfMonth());
    }

    private static boolean compararMes(LocalDate hoje, LocalDate nascimento) {
       return hoje.getMonthValue() < nascimento.getMonthValue();
    }

    private static void calcularJuros(BigDecimal v) {
        int i = 0;
        BigDecimal percentagem = BigDecimal.ZERO;
        BigDecimal acumulado = BigDecimal.ZERO;
        while(i < 36){
            percentagem = BigDecimal.valueOf(
                            Double.sum(Double.parseDouble(acumulado.toString()),
                                    Double.parseDouble(v.toString())) * 0.01
                   );
            acumulado = BigDecimal.valueOf(
                    Double.sum(
                        Double.sum(Double.parseDouble(percentagem.toString()),
                                Double.parseDouble(v.toString())),
                            Double.parseDouble(acumulado.toString())
                    )
            );
            i++;
        }
        System.out.println(acumulado);
    }
}
