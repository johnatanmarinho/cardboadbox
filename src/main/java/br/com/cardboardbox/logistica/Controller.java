package br.com.cardboardbox.logistica;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/teste")
public class Controller {
	
	@GetMapping("/1")
	public List<Object> teste() {
		return new ArrayList<Object>();
	}

}
