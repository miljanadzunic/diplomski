package rs.diplomski.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.diplomski.controller.dto.PriceListDTO;
import rs.diplomski.mapper.PriceListMapper;
import rs.diplomski.model.PriceList;
import rs.diplomski.repository.PriceListRepository;

@Service
public class PriceListService {

	@Autowired
	private PriceListRepository priceListRepository;
	
	@Autowired
	private PriceListMapper priceListMapper;
	
	@Transactional(readOnly = true)
	public List<PriceListDTO> getAllPriceList() {
		List<PriceList> res = priceListRepository.findAll();
		List<PriceListDTO> resDTOs = priceListMapper.entitiesToDtos(res);
		return resDTOs;
	}
}
