package be.ugent.iii.controller;

import be.ugent.iii.zoo.entity.Zoo;
import be.ugent.iii.zoo.service.IZooService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Wouter
 */

@Controller
@RequestMapping("zoo")
public class ZooController {
    @Autowired
    private IZooService zooService;
    
    @GetMapping("zoo/{id}")
	public ResponseEntity<Zoo> getZooById(@PathVariable("id") Integer id) {
		Zoo zoo = zooService.getZooById(id);
		return new ResponseEntity<Zoo>(zoo, HttpStatus.OK);
	}
	@GetMapping("zoos")
	public ResponseEntity<List<Zoo>> getAllZoos() {
		List<Zoo> list = zooService.getAllZoos();
		return new ResponseEntity<List<Zoo>>(list, HttpStatus.OK);
	}
	@PostMapping("zoo")
	public ResponseEntity<Void> addZoo(@RequestBody Zoo zoo, UriComponentsBuilder builder) {
                boolean flag = zooService.addZoo(zoo);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/zoo/{id}").buildAndExpand(zoo.getId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("zoo")
	public ResponseEntity<Zoo> updateZoo(@RequestBody Zoo zoo) {
		zooService.updateZoo(zoo);
		return new ResponseEntity<Zoo>(zoo, HttpStatus.OK);
	}
	@DeleteMapping("zoo/{id}")
	public ResponseEntity<Void> deleteZoo(@PathVariable("id") Integer id) {
		zooService.deleteZoo(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
    
}
