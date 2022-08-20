package com.mifel.pokemon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
//@JsonTest
@AutoConfigureJson
@RunWith(SpringRunner.class)
public class TestPokemonApi {
    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testPokeApi() {
        Pokemon ditto = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/ditto", Pokemon.class);
        assertEquals(2, ditto.abilities.size());
        assertEquals("limber", ditto.getAbilities().get(0).getAbility().name);
        assertEquals(101, ditto.baseExperience);
    }
}
