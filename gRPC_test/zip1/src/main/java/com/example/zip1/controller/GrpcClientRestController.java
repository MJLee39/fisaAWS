package com.example.zip1.controller;

import com.example.zip1.dto.EstateDTO;
import com.example.zip1.service.GrpcClientServer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GrpcClientRestController {

    private final GrpcClientServer grpcClientServer;

    @GetMapping("/auth-server")
    public EstateDTO printEstateDTO(){
        return grpcClientServer.findEstateInfo("d9c5cb62913a4a31fa214ea8c44a5ade");
    }
}
