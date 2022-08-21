package com.mifel.service.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PokemonItem {
    Item item;
    @JsonProperty(value = "version_details")
    List<VersionDetails> versionDetails;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<VersionDetails> getVersionDetails() {
        return versionDetails;
    }

    public void setVersionDetails(List<VersionDetails> versionDetails) {
        this.versionDetails = versionDetails;
    }
}
