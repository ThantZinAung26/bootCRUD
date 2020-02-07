package com.uploadFile.rest.RestFileUpload.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uploadFile.rest.RestFileUpload.model.UploadModel;

@RestController
public class RestUploadController {

	private final Logger logger = LoggerFactory.getLogger(RestController.class);
	private final String UPLOAD_FOLDER = "C:\\Users\\opmyanmar\\upload";

	@GetMapping("/api/all")
	public String getAll() {
		return "Page";
	}

	@PostMapping("/api/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFile) {

		logger.debug("Single file upload!");

		if (multipartFile.isEmpty()) {
			return new ResponseEntity<Object>("Please select file.", HttpStatus.OK);
		}

		try {
			writeFile(Arrays.asList(multipartFile));
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Success file upload " + multipartFile.getOriginalFilename(), new HttpHeaders(),
				HttpStatus.OK);
	}

	@PostMapping("/api/upload/multi")
	public ResponseEntity<?> multiFileUpload(@RequestParam("extraed") String extraField,
			@RequestParam("files") MultipartFile[] uploadFiles) {

		logger.debug("Successful multi file upload");

		String uploadFileName = Arrays.stream(uploadFiles).map(name -> name.getOriginalFilename())
				.filter(mfile -> !StringUtils.isEmpty(mfile)).collect(Collectors.joining(" , "));

		if (StringUtils.isEmpty(uploadFileName)) {
			return new ResponseEntity<>("Please select a file ", HttpStatus.OK);
		}

		try {
			writeFile(Arrays.asList(uploadFiles));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Successfully uploaded - " + uploadFileName, HttpStatus.OK);
	}
	
	@PostMapping("/api/upload/multi/file")
	public ResponseEntity<?> multiFileUploadModel(@ModelAttribute UploadModel model) {
		
		logger.debug("Mutiple file upload with model.");
		
		try {
			writeFile(Arrays.asList(model.getFiles()));
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("Successful upload Model",HttpStatus.OK);
	}

	private void writeFile(List<MultipartFile> files) throws IOException {
		for (MultipartFile multipartFile : files) {
			if (files.isEmpty()) {
				continue;
			}

			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + multipartFile.getOriginalFilename());
			Files.write(path, bytes);
		}
	}

}
