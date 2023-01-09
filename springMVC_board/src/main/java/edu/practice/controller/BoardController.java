package edu.practice.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import edu.practice.service.BoardService;
import edu.practice.vo.BoardVO;
import edu.practice.vo.SearchVO;
import edu.practice.vo.SmarteditorVO;
import edu.practice.vo.UserVO;

@RequestMapping(value="/board")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	

	@RequestMapping(value="/list.do",method=RequestMethod.GET)
	public String view(Model model,SearchVO vo) {
		
		List<BoardVO> list = boardService.list(vo);
		model.addAttribute("datalist",list);
		
		return "board/list";
	}
	
	@RequestMapping(value="/write.do",method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="/write.do",method=RequestMethod.POST)
	public String write(BoardVO vo,HttpSession session) {
		
		UserVO login = (UserVO) session.getAttribute("login");
		vo.setUidx(login.getUidx());
		vo.setId(login.getId());
		int result = boardService.insert(vo);
		System.out.println(vo.toString());
		
		return "redirect:view.do?bidx="+vo.getBidx();
	}
	
	@RequestMapping(value="/view.do",method=RequestMethod.GET)
	public String view(int bidx,Model model) {
		
		BoardVO vo = boardService.selectByBidx(bidx);
		model.addAttribute("vo", vo);
		
		return "board/view";
	}
	
	@RequestMapping(value="/modify.do",method=RequestMethod.GET)
	public String modify(int bidx,Model model) {
		
		BoardVO vo = boardService.selectByBidx(bidx);
		model.addAttribute("vo",vo);
		return "board/modify";
	}
	
	@RequestMapping(value="/modify.do",method=RequestMethod.POST)
	public String modify(BoardVO vo) {
		System.out.println(vo.toString());
		boardService.updateByBidx(vo);
		
		return "redirect:view.do?bidx="+vo.getBidx();
	}
	
	@RequestMapping(value="/delete.do" , method=RequestMethod.POST)
	public String delete(int bidx) {
		
		boardService.deleteByBidx(bidx);
		
		return "redirect:list.do";
	}
	
	@RequestMapping(value="/file_uploader_html5.do",method=RequestMethod.POST)
	 public void file_uploader_html5(HttpServletRequest request, HttpServletResponse response){
		try {
			//파일정보
			String sFileInfo = "";
			//파일명을 받는다 - 일반 원본파일명
			String sFilename = request.getHeader("file-name");
			//파일 확장자
			String sFilenameExt = sFilename.substring(sFilename.lastIndexOf(".")+1);
			//확장자를소문자로 변경
			sFilenameExt = sFilenameExt.toLowerCase();
				
			//이미지 검증 배열변수
			String[] allowFileArr = {"jpg","png","bmp","gif"};

			//확장자 체크
			int nCnt = 0;
			for(int i=0; i<allowFileArr.length; i++) {
				if(sFilenameExt.equals(allowFileArr[i])){
					nCnt++;
				}
			}

			//이미지가 아니라면
			if(nCnt == 0) {
				PrintWriter print = response.getWriter();
				print.print("NOTALLOW_"+sFilename);
				print.flush();
				print.close();
			} else {
				//디렉토리 설정 및 업로드	
				
				//파일경로
				String filePath = "D:\\springDev\\springMVC_board\\src\\main\\webapp\\resources\\upload";
				File file = new File(filePath);
				
				if(!file.exists()) {
					file.mkdirs();
				}
				
				String sRealFileNm = "";
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
				String today= formatter.format(new java.util.Date());
				sRealFileNm = today+UUID.randomUUID().toString() + sFilename.substring(sFilename.lastIndexOf("."));
				String rlFileNm = filePath + sRealFileNm;
				
				///////////////// 서버에 파일쓰기 ///////////////// 
				InputStream inputStream = request.getInputStream();
				OutputStream outputStream=new FileOutputStream(rlFileNm);
				int numRead;
				byte bytes[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
				while((numRead = inputStream.read(bytes,0,bytes.length)) != -1){
					outputStream.write(bytes,0,numRead);
				}
				if(inputStream != null) {
					inputStream.close();
				}
				outputStream.flush();
				outputStream.close();
				
				///////////////// 이미지 /////////////////
				// 정보 출력
				sFileInfo += "&bNewLine=true";
				// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
				sFileInfo += "&sFileName="+ sFilename;
				sFileInfo += "&sFileURL="+"/resources/upload"+sRealFileNm;
				PrintWriter printWriter = response.getWriter();
				printWriter.print(sFileInfo);
				printWriter.flush();
				printWriter.close();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
}
	
	@RequestMapping(value="/photoUpload",method=RequestMethod.POST)
	public String photoUpload(HttpServletRequest request, SmarteditorVO vo) {
		
		String callback = vo.getCallback();
		String callback_func = vo.getCallback_func();
		String file_result = "";
		
		try {
			if(vo.getFiledata() != null && vo.getFiledata().getOriginalFilename() != null && !vo.getFiledata().getOriginalFilename().equals("")) {
				String original_name = vo.getFiledata().getOriginalFilename();
				String ext = original_name.substring(original_name.lastIndexOf(".")+1);
				String defaultPath = request.getSession().getServletContext().getRealPath("/");
				String path = defaultPath+"resources"+File.separator+"upload"+File.separator;
				
				File file = new File(path);
				System.out.println("path:"+path);
				
				if(!file.exists()) {
					file.mkdirs();
				}
				String realname = UUID.randomUUID().toString()+"."+ext;
				vo.getFiledata().transferTo(new File(path+realname));
				file_result += "&bNewLint=true&sFileName="+original_name+"&sFileURL=/resources/upload/"+realname;
			}else {
				file_result += "&errstr=error";
			}
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		return "redirect:"+callback+"?callback_func"+callback_func+file_result;
		}
	

}
