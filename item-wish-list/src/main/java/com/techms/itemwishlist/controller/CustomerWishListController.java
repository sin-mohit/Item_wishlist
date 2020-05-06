package com.techms.itemwishlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techms.itemwishlist.model.CustomerWishList;
import com.techms.itemwishlist.service.CustomerWishListService;

@RestController
public class CustomerWishListController {

	@Autowired
	private CustomerWishListService customerWishListService;

//	@RequestMapping("/customerWishLists")
//	public List<CustomerWishList> getAllWishLists() {	
//		return customerWishListService.getAllWishLists();
//	}

	@RequestMapping("/customerWishLists")
	public List<CustomerWishList> getWishList(@RequestParam(value = "customerId", required = true) String customerId,
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "size", defaultValue = "5", required = false) int size) {
		Pageable pageable = PageRequest.of(page, size);
		return customerWishListService.getWishList(customerId, pageable);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/customerWishLists")
	public void addWishList(@RequestBody CustomerWishList customerWishList) {
		customerWishListService.addWishList(customerWishList);
	}

	// This method is to delete the wish list
	@RequestMapping(method = RequestMethod.DELETE, value = "/customerWishLists")
	public void deleteWishList(@RequestParam(value = "itemName") String itemName,
			@RequestParam(value = "customerId", required = true) String customerId) {
		customerWishListService.deleteWishList(itemName, customerId);
	}

}
