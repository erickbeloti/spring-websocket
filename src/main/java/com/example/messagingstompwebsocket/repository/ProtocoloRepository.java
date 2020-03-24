package com.example.messagingstompwebsocket.repository;

import com.example.messagingstompwebsocket.model.Protocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProtocoloRepository extends JpaRepository<Protocolo, Long> {

    List<Protocolo> findTop5ByDestinoOrderByDataHoraDesc(String destino);
}
