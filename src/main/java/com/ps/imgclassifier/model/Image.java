package com.ps.imgclassifier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="img")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
	private Long id;
    
    @Column(name="fileName")
	private String fileName;
    
    @Column(name="filetype")
	private String fileType;
    
    @Column(name="resultRecognition")
    private String resultRecognition;
    
    @Column(name="content")
	@Lob
	private byte[] content;
    
    @Column(nullable=true, insertable=false, updatable=false)
    private String src;
    
    public Image() {
    	
    }
    
	public Image(Long id, String fileName, String resultRecognition, String src) {
		this.id = id;
		this.fileName = fileName;
		this.resultRecognition = resultRecognition;
		this.src = src;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}

	public String getResultRecognition() {
		return resultRecognition;
	}

	public void setResultRecognition(String resultRecognition) {
		this.resultRecognition = resultRecognition;
	}
    
    
}
