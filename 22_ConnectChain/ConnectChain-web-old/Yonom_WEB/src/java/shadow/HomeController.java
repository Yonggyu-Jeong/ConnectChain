package shadow;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import common.controller.SuperController;

@RestController
public class HomeController extends SuperController {
	
	@RequestMapping(value = {  "", "/",  "index" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> myInfo() throws Exception {
		String result = "";
		try {
			result = "test";
		} catch (Exception e) {
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}	
}