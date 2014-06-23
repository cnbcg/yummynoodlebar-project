package com.yummynoodlebar.core.persistence.integration;

import static com.yummynoodlebar.core.persistence.domain.fixture.PersistenceFixture.standardItem;
import static junit.framework.TestCase.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yummynoodlebar.config.persistence.MongoConfiguration;
import com.yummynoodlebar.persistence.repository.MenuItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfiguration.class })
public class MenuItemRepositoryIntegrationTests {

	@Autowired
	MenuItemRepository menuItemRepository;

	@Autowired
	MongoOperations mongo;

	@Before
	public void setup() throws Exception {
		mongo.dropCollection("menu");
	}

	@After
	public void teardown() {
		mongo.dropCollection("menu");
	}

	@Test
	public void thatItemIsInsertedIntoRepoWorks() throws Exception {

		assertEquals(0, mongo.getCollection("menu").count());

		menuItemRepository.save(standardItem());

		assertEquals(1, mongo.getCollection("menu").count());
	}

}
