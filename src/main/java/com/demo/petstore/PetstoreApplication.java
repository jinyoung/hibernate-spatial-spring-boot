package com.demo.petstore;

import com.demo.petstore.domain.Cat;
import com.demo.petstore.domain.CatRepository;
import com.demo.petstore.domain.Dog;
import com.demo.petstore.domain.Pet;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.util.GeometricShapeFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PetstoreApplication {

	static ApplicationContext applicationContext;
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(PetstoreApplication.class, args);

		Pet dog = new Dog();   // new Cat();
		dog.setName("뽀삐");

		System.out.println(dog.speak());

		System.out.println("pet is eating");
		dog.eat();

		System.out.println(dog);
		

		System.out.println("pet is sleeping");
		dog.sleep();

		System.out.println(dog);

		dog.save();

	}

	public Geometry createCircle(double x, double y, double radius) {
		GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
		shapeFactory.setNumPoints(32);
		shapeFactory.setCentre(new Coordinate(x, y));
		shapeFactory.setSize(radius * 2);
		return shapeFactory.createCircle();
	}


	@Autowired CatRepository catRepository;

	@RequestMapping(method=RequestMethod.GET, value="/cats/search/findWithin")
	public List<Cat> findCatWithin(@RequestParam("x") int x, @RequestParam("x") int y){

		return catRepository.findCatWithin(createCircle(x, y, 5));
	}


}
