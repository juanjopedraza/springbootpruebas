package com.example.demo.controlador;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class MiControlador {

	@GetMapping({"","/","/home","/inicio","/index"})
	public String welcome(Model model) {


		model.addAttribute("mensaje","Ramoncín");
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
	public String parametros (Model model) throws MalformedURLException  {

		InetAddress addr=null;
		String hostnameCanonical=null;
		String hostname=null;
		byte[] address=null;

		final String currentURL =
				ServletUriComponentsBuilder.
						fromCurrentContextPath().
						build().
						toUriString();
		URL requestURL = new URL(currentURL);

		try {
			addr = InetAddress.getByName(
					InetAddress.getLocalHost().
							getHostName());
			hostnameCanonical = addr.getCanonicalHostName();
			address = addr.getAddress();
			hostname = addr.getHostName();
		} catch (UnknownHostException e) {
			model.addAttribute("error","UnknownHostException");
		}
		model.addAttribute("dominio","aaa");
		model.addAttribute("titulo", "Propiedades del servidor y URL");
		model.addAttribute("addr",addr);
		model.addAttribute("hostnameCanonical",hostnameCanonical);
		model.addAttribute("hostname",hostname);
		model.addAttribute("address",address);
		model.addAttribute("url",currentURL);
		model.addAttribute("port",requestURL.getPort());
		model.addAttribute("host",requestURL.getHost());
		model.addAttribute("protocol",requestURL.getProtocol());
		model.addAttribute("path",requestURL.getPath());
		model.addAttribute("query",requestURL.getQuery());



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
	public String login(@PathVariable(name="usuario", required = false) String usuario, Model model) {
		model.addAttribute("titulo","Panel de control");
		model.addAttribute("usuario",usuario);
		return "backend/login";
	}

	/* admin */
	@GetMapping({"/admin","/intranet"})
	public String admin(@RequestParam("usuario") Optional<String>  usuario, Model model) {
		model.addAttribute("titulo","Intranet");
		model.addAttribute("usuario",usuario.orElse(""));
		return "admin/login";
	}


}
