package shadow.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

	@RequestMapping(value = "/add-user", method = RequestMethod.POST, headers = "Content-Type=multipart/form-data")
	public ResponseEntity<String> addUserController(MultipartHttpServletRequest multipartRequest,
			@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception {
		String result = null;
		try {
			result = userService.addUser(multipartRequest).aBoxToJsonObject().toString();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/modify/image", method = RequestMethod.POST, headers = "Content-Type=multipart/form-data")
	public ResponseEntity<String> modifyUserImageController(MultipartHttpServletRequest multipartRequest,
			@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception {
		String result = null;
		try {
			result = userService.modifyProfileImage(multipartRequest).aBoxToJsonObject().toString();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST, headers = "Content-Type=application/json;utf-8")
	public ResponseEntity<String> modifyUserController(@RequestBody String json) throws Exception {
		String result = "";
		ABox jsonBox = new ABox();
		jsonBox = jsonBox.jsonToABox(json);
		try {
			//result = userService.modifyprofile(jsonBox).aBoxToJsonObject().toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Content-Type=application/json;utf-8")
	public ResponseEntity<String> loginController(@RequestBody String json) throws Exception {
		String result = "";
		ABox jsonBox = new ABox();
		jsonBox = jsonBox.jsonToABox(json);
		try {
			result = userService.loginUser(jsonBox).aBoxToJsonObject().toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST, headers = "Content-Type=application/json;utf-8")
	public ResponseEntity<String> getUserController(@RequestBody String json) throws Exception {
		String result = "";
		ABox jsonBox = new ABox();
		jsonBox = jsonBox.jsonToABox(json);
		try {
			//result = userService.selectprofile(jsonBox).aBoxToJsonObject().toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/wish-list", method = RequestMethod.POST, headers = "Content-Type=application/json;utf-8")
	public ResponseEntity<String> getWishListController(@RequestBody String json) throws Exception {
		String result = "";
		ABox jsonBox = new ABox();
		jsonBox = jsonBox.jsonToABox(json);
		try {
			result = userService.wishgoods(jsonBox).aBoxToJsonObject().toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}


}
