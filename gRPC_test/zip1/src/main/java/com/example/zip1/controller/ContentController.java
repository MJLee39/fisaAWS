package com.example.zip1.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;

import com.example.zip1.dto.Content;
import com.example.zip1.dto.ContentForm;
import com.example.zip1.dto.UploadFile;
import com.example.zip1.service.FileStore;

public class FileController {

	@PostMapping("/content/write")
	public String writeContent(ContentForm form) throws IOException {
		Content content = new Content();
		content.setTitle(form.getTitle());
		content.setWriter(form.getWriter());
		content.setTexts(form.getTexts());

		LocalDateTime NowTime = LocalDateTime.now();
		String formatDate = NowTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		content.setUpdateDate(formatDate);

		// 첨부파일, 이미지들 처리하는 부분
		FileStore fileStore = new FileStore();
		ContentService contentService = new ContentService();

		UploadFile attachFile = fileStore.storeFile(form.getAttachFile());
		List<UploadFile> imageFiles = fileStore.storeFiles(form.getImageFiles());
		content.setAttachFile(attachFile);
		content.setImageFiles(imageFiles);

		contentService.writeContent(content);

		return "redirect:/basic-board";
	}
}
