package com.avg.model;

import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;

import javax.persistence.*;
import java.io.IOException;


@Entity(name = "Avs")
@Table(name = "avs")
public class Avs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long key;

	@Basic
    private String rand;
	private String autn;
	private String ck;
	private String ik;
	private String xres;
	

	public Avs() {
		super();
	}
	
	
	public Avs(String rand, String autn, String ck, String ik ,String xres) {
		super();
		this.rand = rand;
		this.autn = autn;
		this.ck = ck;
		this.ik = ik;
		this.xres = xres;
		
	}

	public Avs(String avs) throws IOException {
		super();

		JsonNode jsonAvs = JsonLoader.fromString(avs);

		this.rand = jsonAvs.get("rand").asText();
		this.autn = jsonAvs.get("autn").asText();
		this.ck = jsonAvs.get("ck").asText();
		this.ik = jsonAvs.get("ik").asText();
		this.xres = jsonAvs.get("xres").asText();

	}

    @Column(name="rand")
	public String getRand() {
		return rand;
	}
	public void setRand(String rand) {
		this.rand = rand.replace("\"", "");
	}

    @Column(name="autn")
	public String getAutn() {
		return autn;
	}
	public void setAutn(String autn) {
		this.autn = autn;
	}

    @Column(name="ck")
	public String getCk() {
		return ck;
	}
	public void setCk(String ck) {
		this.ck = ck;
	}

    @Column(name="ik")
	public String getIk() {
		return ik;
	}
	public void setIk(String ik) {
		this.ik = ik;
	}

    @Column(name="xres")
    public String getXres() {
		return xres;
	}
	public void setXres(String xres) {
		this.xres = xres;
	}

    @Override
    public boolean equals(Object o) {

        if(!(o instanceof Avs)) return false;

	    Avs avs = (Avs) o;
        return this.rand.equals(avs.getRand())
                && this.autn.equals(avs.getAutn())
                && this.ck.equals(avs.getCk())
                && this.ik.equals(avs.getIk())
                && this.xres.equals(avs.getXres());
    }

    @Override
    public int hashCode() {
        return Objects.hash(rand, autn, ck, ik, xres);
    }

}