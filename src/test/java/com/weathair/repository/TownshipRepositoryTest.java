package com.weathair.repository;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weathair.entities.Township;
import com.weathair.repositories.TownshipRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TownshipRepositoryTest {

	@Autowired
	private TownshipRepository townshipRepository;

	
	@Test
	public void testFindByNameOrderByPopulationDesc() {
		List<Township> township = townshipRepository.findByNameOrderByPopulationDesc("Abancourt");
		System.out.println(township.get(0).getPopulation());
		assertEquals(2, township.size());
	}
}
