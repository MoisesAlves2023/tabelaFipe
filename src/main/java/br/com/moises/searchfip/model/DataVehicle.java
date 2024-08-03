package br.com.moises.searchfip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataVehicle(String codigo,
                          String nome) {
}
