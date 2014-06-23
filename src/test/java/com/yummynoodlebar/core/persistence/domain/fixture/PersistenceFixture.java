package com.yummynoodlebar.core.persistence.domain.fixture;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import com.yummynoodlebar.persistence.domain.Ingredient;
import com.yummynoodlebar.persistence.domain.MenuItem;
import com.yummynoodlebar.persistence.domain.Order;
import com.yummynoodlebar.persistence.domain.OrderStatus;

public class PersistenceFixture {

	public static MenuItem standardItem() {
		MenuItem item = new MenuItem();
		item.setKey(UUID.randomUUID());
		item.setDescription("Peanutty Noodles, perfect for every occasion.");
		item.setName("Yummy Noodles");
		item.setCost(new BigDecimal("12.99"));
		item.setIngredients(new HashSet<Ingredient>(Arrays.asList(new Ingredient("Noodles", "Crisp, lovely noodles"), new Ingredient("Egg",
				"Used in the noodles"), new Ingredient("Peanuts", "A Nut"))));

		return item;
	}

	public static MenuItem eggFriedRice() {
		MenuItem item = new MenuItem();
		item.setKey(UUID.randomUUID());
		item.setDescription("Rice, Egg Fried");
		item.setName("Yummy Rice");
		item.setCost(new BigDecimal("12.99"));
		item.setIngredients(new HashSet<Ingredient>(Arrays.asList(new Ingredient("Rice", "Straight White Rice"),
				new Ingredient("Egg", "Chicken Eggs"))));

		return item;
	}

	public static Order standardOrder() {

		Order order = new Order();
		order.setDateTimeOfSubmission(new Date());
		order.setKey(UUID.randomUUID());

		Map<String, Integer> items = new HashMap<String, Integer>();

		items.put("yummy1", 15);
		items.put("yummy3", 12);
		items.put("yummy5", 7);

		order.setOrderItems(items);

		return order;
	}

	public static Order yummy16Order() {

		Order order = new Order();
		order.setDateTimeOfSubmission(new Date());
		order.setKey(UUID.randomUUID());

		Map<String, Integer> items = new HashMap<String, Integer>();

		items.put("yummy16", 22);

		order.setOrderItems(items);

		return order;
	}

	public static OrderStatus orderReceived(UUID orderId) {
		return new OrderStatus(orderId, UUID.randomUUID(), new Date(), "Order Received");
	}

	public static OrderStatus startedCooking(UUID orderId) {
		return new OrderStatus(orderId, UUID.randomUUID(), new Date(), "Started Cooking");
	}

	public static OrderStatus finishedCooking(UUID orderId) {
		return new OrderStatus(orderId, UUID.randomUUID(), new Date(), "Finished Cooking");
	}
}
