package br.com.cardboardbox.logistica.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.cardboardbox.logistica.beans.Rota;

@RestController
@RequestMapping("/distance")
public class RotasController {
	
	@PostMapping("/calc")
	public Double getDistanceBetween(@RequestBody Rota rota) {
		
		return 0.0;
	}
}
