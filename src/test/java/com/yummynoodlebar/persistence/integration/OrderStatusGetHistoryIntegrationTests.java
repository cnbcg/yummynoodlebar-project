package com.yummynoodlebar.persistence.integration;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yummynoodlebar.config.persistence.GemfireConfiguration;
import com.yummynoodlebar.persistence.domain.OrderStatus;
import com.yummynoodlebar.persistence.domain.fixture.PersistenceFixture;
import com.yummynoodlebar.persistence.repository.OrderStatusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { GemfireConfiguration.class })
public class OrderStatusGetHistoryIntegrationTests {

	@Autowired
	OrderStatusRepository ordersStatusRepository;

	@Before
	public void setup() {
		ordersStatusRepository.deleteAll();
	}

	@After
	public void teardown() {
		ordersStatusRepository.deleteAll();
	}

	@Test
	public void thatGetHistoryWorks() throws Exception {

		UUID orderId = UUID.randomUUID();

		UUID key0 = ordersStatusRepository.save(PersistenceFixture.orderReceived(orderId)).getOrderId();
		UUID key1 = ordersStatusRepository.save(PersistenceFixture.startedCooking(orderId)).getOrderId();
		UUID key2 = ordersStatusRepository.save(PersistenceFixture.finishedCooking(orderId)).getOrderId();

		List<OrderStatus> history = new ArrayList<OrderStatus>(ordersStatusRepository.getOrderHistory(orderId));

		assertNotNull(history);
		assertEquals(3, history.size());
		assertEquals(key0, history.get(0).getOrderId());
		assertEquals(key1, history.get(1).getOrderId());
		assertEquals(key2, history.get(2).getOrderId());
	}
}
