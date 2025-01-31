package com.nossaclinica_api.services;

import com.nossaclinica_api.models.dtos.SMSDestino;
import com.nossaclinica_api.models.dtos.responses.autenticacao.RequestValidarTelefone;
import com.nossaclinica_api.repositories.RepositoryUser;
import com.nossaclinica_api.utils.ConstantsValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsService {

    private final RepositoryUser userRepository;
    public String sendSMS(RequestValidarTelefone validar) {

        var user = userRepository.findById(validar.getIdUser())
                .orElseThrow(() -> {throw new RuntimeException("Não Encontrado");});

        if(!user.getTelefone().equals(validar.getTelefone())) {
            return "Não Confere";
        }
        SMSDestino sms = new SMSDestino();
        sms.setDestino("+5581992122261");
        sms.setMensagem("64eras8%@");
        String msg = formatarMSG(sms.getMensagem());
        //TODO: Implementar envio via whatsapp
        /***
         * Escrever código de envio de whatsapp aqui!
         */
        return msg;
    }
    private String formatarMSG(String senha) {
        return String.format(ConstantsValue.MSG_GENERICA, senha);
    }
}
