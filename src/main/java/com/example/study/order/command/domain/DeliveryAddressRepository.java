package com.example.study.order.command.domain;

public interface DeliveryAddressRepository {

    DeliveryAddress save(DeliveryAddress deliveryAddress);

    DeliveryAddress findById(Long id);

    DeliveryAddress modify(Long id, DeliveryAddress deliveryAddress);

    DeliveryAddress findByMemberId(String memberId);
}