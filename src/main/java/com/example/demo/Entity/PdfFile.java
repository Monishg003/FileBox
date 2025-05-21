package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PdfFile {
	
	@Id
	private int id;
	
	private String fileName;
	
	@Lob
	private byte[] file;
	
	private String fileType;

	public int getId() {
		return id;
	}

	public PdfFile() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String contentType) {
		this.fileType = contentType;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public PdfFile(int id, String fileName, byte[] file, String fileType) {
		this.id = id;
		this.fileName = fileName;
		this.file = file;
		this.fileType = fileType;
	}
	
	
	
	
	

}
