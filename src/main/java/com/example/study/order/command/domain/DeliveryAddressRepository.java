package com.example.study.order.command.domain;

import java.util.Optional;

public interface DeliveryAddressRepository {

    DeliveryAddress save(DeliveryAddress deliveryAddress);

    Optional<DeliveryAddress> findById(Long id);
}