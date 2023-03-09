package rs.diplomski.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.diplomski.controller.dto.PriceListDTO;
import rs.diplomski.service.PriceListService;
import rs.diplomski.sys.model.ResponseWrapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class PriceListController {

	@Autowired
	private PriceListService priceListService;

	@RequestMapping(value = "/getAllPriceList", method = RequestMethod.GET)
	ResponseEntity<?> getAllPriceList() throws Exception {
		List<PriceListDTO> resDTOs = this.priceListService.getAllPriceList();
		return new ResponseEntity<>(new ResponseWrapper(resDTOs), HttpStatus.OK);
	}
}
