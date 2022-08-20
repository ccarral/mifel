package com.mifel.pokemon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureJson
@RunWith(SpringRunner.class)
public class TestPokemonApi {
    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testPokeApi() {
        Pokemon ditto = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/ditto", Pokemon.class);
        assertEquals(132, ditto.getId());
        assertEquals(2, ditto.abilities.size());
        assertEquals("limber", ditto.getAbilities().get(0).getAbility().name);
        assertEquals(false, ditto.getAbilities().get(0).isHidden());
        assertEquals(true, ditto.getAbilities().get(1).isHidden());
        assertEquals(101, ditto.getBaseExperience());
        assertEquals(2, ditto.getHeldItems().size());
        assertEquals("metal-powder", ditto.getHeldItems().get(0).getItem().getName());
        assertEquals("ruby", ditto.getHeldItems().get(0).getVersionDetails().get(0).getVersion().getName());
    }
}
