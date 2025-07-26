package com.example.study.application.order;

import com.example.study.domain.AddressVO;
import com.example.study.domain.order.DeliveryAddress;
import com.example.study.domain.order.DeliveryAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeliveryAddressService {

    private final DeliveryAddressRepository deliveryAddressRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long saveDeliveryAddress(String memberId, DeliveryAddressDto dto) {
        AddressVO addressVO = new AddressVO(
                dto.zipCode(),
                dto.baseAddress(),
                dto.detailAddress()
        );

        DeliveryAddress deliveryAddress = new DeliveryAddress(memberId, dto.name(), addressVO);

        return deliveryAddressRepository.save(deliveryAddress).getId();
    }
}
