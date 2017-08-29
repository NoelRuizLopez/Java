package com.avg.service;

import java.util.List;
import java.util.Optional;

import com.avg.exceptions.AvgException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avg.model.Avs;
import com.avg.model.Imsi;

import com.avg.repository.ImsiRepository;

@Service("AVGService")
public class AVGServiceImpl implements AVGService{

	@Autowired
	private ImsiRepository imsiRepository;
	
	@Override
	public List<Imsi> getAllImsis() {
		return (List<Imsi>) imsiRepository.findAll();
	}
	
	@Override
	public Optional<Imsi> getAllAvsFromImsi(long imsi) {
	
		return imsiRepository.findById(imsi);
	}

	@Override
	public long deleteImsi(long imsi) {
		try {
			imsiRepository.deleteById(imsi);
		}catch (Exception e){
			imsi = 0;
		}
		return imsi;
	}

	@Override
	public Imsi postImsi(Imsi imsi) {
		return imsiRepository.save(imsi);
	}

	@Override
	public Imsi addAvstoImsi(long imsi, Avs avs) throws AvgException {
		Imsi imsi_to_update = imsiRepository.findById(imsi).get();

		imsi_to_update.addAvs(avs);

		return imsiRepository.save(imsi_to_update);

	}

	public Imsi deleteAvs(long imsi, Avs avs) throws AvgException {
		Imsi imsi_to_update = imsiRepository.findById(imsi).get();
		imsi_to_update.deleteAvs(avs);

		return imsiRepository.save(imsi_to_update);
	}

}