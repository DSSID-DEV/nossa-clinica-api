package com.nossaclinica_api.utils;

import com.nossaclinica_api.config.security.utils.ConstantsValues;
import com.nossaclinica_api.models.dtos.KeyValue;
import com.nossaclinica_api.models.enums.PathsDosAvatas;
import com.nossaclinica_api.reports.utils.ConstantesValues;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {

    private static final int MAX_LENGHT = 5;
    private static final String ZEROS = "00000";

    public static String criarUsernameDefault(String nome, String sobrenome, boolean addId) {
        String id = addId ? String.format("%03d", new Random().nextInt(1000)) : "";
        var username = ConstantsValues.PADRAO_USERNAME
                .replace(ConstantsValues.NOME, nome)
                        .replace(ConstantsValues.SOBRENOME, sobrenome)
                .replace(ConstantsValues.ID, id);
        return Normalizer.normalize(replace(username), Normalizer.Form.NFD);
    }

    public static String replace(String username) {
        return username.replace("ç", "c").replace("Ç", "C");
    }

    public static String criarNomeDoAvatar(String username, String permissao) {
        String path = obterPath(permissao);

        return path.concat(username
                .replace(ConstantsValues.PONTO, "_")
                .replace(ConstantsValues.DOMINIO_NOSSA_CLINICA, "")
                .toLowerCase()
                .concat(ConstantsValues.PNG));
    }

    public static String obterPath(String permissao) {
        for(var avatar: PathsDosAvatas.values()){
            if(avatar.getPermissao().equals(permissao))
                return avatar.getPath();
        }
        return "";
    }

    public static String criarSenha() {
        var random = new SecureRandom();
        return IntStream.range(0,6)
                .mapToObj(i -> String.valueOf(ConstantsValue.TODOS_OS_CARACTERES
                        .charAt(random.nextInt(ConstantsValue.TODOS_OS_CARACTERES.length()))))
                .collect(Collectors.joining());
    }

    public static KeyValue ocultarCaracteresDoTelefone(String telefone, Integer key) {
        return  KeyValue.builder()
                .key(key)
                .value(
                        telefone.split("-")[0].substring(0,8) + ConstantsValue.DUPLOS_ASTERISTICOS
                        +"-"+ ConstantsValue.DUPLOS_ASTERISTICOS + telefone.split("-")[1].substring(2)
                )
                .build();
    }
    public static String fullLike(String nome) {
        return ConstantsValue.FULL_LIKE.replace(ConstantsValue.VALUE, nome);
    }

    public static String likeLeft(String nome) {
        return ConstantsValue.LIKE_LEFT.replace(ConstantsValue.VALUE, nome);
    }

    public static String likeRight(String nome) {
        return ConstantsValue.LIKE_RIGHT.replace(ConstantsValue.VALUE, nome);
    }

    public static BigDecimal parseBigDecimal(Object[] object, int index) {
        return object != null && StringUtils.isNotBlank(object[index].toString())
                ? new BigDecimal(object[index].toString()) : BigDecimal.ZERO;
    }

    public static LocalDate parseLocalDate(Object[] object, int index) {
        if(object[index] == null) {
            return null;
        }
        return StringUtils.isNotBlank(object[index].toString()) ?
                LocalDate.parse(object[index].toString()) : null;
    }

    public static String parseString(Object[] object, int index) {
        if(object[index] == null) {
            return null;
        }
        String value = String.valueOf(object[index]);
        return StringUtils.isNotBlank(value) ?
                value : null;
    }

    public static String formatarUltimoNumeroAtendimento(Object[] object, int index) {
        String value = object[index] != null ? String.valueOf(object[index]) : null;
        if(isNull(value)) {
            return null;
        }
        return value.length() > 0 ? ZEROS.substring(0, MAX_LENGHT - value.length()).concat(value) : ZEROS;
    }

    public static String formatarNumeroAtendimento(String valor) {
        if(isNull(valor)) {
            return null;
        }
        return valor.length() > 0 ? ZEROS.substring(0, MAX_LENGHT - valor.length()).concat(valor) : ZEROS;
    }

    public static boolean parseBoolean(Object[] object, int index) {
            return (boolean) object[index];
        }

    public static int parseInt(Object[] object, int index) {
        return object[index] != null ?
                Integer.parseInt(String.valueOf(object[index])) : null;
    }

    public static Long parseLogn(Object[] object, int index) {
        return object != null && StringUtils.isNotBlank(String.valueOf(object[index])) ?
                Long.parseLong(object[index].toString()) : null;
    }
    public static double parseDouble(Object[] object, int index) {
        return object != null && StringUtils.isNotBlank(String.valueOf(object[index])) ?
                Double.parseDouble(object[index].toString()) : 0;
    }

    public static String verificarString(String entity, String dto) {
        return Verificador.isNotBlank(dto) ? dto : entity;
    }

    public static BigDecimal verificarBigDecimal(BigDecimal entity, BigDecimal request) {
        return request != null && !request.equals(entity) ? request : entity;
    }

    public static LocalDate verificarLocalDate(LocalDate entity, LocalDate dto) {
        return dto != null ? dto : entity;
    }

    public static String formatarContato(String telefone) {
        var telefoneOriginal = telefone.replaceAll(ConstantesValues.APENAS_NUMEROS, "");
        var telefoneFormatado = telefoneOriginal.replaceFirst("(\\d{2})(\\d)", "$1");
        return ConstantesValues.COD55.concat(telefoneFormatado)
               .concat(ConstantesValues.CUS);
    }

    public static boolean isNull(String str) {
       return str == null || str.isEmpty();
    }
    public static String capitalize(String str) {
        if(isNull(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase().concat(str.substring(1).toLowerCase());
    }

    public static String formatarNomeSobrenome(String nome) {
        var nomes = nome.split("@")[0].split("\\.");
        return capitalize(nomes[0]) + " " + capitalize(nomes[1]);
    }

    public static String definirArtigo(String sexo) {
        return sexo.equals("F") ? "a" : "o";
    }

    public static String difinirTratamento(String sexo, LocalDate nascidoEm) {
        int idade = nascidoEm.compareTo(LocalDate.now());
        if(idade < 25) {
            if(ConstantesValues.CASADO) {
                return sexo.equals("F") ? "Sra." : "Sr.";
            }
            return "você";
        }
        return sexo.equals("F") ? "Sra." : "Sr.";
    }

    public static String definiPronomeDosProfissionais(String sexo, boolean comArtigo) {
        return sexo.equals("F") ?
                (comArtigo ? "a Dra. " : "Dra. ") :
                (comArtigo ? "o Dr. " : "Dr. ");
    }

    public static String obterPrimeiroNome(String nome) {
        return nome.split(" ")[0].substring(0, 1).toUpperCase()
                +nome.split(" ")[0].substring(1).toLowerCase();
    }

    public static String CorrigirSeUSG(String tipoAtendimento) {
        return tipoAtendimento.contains("Ultrassom") ? tipoAtendimento.replace("- Ultrassom ", "") : tipoAtendimento;
    }

    public static String FormatarStatus(int total, String status) {
        return total > 1 ? status.toLowerCase()+"s." : status.toLowerCase()+".";
    }

    public static String trararStringVazia(String string) {
        return !isNull(string) ? string : "";
    }

    public static String converteEmMoeda(BigDecimal value) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return value != null ? numberFormat.format(value) : "";
    }

    public static int calcularIdade(LocalDate nascidoEm) {
        var hoje = LocalDate.now();
        int idade = hoje.compareTo(nascidoEm);
        if(compararDatas(hoje, nascidoEm)) {
            idade --;
        }
        return idade;
    }

    public static boolean compararDatas(LocalDate hoje, LocalDate nascidoEm) {
        return hoje.getDayOfMonth() < nascidoEm.getMonthValue()
                || (hoje.getMonthValue() == nascidoEm.getMonthValue()
                && hoje.getDayOfMonth() < nascidoEm.getDayOfMonth());
    }

    public static BigDecimal convertToBigDecimal(String value) {
        if(isNull(value)) {
            return BigDecimal.ZERO;
        }
        try {
            NumberFormat format =  NumberFormat.getInstance(new Locale("pt", "BR"));
            Number number = format.parse(value);
            return BigDecimal.valueOf(number.doubleValue());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String obterAnoCorrente() {
        return String.valueOf(LocalDate.now().getYear()).substring(2);
    }

    public static double convertToDouble(String value) {
        if(isNull(value)) {
            return 0.0;
        }
        try {
            NumberFormat format =  NumberFormat.getInstance(new Locale("pt", "BR"));
            Number number = format.parse(value);
            return number.doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
