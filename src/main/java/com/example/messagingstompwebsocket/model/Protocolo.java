package com.example.messagingstompwebsocket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHora;

    @Column(name = "uniqueid")
    private String uniqueId;
}
