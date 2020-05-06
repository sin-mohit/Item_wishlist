package com.techms.itemwishlist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techms.itemwishlist.dao.CustomerWishListRepository;
import com.techms.itemwishlist.model.CustomerWishList;

@Service
public class CustomerWishListService {

	@Autowired
	private CustomerWishListRepository customerWishListRepository;
	
	public List<CustomerWishList> getAllWishLists() {
		List<CustomerWishList> customeWishLists = new ArrayList<>();
		customerWishListRepository.findAll().forEach(customeWishLists::add);
		return customeWishLists;
	}
	
	public List<CustomerWishList> getWishList(String customerId, Pageable pageable) {
		return customerWishListRepository.findByCustomerId(customerId, pageable);
	}
	
	public void addWishList(CustomerWishList customerWishList) {
		customerWishListRepository.save(customerWishList);
	}

	@Transactional
	public void deleteWishList(String itemName, String customerId) {
		customerWishListRepository.deleteByItemNameAndCustomerId(itemName, customerId);
	}
}
