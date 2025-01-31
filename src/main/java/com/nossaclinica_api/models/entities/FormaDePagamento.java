package com.nossaclinica_api.models.entities;

import lombok.*;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "formas_de_pagamento")
public class FormaDePagamento {

    @Id
    @Column(name = "id_forma_de_pagamento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_forma_de_pagamento_id")
    @SequenceGenerator(name = "seq_forma_de_pagamento_id",
            sequenceName = "seq_forma_de_pagamento_id", allocationSize = 1)
    private Long idFormaDePagamento;

    @Column(name = "pago_com")
    private String pagaCom;

    @Column(name = "hash_autorizacao_cancelamento")
    private String hashAutorizacao;

    private String status;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "id_movimentacao", referencedColumnName = "id_movimentacao")
    private Movimentacao movimentacao;


    public String toString() {
        Locale localeBr = new Locale("pt", "BR");
        var numberFormat = NumberFormat.getCurrencyInstance(localeBr);
        var moedaBrasil = numberFormat.format(valor);
        return "Pagamento com "
                + this.pagaCom
                + " da importância de "
                + moedaBrasil+ " -> "
                + this.status.toLowerCase()+".";
    }
}
