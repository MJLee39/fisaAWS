package com.example.zip1.service;

import com.example.estate.EstateIdRequest;
import com.example.estate.EstateInfoResponse;
import com.example.estate.EstateProtoServiceGrpc;
import com.example.zip1.dto.EstateDTO;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientServer {

    @GrpcClient("auth-server")
    private EstateProtoServiceGrpc.EstateProtoServiceBlockingStub estateStub;

    public EstateDTO findEstateInfo(final String id){
        try {
            EstateInfoResponse response = this.estateStub.findEstateInfo(EstateIdRequest.newBuilder().setEstateId(id).build());
            EstateDTO estateDTO = new EstateDTO();
            estateDTO.setBuildingFloor(response.getBuildingFloor());
            estateDTO.setEstateType(response.getEstateType());
            estateDTO.setDirection(response.getDirection());
            estateDTO.setHashtag(response.getHashtag());
            estateDTO.setLocation(response.getLocation());
            estateDTO.setM2(response.getM2());
            estateDTO.setRoom(response.getRoom());
            estateDTO.setToilet(response.getToilet());
            estateDTO.setTotalFloor(response.getTotalFloor());
            estateDTO.setSuccess(true);
            return estateDTO;
        }catch (StatusRuntimeException e){
            EstateDTO estateDTO = new EstateDTO();
            estateDTO.setSuccess(false);
            return estateDTO;
        }
    }
}
