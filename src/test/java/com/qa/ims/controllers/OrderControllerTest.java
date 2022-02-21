package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;
	
	@Mock
	private OrderItemDAO dao2;

	@InjectMocks
	private OrderController controller;
	
	@Test
	public void testCreate() {
		final Long FKCID = 3L;
		final Order created = new Order(FKCID);

		Mockito.when(utils.getLong()).thenReturn(FKCID);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

//	@Test
//	public void testUpdate() {
//		Order updated = new Order(1L, 4L);
//
//		Mockito.when(this.utils.getLong()).thenReturn(updated.getOrderId());
//		Mockito.when(this.utils.getLong()).thenReturn(updated.getFkId());
//		Mockito.when(dao.update(updated)).thenReturn(updated);
//
//		assertEquals(updated, controller.update());
//
//		Mockito.verify(this.utils, Mockito.times(2)).getLong();
//		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
//	}

	@Test
	public void testDelete() {
		final long OI_ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(OI_ID);
		Mockito.when(dao.delete(OI_ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(OI_ID);
	}
}
