package com.example.messagingstompwebsocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Table(name = "protocolo")
public class Protocolo {

    @Id
    private Long numero;

    private String origem;
    private String destino;

    @Column(name = "datahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;

    @Column(name = "uniqueid")
    private String uniqueId;
}
