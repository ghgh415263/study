package com.example.study.order.ui;

import com.example.study.common.LoginMemberContext;
import com.example.study.common.lock.LockTemplate;
import com.example.study.order.command.application.DeliveryAddressDto;
import com.example.study.order.command.application.DeliveryAddressService;
import com.example.study.common.ApiSuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders/delivery-addresses")
public class DeliveryAddressController {

	private final DeliveryAddressService deliveryAddressService;

	private final LockTemplate lockTemplate;

	private final LoginMemberContext loginMemberContext;

	@PostMapping
	public ApiSuccessResponse<Void> save(@Valid @RequestBody DeliveryAddressDto dto) {

		String lockName = "deliveryAddress-lock:" + loginMemberContext.getLoginId();

		lockTemplate.executeWithLock(lockName, () -> deliveryAddressService.saveDeliveryAddress(dto));

		return ApiSuccessResponse.empty();
	}

	@PutMapping
	public ApiSuccessResponse<Void> modify(@Valid @RequestBody DeliveryAddressDto dto, HttpSession session){
		String memberId = (String) session.getAttribute("loginId");

		String lockName = "deliveryAddress-lock:" + memberId;

		lockTemplate.executeWithLock(lockName, () -> deliveryAddressService.modifyDeliveryAddress(memberId, dto));

		return ApiSuccessResponse.empty();
	}
}
