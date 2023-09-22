package shadow.nft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import common.collection.ABox;
import common.controller.SuperController;
import shadow.nft.service.NftService;

@RequestMapping("/nft")
@RestController
public class NftController extends SuperController{

	@Value("#{nft_account['ACCESSKEY'].trim()}")
	private String accessKey;

	@Value("#{nft_account['SECRETKEY'].trim()}")
	private String secretKey;

	@Value("#{nft_account['WALLET'].trim()}")
	private String wallet;
	
	@Autowired
	private NftService nftService;

	
	@RequestMapping(value = "/", produces = "application/json; charset=utf8", method = RequestMethod.GET)
	public ResponseEntity<String> apiController()
			throws Exception {
		String result = "";
		try {
			result = "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/test", produces = "application/json; charset=utf8", method = {RequestMethod.POST, RequestMethod.GET}, headers = "Content-Type=application/json;utf-8")
	public ResponseEntity<String> postController(@RequestBody String json) throws Exception{
		String result = "";
		ABox jsonBox = new ABox();
		try {
			if(json.length() > 0) {
				jsonBox = jsonBox.jsonToABox(json);
				result = "POST : "+jsonBox.toString();				
			} else {
				result = "GET";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAuthToken/{expiresIn}", produces = "application/json; charset=utf8", method = RequestMethod.GET)
	public ResponseEntity<String> getAuthTokenController(@PathVariable("expiresIn") int expiresIn)
			throws Exception {
		String result = "";
		try {
			result = nftService.getAuthToken(new ABox().set("expiresIn", expiresIn)).aBoxToJsonObject().toString();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);

		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/uploadFile", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView moveMeadiaFileController() throws Exception {
		ModelAndView mav = new ModelAndView("web/uploadForm");
		return mav;
	}
	
	@RequestMapping(value = "/uploadMeadiaFile", method = RequestMethod.POST, headers = "Content-Type=multipart/form-data")
	public ResponseEntity<String> uploadMeadiaFileController(MultipartHttpServletRequest multipartRequest,
			@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception {

		String result = "";
		try {
			aLog.i("==작동==");
			//result = nftService.uploadMediaFile(multipartRequest).aBoxToJsonObject().toString();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);

		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
