package com.avg.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

import com.avg.exceptions.AvgException;
import com.avg.model.Avs;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;

@Entity(name = "IMSI")
@Table(name = "imsi")
public class Imsi {
	
	@Id
	private long imsi;

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch=FetchType.EAGER,
			orphanRemoval = true
	)
	private List<Avs> avs;
	
	/*@ElementCollection
	List<Avs> avs;
	*/

	public Imsi() {
		super();
	}


	public Imsi(long imsi) {
		super();
		
		this.imsi = imsi;
		this.avs = new ArrayList<Avs>();
	}

	public Imsi(String imsi) throws IOException, AvgException{
		super();

		JsonNode jsonImsi = JsonLoader.fromString(imsi);
		this.imsi = jsonImsi.get("imsi").longValue();
		this.avs = new ArrayList<Avs>();

		for ( Iterator<JsonNode> iterAvs = jsonImsi.get("avs").elements(); iterAvs.hasNext();){

			JsonNode  next = iterAvs.next();
			Avs newAvs = new Avs(
					next.get("rand").asText(),
					next.get("autn").asText(),
					next.get("ck").asText(),
					next.get("ik").asText(),
					next.get("xres").asText());

			this.addAvs(newAvs);
		}

	 /*
		JsonNode array = objectMapper.readValue(rawContentParameters, JsonNode.class);
		JsonNode object = array.get(1);

		String reportKey = object.get("reportKey").textValue();

		logger.info("ExportController : generatesExportExcel : parameters: {}", reportKey);
		*/
	}


	public Imsi(long imsi, Avs avs) {
		super();
		
		this.imsi = imsi;

		this.avs = new ArrayList<Avs>();
		this.avs.add(avs);
		
	}

	public long getImsi() {
		return this.imsi;	
	}
	public void setImsi(long imsi) {
		this.imsi = imsi;	
	}

	public List<Avs> getAvs() {
		return this.avs;	
	}
	public void setAvs(List<Avs> avs) {
		this.avs = avs;	
	}


	public void addAvs(Avs avs)
			throws AvgException {

		if(this.avs.indexOf(avs) == -1) {
			this.avs.add(avs);
		}
		else {
			throw new AvgException("Duplicated Avs");
		}
	}
	public void deleteAvs(Avs avs) throws AvgException {
		if(this.avs.indexOf(avs) != -1){
			this.avs.remove(this.avs.indexOf(avs));
		}
		else{
			throw new AvgException("Avs doesn't exist in " + String.valueOf(imsi));
		}


	}

		
}