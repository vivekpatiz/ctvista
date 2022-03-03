package com.danaherdigital.che_hx.report_management.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danaherdigital.che_hx.report_management.service.DocumentService;
import com.danaherdigital.che_hx.report_management.utils.ApplicationConstants;

@RestController
@RequestMapping("/api/v1/document")
public class DocumentController {

	static final  Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	DocumentService documentService;


	 	@PostMapping("/excel/{type}")
	    public ResponseEntity<Resource> getExcelDocumentTemplate(@RequestBody String data,@PathVariable String type) throws IOException {
	    	LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(),
					 "getExcelDocumentTemplate");

	    	 File file =  documentService.getExcelDocumentTemplate(data,type);
   	    			   InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	    			    return ResponseEntity.ok()
	    			            .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" )
	    			            .contentLength(file.length())
	    			            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	    			            .body(resource);
	    }


	 	@GetMapping("/doc")
	    public ResponseEntity<Resource> getS3Document(@RequestParam("filename") String docName) throws FileNotFoundException {
	    	LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(),
					 "getPdfDocument");

	    	 File file =  documentService.getS3Document(docName);
   	    			   InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	    			    return ResponseEntity.ok()
	    			            .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" )
	    			            .contentLength(file.length())
	    			            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	    			            .body(resource);
	    }

}
