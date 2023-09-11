package com.utn.ejerciciobidiuno;

import com.utn.ejerciciobidiuno.entidades.Domicilio;
import com.utn.ejerciciobidiuno.entidades.Persona;
import com.utn.ejerciciobidiuno.repositorios.DomicilioRepository;
import com.utn.ejerciciobidiuno.repositorios.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EjerciciobidiunoApplication {

	@Autowired
	PersonaRepository personaRepository;
	@Autowired
	DomicilioRepository domicilioRepository;

	public static void main(String[] args) {
		SpringApplication.run(EjerciciobidiunoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(PersonaRepository personaRepo, DomicilioRepository domicilioRepo) {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");


			Domicilio domicilio = Domicilio.builder()
					.calle("Suipacha")
					.numero(30)
					.build();
			Persona persona = Persona.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.edad(30)
					.build();
			// Asocio el domicilio
			persona.setDomicilio(domicilio);
			// Asocio la persona
			domicilio.setPersona(persona);
			personaRepository.save(persona);

			Domicilio domicilio2 = Domicilio.builder()
					.calle("Suipacha")
					.numero(30)
					.build();
			Persona persona2 = Persona.builder()
					.nombre("persona2")
					.apellido("Pérez")
					.edad(30)
					.build();
			persona2.setDomicilio(domicilio2);
			domicilio.setPersona(persona2);
			personaRepository.save(persona2);
			System.out.println(persona2.getApellido()==persona.getApellido());
			// Guardar el objeto Persona en la base de datos

			// Recuperar el objeto Persona desde la base de datos


				Persona personaRecuperada = personaRepository.findById(persona.getId()).orElse(null);
				if (personaRecuperada != null) {
					System.out.println("Nombre: " + personaRecuperada.getNombre());
					System.out.println("Apellido: " + personaRecuperada.getApellido());
					System.out.println("Edad: " + personaRecuperada.getEdad());
					System.out.println("Calle : " + personaRecuperada.getDomicilio().getCalle());
					System.out.println("Número :" + personaRecuperada.getDomicilio().getNumero());
				}

				System.out.println("......  Muestro la bidireccionalidad.......");
				// Recuperar el objeto Persona desde la base de datos
				Domicilio domicilioRecuperado = domicilioRepository.findById(persona.getId()).orElse(null);
				if (domicilioRecuperado!= null) {
					System.out.println("Nombre: " + domicilioRecuperado.getPersona().getNombre());
					System.out.println("Apellido: " + domicilioRecuperado.getPersona().getApellido());
					System.out.println("Edad: " + domicilioRecuperado.getPersona().getEdad());
					System.out.println("Calle : " + domicilioRecuperado.getCalle());
					System.out.println("Número :" + domicilioRecuperado.getNumero());
				}


		};

	}
}

