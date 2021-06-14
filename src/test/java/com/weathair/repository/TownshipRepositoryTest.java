package com.weathair.repository;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.weathair.entities.Township;
import com.weathair.repositories.TownshipRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
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
