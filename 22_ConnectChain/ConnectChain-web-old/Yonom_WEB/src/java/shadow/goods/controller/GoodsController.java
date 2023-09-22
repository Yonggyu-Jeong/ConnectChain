package shadow.goods.controller;

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
import shadow.goods.service.GoodsService;
import shadow.nft.service.NftService;

@RequestMapping("/goods")
@RestController
public class GoodsController extends SuperController{

	@Autowired
	GoodsService goodsService;
	
	@RequestMapping(value = "/", produces = "application/json; charset=utf8", method = RequestMethod.GET)
	public ResponseEntity<String> apiController()throws Exception {
		String result = "";
		try {
			result = goodsService.selectGoods(new ABox()).aBoxToJsonObject().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/goods-list", method = RequestMethod.POST, headers = "Content-Type=application/json;utf-8")
	public ResponseEntity<String> getGoodsListController(@RequestBody String json) throws Exception {
		String result = "";
		ABox jsonBox = new ABox();
		jsonBox = jsonBox.jsonToABox(json);
		try {
			result = goodsService.selectGoods(jsonBox).aBoxToJsonObject().toString();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sold-good-list", method = RequestMethod.POST, headers = "Content-Type=application/json;utf-8")
	public ResponseEntity<String> getSoldGoodsListController(@RequestBody String json) throws Exception {
		String result = "";
		ABox jsonBox = new ABox();
		jsonBox = jsonBox.jsonToABox(json);
		try {
			result = goodsService.selectSalesRecord(jsonBox).aBoxToJsonObject().toString();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/record-list", method = RequestMethod.POST, headers = "Content-Type=application/json;utf-8")
	public ResponseEntity<String> getSaleRecordListController(@RequestBody String json) throws Exception {
		String result = "";
		ABox jsonBox = new ABox();
		jsonBox = jsonBox.jsonToABox(json);
		try {
			result = goodsService.selectGoods(jsonBox).aBoxToJsonObject().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/register-goods", method = RequestMethod.POST, headers = "Content-Type=multipart/form-data")
	public ResponseEntity<String> registerGoodsController(MultipartHttpServletRequest multipartRequest,
			@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception {
		String result = null;
		try {
			result = goodsService.registerGoods(multipartRequest).aBoxToJsonObject().toString();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/request-purchase", method = RequestMethod.POST, headers = "Content-Type=application/json;utf-8")
	public ResponseEntity<String> requestPurchaseController(@RequestBody String json) throws Exception {
		String result = "";
		ABox jsonBox = new ABox();
		jsonBox = jsonBox.jsonToABox(json);
		try {
			result = goodsService.requestPurchase(jsonBox).aBoxToJsonObject().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/choose-buyer", method = RequestMethod.POST, headers = "Content-Type=application/json;utf-8")
	public ResponseEntity<String> chooseBuyerController(@RequestBody String json) throws Exception {
		String result = "";
		ABox jsonBox = new ABox();
		jsonBox = jsonBox.jsonToABox(json);
		try {
			result = goodsService.chooseBuyer(jsonBox).aBoxToJsonObject().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}



}
