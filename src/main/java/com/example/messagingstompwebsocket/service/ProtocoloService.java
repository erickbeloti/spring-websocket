package com.example.messagingstompwebsocket.service;

import com.example.messagingstompwebsocket.model.Protocolo;
import com.example.messagingstompwebsocket.repository.ProtocoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProtocoloService {

    @Autowired
    private ProtocoloRepository protocoloRepository;

    public List<Protocolo> findTop10ByDestinoOrderByDataHoraDesc(String destino){
        return protocoloRepository.findTop10ByDestinoOrderByDataHoraDesc(destino);
    }

}
