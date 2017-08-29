package com.avg.rest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.avg.validator.ValidationTypes;
import com.avg.validator.ValidationUtils;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avg.exceptions.AvgException;
import com.avg.model.Imsi;
import com.avg.model.Avs;
import com.avg.model.Response;
import com.avg.service.AVGService;


@RestController
public class AVGController {
	
	private static final Logger logger = LoggerFactory.getLogger(AVGController.class);

	@Autowired
	private AVGService avgService;
	
	@RequestMapping(value="/authservice/rest/imsi", method=RequestMethod.GET)
	public ResponseEntity<List<Imsi>> getAllImeis(){
    	logger.info("Returning the AVG´s IMSI");
		return new ResponseEntity<List<Imsi>>(avgService.getAllImsis(), HttpStatus.OK);
	}
	
    @RequestMapping(value = "/authservice/rest/imsi/{imsi}", method = RequestMethod.GET)
	public ResponseEntity<Imsi> getAllAvsFromImei(@PathVariable("imsi") long imsi) throws AvgException{
    	logger.info("Imei number to return " + imsi);
		Optional<Imsi> imsi_result = avgService.getAllAvsFromImsi(imsi);

    	if (!imsi_result.isPresent()){
            throw new AvgException("Imsi doesn´t exist");
    	}
		return new ResponseEntity<Imsi>(imsi_result.get(), HttpStatus.OK);
	}

    @RequestMapping(value = "/authservice/rest/imsi/{imsi}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteImsi(@PathVariable("imsi") long imsi) throws AvgException{
    	logger.info("Imsi id to remove " + imsi);
    	long imsi_deleted = avgService.deleteImsi(imsi);
    	
    	if (imsi_deleted != imsi){
            throw new AvgException("Imsi to delete doesn´t exist");
    	}
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),
											Long.toString(imsi_deleted) + " Deleted"), HttpStatus.OK);

	}
    
    @RequestMapping(value = "/authservice/rest/imsi", method = RequestMethod.POST)
   	public ResponseEntity<Imsi> postImsi(@RequestBody String imsi)
			throws AvgException, IOException, ProcessingException {

    	logger.info("Imsi to save " + imsi);
    	if (!ValidationUtils.isJsonValid(ValidationTypes.IMSI, imsi)){
            throw new AvgException("Imsi malformed");
    	}

		return new ResponseEntity<Imsi>(avgService.postImsi(new Imsi(imsi)), HttpStatus.OK);
   	}


	@RequestMapping(value = "/authservice/rest/imsi/{imsi}/avs", method = RequestMethod.POST)
	public ResponseEntity<Imsi> addAvstoImsi(@PathVariable("imsi") long imsi, @RequestBody String avs)
			throws AvgException, ProcessingException, IOException{
		logger.info("Avs to save " + avs);
		if (!ValidationUtils.isJsonValid(ValidationTypes.AVS,avs)){
			throw new AvgException("Avs malformed");
		}

		Optional<Imsi> imsi_result = avgService.getAllAvsFromImsi(imsi);
		if (!imsi_result.isPresent()){
			throw new AvgException("Imsi doesn´t exist");
		}

		return new ResponseEntity<Imsi>(avgService.addAvstoImsi(imsi, new Avs(avs)), HttpStatus.OK);
	}


	@RequestMapping(value = "/authservice/rest/imsi/{imsi}/avs", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteAvs(@PathVariable("imsi") long imsi, @RequestBody String avs)
			throws AvgException, IOException, ProcessingException {

		logger.info("Imsi id to remove " + imsi);

		if (!ValidationUtils.isJsonValid(ValidationTypes.AVS,avs)){
			throw new AvgException("Avs malformed");
		}

		if (!avgService.getAllAvsFromImsi(imsi).isPresent()){
			throw new AvgException("Imsi to delete doesn´t exist");
		}

		avgService.deleteAvs(imsi, new Avs(avs));

		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),
				 "Avs Deleted from " + Long.toString(imsi)), HttpStatus.OK);

	}
}