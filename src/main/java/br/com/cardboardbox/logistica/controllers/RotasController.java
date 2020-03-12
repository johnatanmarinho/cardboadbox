package br.com.cardboardbox.logistica.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.cardboardbox.logistica.beans.Rota;

@RestController
@RequestMapping("/distance")
public class RotasController {
	
	@GetMapping("/")
	public double getDistanceBetween( Rota rota) {
		
		return 0.0;
	}
}
