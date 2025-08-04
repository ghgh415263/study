package com.example.study.order.command.application;

import com.example.study.common.LoginMemberContext;
import com.example.study.order.command.domain.AddressVO;
import com.example.study.order.command.domain.DeliveryAddress;
import com.example.study.order.command.domain.DeliveryAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeliveryAddressService {

    private final DeliveryAddressRepository deliveryAddressRepository;

    private final LoginMemberContext loginMemberContext;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long saveDeliveryAddress(DeliveryAddressDto dto) {
        AddressVO addressVO = new AddressVO(
                dto.zipCode(),
                dto.baseAddress(),
                dto.detailAddress()
        );

        DeliveryAddress deliveryAddress = new DeliveryAddress(loginMemberContext.getLoginId(), dto.name(), addressVO);

        return deliveryAddressRepository.save(deliveryAddress).getId();
    }

    @Transactional()
    public Long modifyDeliveryAddress(String memberId, DeliveryAddressDto dto) {
        DeliveryAddress modifyDeliveryAddress = deliveryAddressRepository.findById(deliveryAddressRepository.findByMemberId(memberId).getId());

        AddressVO addressVO = new AddressVO(
                dto.zipCode(),
                dto.baseAddress(),
                dto.detailAddress()
        );

        modifyDeliveryAddress.updateDeliveryAddress(dto.name(), addressVO);

        return deliveryAddressRepository.save(modifyDeliveryAddress).getId();
    }
}
