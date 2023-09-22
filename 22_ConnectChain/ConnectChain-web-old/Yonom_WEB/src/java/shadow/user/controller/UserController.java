package shadow.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import common.collection.ABox;
import common.controller.SuperController;
import shadow.user.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController extends SuperController{

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/", produces = "application/json; charset=utf8", method = RequestMethod.GET)
	public ResponseEntity<String> userController()throws Exception {
		String result = "";
		try {
			result = "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/add-user", produces = "application/json; charset=utf8", method = RequestMethod.GET)
	public ResponseEntity<String> userAddController()throws Exception {
		String result = "";
		try {
			result = "fs";
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
