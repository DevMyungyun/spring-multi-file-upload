package com.sist.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sist.dao.DataBoardDAO;
import com.sist.dao.DataBoardVO;

@Controller
public class BoardController {
	@Autowired
	private DataBoardDAO dao;
	
	@RequestMapping("main/list.do")
	public String main_list(String page,Model model){
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<DataBoardVO> list=dao.boarListData(curpage);
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping("main/insert.do")
	public String main_insert(){
		
		return "insert";
	}
	
	@RequestMapping("main/insert_ok.do")
	public String main_insert_ok(DataBoardVO uploadForm){
		List<MultipartFile> list=uploadForm.getFiles();
		
		//다운로드 폴더가 존재하지 않을때 폴더를 만들어라
		File ff=new File("c:\\download");
		if(ff.exists())
			ff.mkdirs();
		
		//null이 반드시 앞으로 와야함
		if(list!=null && list.size()>0) {
			String fn="";
			String fs="";
			for(MultipartFile mf:list){
				String name=mf.getOriginalFilename();
				File file=new File("c:\\download\\"+name);
				try{
					mf.transferTo(file);
				}catch(Exception e){}
				fn+=name+",";
				fs+=file.length()+",";
			}
			uploadForm.setFilename(fn.substring(0,fn.lastIndexOf(",")));
			uploadForm.setFilename(fs.substring(0,fs.lastIndexOf(",")));
			uploadForm.setFilecount(list.size());
		}else{
			uploadForm.setFilename("");
			uploadForm.setFilesize("");
			uploadForm.setFilecount(0);
		}
		dao.boardInsert(uploadForm);
		
		return "redirect:/main/list.do";
			
	}
	@RequestMapping("main/content.do")
	public String board_content(int no,Model model){
		DataBoardVO vo=dao.boardContentData(no);
		if(vo.getFilecount()>0){
			String[] files=vo.getFilename().split(",");
			System.out.println(Arrays.toString(files));
			model.addAttribute("files", files);
		}
		model.addAttribute("vo", vo);
		return "content";
	}
	@RequestMapping("main/download.do")
	public void board_download(String fn,HttpServletResponse res) {
		try{
			File file=new File("c:\\download\\"+fn);
			res.setHeader("Content-Disposition", "attachment;filename="
							+URLEncoder.encode(fn,"UTF-8"));
			res.setContentLength((int)file.length());
			BufferedInputStream bis=
					new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos=
					new BufferedOutputStream(res.getOutputStream());
			int i=0;
			byte[] buffer=new byte[1024];
			while((i=bis.read(buffer,0,1024))!=-1){
				bos.write(buffer,0,i);
			}
			bis.close();
			bos.close();
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}	
	@RequestMapping("main/delete.do")
	public String board_delete(int no,Model model){
		model.addAttribute("no", no);
		return "delete";
	}
	
	@RequestMapping("main/delete_ok.do")
	public String board_delete(int no,String pwd,Model model){
		DataBoardVO vo=dao.boardFileInfo(no);
		boolean bCheck=dao.boardDelete(no, pwd);
		if(bCheck==true){
			try{
				if(vo.getFilecount()>0){
					String[] str=vo.getFilename().split(",");
					for(String s:str){
						File f=new File("c:\\download\\"+s);
						f.delete();
					}
				}
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			
		}
		model.addAttribute("bCheck", bCheck);
		return "delete_ok";
	}
	
}
