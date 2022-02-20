package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.DBUtils;

public class OrderItemDAO {

	public static final Logger LOGGER = LogManager.getLogger();

	public OrderItem modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long fkOrderId = resultSet.getLong("fk_order_id");
		Long fkItemId = resultSet.getLong("fk_item_id");
		return new OrderItem(fkOrderId, fkItemId);
	}
	

	/**
	 * Reads all orders_items from the database
	 * 
	 * @return A list of orders_items
	 */
	public List<OrderItem> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items_orders");) {
			List<OrderItem> ordersItems = new ArrayList<>();
			while (resultSet.next()) {
				ordersItems.add(modelFromResultSet(resultSet));
			}
			return ordersItems;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public OrderItem readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM items_orders ORDER BY fk_order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates an order in the database
	 * 
	 * @param order - takes in an order object. order_id will be ignored
	 */

	public OrderItem create(OrderItem orderitem) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO items_orders(fk_order_id, fk_item_id) VALUES (?)");) {
			statement.setLong(1, orderitem.getFkItemId());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}


	public OrderItem read(Long fkOrderIdValue) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM items_orders WHERE fk_order_id = ?");) {
			statement.setLong(1, fkOrderIdValue);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates an fk_order_id in the database
	 * 
	 * @param fk_order_id - takes in an order object, the fk_order_id field will be
	 *                    used to update that fk_order_id in the database
	 * @return
	 */
	public OrderItem update(OrderItem orderitem) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE items_orders SET fk_item_id = ? WHERE fk_order_id = ?");) {
			statement.setLong(1, orderitem.getFkOrderId());
			statement.setLong(2, orderitem.getFkItemId());
			statement.executeUpdate();
			return read(orderitem.getFkOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes an fk_item_id in the order
	 * 
	 * @param fk_order_id - fk_order_id of the order
	 */
	public int delete(long fkItemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM items_orders WHERE fk_order_id = ?");) {
			statement.setLong(1, fkItemId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	/**
	 * Method called in OrderController to find order total
	 */
	public Double findOrderTotal(Long itemTotal) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT ord.fk_order_id, SUM(it.item_value) AS total_value FROM items_orders ord JOIN items it ON ord.fk_item_id=it.item_id WHERE ord.fk_order_id = ?;");) {
			statement.setLong(1, itemTotal);
			try (ResultSet totalCost = statement.executeQuery();) {
				totalCost.next();
				Double cost = totalCost.getDouble("total_value");
				return cost;
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Method called in OrderController to add item to an order
	 */
	public OrderItem addItem(Long fkOrderId, Long fkItemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO items_orders (fk_order_id, fk_item_id) VALUES (?, ?);");) {
			statement.setLong(1, fkOrderId);
			statement.setLong(2, fkItemId);
			statement.executeUpdate();
			return readLatest();
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Method called in OrderController to delete item from an order
	 */
	public int deleteItemFromOrder(long fkOrderId, long fkItemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM items_orders WHERE fk_order_id = ? AND fk_item_id = ? ");) {
			statement.setLong(1, fkOrderId);
			statement.setLong(2, fkItemId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}
