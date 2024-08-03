package br.com.moises.searchfip.main;

import br.com.moises.searchfip.model.DataModel;
import br.com.moises.searchfip.model.DataVehicle;
import br.com.moises.searchfip.model.Vehicle;
import br.com.moises.searchfip.service.ConnectionApi;
import br.com.moises.searchfip.service.ConvertData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner leitura = new Scanner(System.in);
    private ConnectionApi connectionApi = new ConnectionApi();
    private ConvertData convertData = new ConvertData();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void handleMenu() {
        var menu = """
            *** OPÇÕES ***
            Carro
            Moto
            Caminhão
            
            Digite uma das opções para consulta:
            
            """;

        System.out.println(menu);
        var opcao = leitura.nextLine();
        String url;

        if (opcao.toLowerCase().contains("carr")){
            url = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            url = URL_BASE + "motos/marcas";
        } else {
            url = URL_BASE + "caminhoes/marcas";
        }

        var json = connectionApi.getApi(url);
        var mark = convertData.getlist(json, DataVehicle.class);

        mark.stream()
                .sorted(Comparator.comparing(DataVehicle::codigo))
                .forEach(System.out::println);

        System.out.println("Digite o codigo da marca que deseja consultar: ");
        var model =  leitura.nextLine();
        url = url + "/" + model+ "/modelos";
        System.out.println(url);
        json = connectionApi.getApi(url);
        var modelList = convertData.convertData(json, DataModel.class);
        System.out.println("Modelos: ");

        modelList.modelos().
                forEach(System.out::println);


        System.out.println("Digite um trecho ou o nome do carro que deseja consultar: ");
        var nameCar = leitura.nextLine();
        List<DataVehicle> vehiclesFilter = modelList.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nameCar.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("Modelos Filtrados");
        vehiclesFilter.forEach(System.out::println);

        System.out.println("Digite o codigo do modelo que deseja ver os valores: ");
        var codCar = leitura.nextLine();

        List<Vehicle> vehicles = new ArrayList<>();

        url = url + "/" + codCar + "/anos";

        json = connectionApi.getApi(url);
        List<DataVehicle> years = convertData.getlist(json, DataVehicle.class);

        for (int i = 0; i < years.size() ; i++) {
            var urlYears = url + "/" + years.get(i).codigo();
            json = connectionApi.getApi(urlYears);
            Vehicle vehicle = convertData.convertData(json, Vehicle.class);
            vehicles.add(vehicle);
        }
        System.out.println("Todos veiculos encontrados: ");
        vehicles.forEach(System.out::println);


    }


}