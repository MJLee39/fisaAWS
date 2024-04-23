// package com.example.zip1.controller;
//
// import java.io.IOException;
// import java.net.MalformedURLException;
// import java.nio.charset.StandardCharsets;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import java.util.List;
//
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.core.io.Resource;
// import org.springframework.core.io.UrlResource;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.multipart.MultipartFile;
// import org.springframework.web.util.UriUtils;
//
// import com.amazonaws.services.s3.AmazonS3;
// import com.example.zip1.dto.Content;
// import com.example.zip1.dto.ContentForm;
// import com.example.zip1.dto.UploadFile;
// import com.example.zip1.service.ContentService;
// import com.example.zip1.service.FileStore;
// import com.example.zip1.service.S3UploadService;
//
// import lombok.RequiredArgsConstructor;
//
// @Controller
// @RequiredArgsConstructor
// public class ContentController {
//
// 	private final ContentService contentService;
//
// 	private final S3UploadService s3UploadService;
//
// 	private final FileStore fileStore;
//
// 	private final AmazonS3 amazonS3;
//
// 	@Value("${cloud.aws.s3.bucket}")
// 	private String bucket;
//
// 	// 홈 화면
// 	@GetMapping(value = {"", "/"})
// 	public String home(Model model) {
// 		model.addAttribute("contents", contentService.getAllContents());
// 		return "home";
// 	}
//
// 	// 글 쓰기 화면
// 	@GetMapping("/content/write")
// 	public String writePage() {
// 		return "write-page";
// 	}
//
// 	@PostMapping("/content/write")
// 	public String writeContent(ContentForm form) throws IOException {
// 		Content content = new Content();
// 		content.setTitle(form.getTitle());
// 		content.setWriter(form.getWriter());
// 		content.setTexts(form.getTexts());
//
// 		LocalDateTime NowTime = LocalDateTime.now();
// 		String formatDate = NowTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
// 		content.setUpdateDate(formatDate);
//
// 		// 첨부파일, 이미지들 처리하는 부분
// 		UploadFile attachFile = fileStore.storeFile(form.getAttachFile());
// 		List<UploadFile> imageFiles = fileStore.storeFiles(form.getImageFiles());
// 		content.setAttachFile(attachFile);
// 		content.setImageFiles(imageFiles);
//
// 		contentService.writeContent(content);
//
// 		return "redirect:/";
// 	}
//
// 	// 글 보기 화면
// 	@GetMapping("/content/{id}")
// 	public String showContent(@PathVariable int id, Model model) {
// 		model.addAttribute("content", contentService.getContent(id));
// 		return "content-page";
// 	}
//
// 	// 글 수정
// 	@PostMapping("/content/{id}")
// 	public String editContent(@PathVariable int id, Content content) {
// 		contentService.editContent(id, content.getTexts(), content.getPassword());
// 		return "redirect:/";
// 	}
//
// 	// 글 삭제
// 	@PostMapping("/content/delete/{id}")
// 	public String deleteContent(@PathVariable int id, Content content) {
// 		contentService.deleteContent(id, content.getPassword());
// 		return "redirect:/";
// 	}
//
// 	@ResponseBody
// 	@GetMapping("/images/{filename}")
// 	public Resource showImage(@PathVariable String filename) throws MalformedURLException {
// 		return new UrlResource("file:" + fileStore.getFullPath(filename));
// 	}
//
// 	@GetMapping("/attach/{id}")
// 	public ResponseEntity<Resource> downloadAttach(@PathVariable int id) throws MalformedURLException {
// 		Content content = contentService.getContent(id);
//
// 		System.out.println(content.getAttachFile());
// 		String storeFilename = content.getAttachFile().getStoreFilename();
// 		String uploadFilename = content.getAttachFile().getUploadFilename();
// 		System.out.println(fileStore.getFullPath(storeFilename));
//
// 		UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFilename));
//
// 		// 업로드 한 파일명이 한글인 경우 아래 작업을 안해주면 한글이 깨질 수 있음
// 		String encodedUploadFileName = UriUtils.encode(uploadFilename, StandardCharsets.UTF_8);
// 		String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";
//
// 		// header에 CONTENT_DISPOSITION 설정을 통해 클릭 시 다운로드 진행
// 		return ResponseEntity.ok()
// 			.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
// 			.body(urlResource);
// 	}
//
// 	//미리보기
// 	@GetMapping("/mypage")
// 	public String myPage(Model model) {
// 		String filename = "im.jpg"; // 파일명은 적절히 설정
// 		String url = amazonS3.getUrl(bucket, filename).toString();
// 		model.addAttribute("imageUrl", url);
// 		return "preview"; // 동적으로 생성할 HTML 템플릿 파일명
// 	}
//
// 	//S3에 업로드
// 	@PostMapping("/upload")
// 	public ResponseEntity<String> uploadFile(@RequestParam("files") MultipartFile[] files) {
// 		try {
// 			// 여러 파일을 처리하도록 로직 수정
// 			List<String> urls = new ArrayList<>();
// 			for (MultipartFile file : files) {
// 				String url = s3UploadService.saveFile(file);
// 				urls.add(url);
// 			}
// 			return ResponseEntity.ok(urls.toString());
// 		} catch (IOException e) {
// 			e.printStackTrace();
// 			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload files");
// 		}
// 	}
// }
