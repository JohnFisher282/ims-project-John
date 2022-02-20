package com.qa.ims.controller;


import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private OrderItemDAO orderItemDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, OrderItemDAO orderItemDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.orderItemDAO = orderItemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates an order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter the customer_id to place the order under");
		Long fkId = utils.getLong();
		Order order = orderDAO.create(new Order(fkId));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the order_id of the order you would like to update");
		Long orderId = utils.getLong();
		LOGGER.info("Please enter customer_id");
		Long fkId = utils.getLong();
		Order order = orderDAO.update(new Order(orderId, fkId));
		LOGGER.info("Order Updated");
		return order;
	}

	/**
	 * Deletes an existing order by the order_id of the order
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the order_id of the order you would like to delete");
		Long orderId = utils.getLong();
		return orderDAO.delete(orderId);
	}
	
	/**
	 * Calculates the order total of a given order_id
	 */
	public Double findOrderTotal() {
		LOGGER.info("Please enter the order_id of the order you would like to find the total cost of");
		Long fkOrderId = utils.getLong();
		Double cost = orderItemDAO.findOrderTotal(fkOrderId);
		LOGGER.info("The total value of the order_id is " + cost);
		return cost;
	
	}
	
	/**
	 * Adds an item to an existing order using the order_id and item_id
	 */
	public OrderItem addItem() {
		LOGGER.info("Please enter the order_id of the order you would like to add an item to");
		Long fkOrderId = utils.getLong();
		LOGGER.info("Please enter the item_id you would like to add");
		Long fkItemId = utils.getLong();
		OrderItem orderItemAdded = orderItemDAO.addItem(fkOrderId, fkItemId);
		LOGGER.info("Order Updated");
		return orderItemAdded;
	}
	
	/**
	 * Deletes items with a particular item_id from an existing order
	 */
	public int deleteItemFromOrder() {
		LOGGER.info("Please enter the order_id of the order you would like to delete an item from");
		Long fkOrderId = utils.getLong();
		LOGGER.info("Please enter the item_id you would like to delete");
		Long fkItemId = utils.getLong();
		return orderItemDAO.deleteItemFromOrder(fkOrderId, fkItemId);
	}

}
