package com.example.demo.controlador;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.example.demo.Dominio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;

@Controller
public class MiControlador {
	String dominio;
	Dominio dom;

	public void buscaURL() throws MalformedURLException {
			this.dom  = new Dominio();
	}

	@GetMapping({"","/","/home","/inicio","/index"})
	public String welcome(Model model) throws MalformedURLException {
		this.buscaURL();
		model.addAttribute("mensaje","Ramoncín");
		model.addAttribute("dominio",dom.getDominio());
		return "index";
	}

	@GetMapping("/hora")
	public String hora(Model model) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		model.addAttribute("fechahora",dtf.format(LocalDateTime.now()));
		DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH");
		int ihora = Integer.parseInt(hora.format(LocalDateTime.now()));
		String background="claro";
		if (ihora >= 20 || ihora <= 7 ) {
			background="oscuro";
		}
		model.addAttribute("background",background);
		return "hora";
	}

	@GetMapping("/parametros")
	public String parametros (Model model) throws MalformedURLException {
		this.buscaURL();
		model.addAttribute("titulo", "Propiedades del servidor y URL");
		model.addAttribute("addr",dom.getAddr());
		model.addAttribute("hostnameCanonical",dom.getCanonnical());
		model.addAttribute("hostname",dom.getHostname());
		model.addAttribute("address",dom.getAddr());
		/* importante */
		model.addAttribute("url",dom.getUrl());
		model.addAttribute("port",dom.getPuerto());
		model.addAttribute("host",dom.getHostname());
		model.addAttribute("protocol",dom.getProtocolo());
		model.addAttribute("path",dom.getPath());
		model.addAttribute("query",dom.getQuery());
		model.addAttribute("dominio",dom.getDominio());
		model.addAttribute("tld",dom.getTld());
		return("pruebas/parametros");

	}

	/**
	 * 127.0.0.1 dominio1.es.tipo1
	 * 127.0.0.1 dominio2.es.tipo1
	 * 127.0.0.1 dominio1.es.tipo2
	 * 127.0.0.1 dominio2.es.tipo2
	 */

	/* backend */


	/*  url amigable */
	@GetMapping({"/login/{usuario}","/login","/usuario/{usuario}","/usuario"})
	public String login(@PathVariable(name="usuario", required = false) String usuario,
						Model model) throws MalformedURLException {
		this.buscaURL();
		model.addAttribute("titulo","Panel de control");
		model.addAttribute("usuario",usuario);
		return "backend/login";
	}

	/* admin */
	@GetMapping({"/admin","/intranet"})
	public String admin(@RequestParam("usuario") Optional<String>  usuario,
						Model model) throws MalformedURLException {
		this.buscaURL();
		model.addAttribute("titulo","Intranet");
		model.addAttribute("usuario",usuario.orElse(""));
		return "admin/login";
	}


}
