package br.com.moises.searchfip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataModel(List<DataVehicle> modelos) {

}
