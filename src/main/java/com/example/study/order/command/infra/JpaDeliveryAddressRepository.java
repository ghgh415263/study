package com.example.study.order.command.infra;

import com.example.study.order.command.domain.DeliveryAddress;
import com.example.study.order.command.domain.DeliveryAddressRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JpaDeliveryAddressRepository implements DeliveryAddressRepository {

    private final EntityManager entityManager;

    @Override
    public DeliveryAddress save(DeliveryAddress deliveryAddress) {
        if (deliveryAddress.getId() == null) {
            entityManager.persist(deliveryAddress);
            return deliveryAddress;
        } else {
            return entityManager.merge(deliveryAddress);
        }
    }

    @Override
    public Optional<DeliveryAddress> findById(Long id) {
        return Optional.ofNullable(entityManager.find(DeliveryAddress.class, id));
    }
}