package com.example.demo.Controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.PdfFile;
import com.example.demo.Repository.FileRepository;

@RestController
public class FileController {

	@Autowired
	private FileRepository fileRepo;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file) {
		try {
			PdfFile pdf = new PdfFile();
			pdf.setFileName(file.getOriginalFilename());
			pdf.setFile(file.getBytes());
			pdf.setFileType(file.getContentType());
			fileRepo.save(pdf);
			return ResponseEntity.ok("File uploaded with ID: " + pdf.getId());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadPdf(@PathVariable int id) {
		Optional<PdfFile> pdfFileOptional = fileRepo.findById(id);

		if (pdfFileOptional.isPresent()) {
			PdfFile pdf = pdfFileOptional.get();
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(pdf.getFileType()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdf.getFileName() + "\"")
					.body(pdf.getFile());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<byte[]> viewPdf(@PathVariable int id) {
		Optional<PdfFile> pdfFileOptional = fileRepo.findById(id);
		if (pdfFileOptional.isPresent()) {
			PdfFile pdf = pdfFileOptional.get();
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(pdf.getFileType()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + pdf.getFileName() + "\"")
					.body(pdf.getFile());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
