package br.com.moises.searchfip.service;

import java.util.List;

public interface IConvertData {
    <T> T convertData(String json, Class<T> tClass);

    <T> List<T> getlist(String json, Class<T> tClass);
}
