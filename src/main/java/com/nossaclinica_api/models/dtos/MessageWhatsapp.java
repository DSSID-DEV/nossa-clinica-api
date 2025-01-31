package com.nossaclinica_api.models.dtos;

import com.nossaclinica_api.reports.utils.ConstantesValues;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageWhatsapp {

	private String chatId;
	private String contentType;
	private Object content;

	public String parseJson() {
		return new StringBuilder()
				.append(ConstantesValues.ABRE_CHAVE).append("\n")
				.append(linha(ConstantesValues.CHAT_ID, this.chatId))
				.append(ConstantesValues.VIRGULA).append("\n")
				.append(linha(ConstantesValues.CONTENT_TYPE, this.contentType))
				.append(ConstantesValues.VIRGULA).append("\n")
				.append(linha(ConstantesValues.CONTENT,
						this.content.toString())).append("\n")
				.append(ConstantesValues.FECHA_CHAVE)
				.append("\n").toString();
	}

	private String linha(String field, String value) {
		return "	\"" + field + "\": " +" \"" + value +"\"";
	}

}
