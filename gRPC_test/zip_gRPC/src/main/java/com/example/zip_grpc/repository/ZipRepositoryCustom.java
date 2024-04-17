package com.example.zip_grpc.repository;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ZipRepositoryCustom {

    private final JPAQueryFactory queryFactory;

}
