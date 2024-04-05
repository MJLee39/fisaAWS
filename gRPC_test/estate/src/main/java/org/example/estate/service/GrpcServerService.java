package org.example.estate.service;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.estate.dto.EstateDTO;
import org.example.estate.entity.Estate;
import org.example.estate.exception.NotExistException;
import org.example.estate.model.EstateRepository;
import org.example.estate.proto.EstateIdRequest;
import org.example.estate.proto.EstateInfoResponse;
import org.example.estate.proto.EstateProtoServiceGrpc;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
@GrpcService
public class GrpcServerService extends EstateProtoServiceGrpc.EstateProtoServiceImplBase {

    @Autowired
    private EstateRepository estateRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void findEstateInfo(EstateIdRequest request, StreamObserver<EstateInfoResponse> responseObserver){
        String estateId = request.getEstateId();

        //제공된 id로 estate 정보를 찾는 로직
        Optional<Estate> estateEntity = estateRepository.findById(estateId);

        EstateDTO estateDTO = mapper.map(estateEntity.get(), EstateDTO.class);
        log.info("get estate dto {}", estateDTO.toString());
        log.info("get estate entity {}", estateEntity.toString());

        EstateInfoResponse response = EstateInfoResponse.newBuilder()
                .setLocation(estateDTO.getLocation())
                .setEstateType(estateDTO.getEstateType())
                .setDirection(estateDTO.getDirection())
                .setTotalFloor(estateDTO.getTotalFloor())
                .setBuildingFloor(estateDTO.getBuildingFloor())
                .setRoom(estateDTO.getRoom())
                .setToilet(estateDTO.getToilet())
                .setHashtag(estateDTO.getHashtag())
                .setM2(estateDTO.getM2())
                .setEstateId(estateId) // 클라이언트로부터 받은 부동산 ID를 그대로 응답에 포함
                .build();

        //클라이언트로 응답을 전송
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
