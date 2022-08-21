package com.mifel.service.pokemon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    int id;
    List<PokemonAbility> abilities;
    @JsonProperty(value = "base_experience")
    int baseExperience;

    @JsonProperty(value = "held_items")
    @JsonUnwrapped
    List<PokemonItem> heldItems;

    public List<PokemonItem> getHeldItems() {
        return heldItems;
    }

    public void setHeldItems(List<PokemonItem> heldItems) {
        this.heldItems = heldItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PokemonAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<PokemonAbility> abilities) {
        this.abilities = abilities;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }
}
