package br.com.moises.searchfip;

import br.com.moises.searchfip.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchfipApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SearchfipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.handleMenu();

	}
}
