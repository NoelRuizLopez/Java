package com.avg.service;

import java.util.List;
import java.util.Optional;

import com.avg.exceptions.AvgException;
import com.avg.model.Imsi;
import com.avg.model.Avs;

public interface AVGService {
	
	public List<Imsi> getAllImsis();
	
	public Optional<Imsi> getAllAvsFromImsi(long imsi);
	
	public Imsi postImsi(Imsi imsi);

	public Imsi addAvstoImsi(long imsi, Avs avs) throws AvgException;

	public long deleteImsi(long imsi);
	
	public Imsi deleteAvs(long imsi, Avs avs) throws AvgException;

}
