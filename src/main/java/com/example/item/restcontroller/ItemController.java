package com.example.item.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class ItemController {
	private static Logger log = LoggerFactory.getLogger(ItemController.class);

	@Value("${configuracion.text}")
	private String texto;
	

	/*@Autowired
	private Environment env;
	*/
	
	@Autowired
	@Qualifier("itemServiceFeign")
	//@Qualifier("serviceRestTemplate")

	@GetMapping("/obtenerConfig")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto) {
		log.info(texto);
		Map<String,String> map = new HashMap<String,String>();
		map.put("texto", texto);
		map.put("puerto",puerto);
		
		
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
	}
}
