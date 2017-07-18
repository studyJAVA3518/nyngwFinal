package com.nyngw.sharingInformation.noticeMatter.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.BoardListViewVO;
import com.nyngw.dto.BoardVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.CommandBoardVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.sharingInformation.noticeMatter.service.NoticeMatterServiceImpl;

@Controller
@RequestMapping("/sharingInformation/noticeMatter")
public class NoticeMatterController implements ApplicationContextAware {
	private static final int PAGE_NUMBER_COUNT_PER_PAGE = 5;
	@Autowired
	private NoticeMatterServiceImpl noticeMatterService;
	
	@Autowired
	private CommonServiceImpl CommonService;
	
	/**
	 * 공지사항 List를 보여주는 페이지의 url을 반환하는 메소드
	 * @return  공지사항 url을 반환
	 */
	@RequestMapping("/nmList")
	public String noticeMatterCheck(@RequestParam(value="page",defaultValue="1")int pageNumber,
			Model model,String val, String index){
		Board_SelectVO select = new Board_SelectVO();
		if(val!=null && !val.equals("")){
			select.setIndex(index);
			select.setVal(val);
		}
		BoardListViewVO viewData = (BoardListViewVO) noticeMatterService.selectNoticeMatterList(pageNumber, select);
		MemberVO member = new MemberVO();
		List<BoardVO> list = viewData.getBoardList();
		for(int i = 0; i < list.size(); i++){
			member = CommonService.findMemberByMemNumber(list.get(i).getBoard_mem_number());
			//			list.get(i).setMem_name();
			viewData.getBoardList().get(i).setMem_name(member.getMem_name());
			System.out.println(";lll"+member.getMem_name());
		}
		if(viewData.getPageTotalCount()>0){
			int beginPageNumber = (viewData.getCurrentPageNumber()-1)/PAGE_NUMBER_COUNT_PER_PAGE*PAGE_NUMBER_COUNT_PER_PAGE+1;
			int endPageNumber = beginPageNumber+ PAGE_NUMBER_COUNT_PER_PAGE-1;
			if(endPageNumber > viewData.getPageTotalCount()){
				endPageNumber = viewData.getPageTotalCount();
			}
			model.addAttribute("perPage", PAGE_NUMBER_COUNT_PER_PAGE);	//페이지 번호의 갯수
			model.addAttribute("end", viewData.getBoardList().size()-1);//마지막 페이지
			model.addAttribute("beginPage", beginPageNumber);	//보여줄 페이지 번호의 시작
			model.addAttribute("endPage", endPageNumber);		//보여줄 페이지 번호의 끝
		}
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		if(val!=null && !val.equals("")){
			model.addAttribute("select",select);
		}
		return "sharingInformation/noticeMatter/noticeMatterList";
	}
	
	/**
	 * 공지사항 List를 보여주는 페이지의 url을 반환하는 메소드
	 * @return  공지사항 url을 반환
	 */
//	@RequestMapping("/nmSelect")
//	public String noticeMatterSelect(){
//		
//		return "sharingInformation/noticeMatter/noticeMatterList";
//	}
	
	/**
	 * 공지사항 List페이지에서 등록 버튼을 누를씨 등록페이지를 보여주는 url을 반환하는 메소드
	 * @return 공지사항등록페이지 url 반환
	 */
	@RequestMapping("/nmWriteForm")
	public String noticeMatterWriteForm(String page, Model model){
		model.addAttribute("page",page);
		return "sharingInformation/noticeMatter/noticeMatterWriteForm";
	}
	
	/**
	 * 정보를 입력한뒤 입력버튼을 눌러 화면을 전환해주는 url을 반환하는 메소드
	 * @return 등록한뒤 url 반환
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value="/nmWrite", method=RequestMethod.POST)
	public String noticeMatterWrite(CommandBoardVO commandboard, String page,Principal principal,
			@RequestParam( value="content") String board_content) throws IOException{
		/*String upload = "D:/git/nyngw/nyngw_final/nyngw_final/src/main/webapp/WEB-INF/upload/notice";
		MultipartFile multipartFile = commandboard.getBoard_file_name();
		if(!multipartFile.isEmpty()){
			File file = new File(upload,multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);*/
			BoardVO board = commandboard.toBoardVO();
			MemberVO member = CommonService.findMemberByMemId(principal.getName());
			board.setBoard_mem_number(member.getMem_number());
			board.setBoard_content(board_content);
//			board.setBoard_file_name(multipartFile.getOriginalFilename());
			noticeMatterService.noticeMatterInsert(board);
			
			return "redirect:/sharingInformation/noticeMatter/nmList";
//		}
//		return "redirect:/sharingInformation/noticeMatter/nmWriteForm";
	}
	
	/**
	 * 공지사항 List페이지에서 수정 버튼을 누를시 수정페이지를 보여주는 url을 반환하는 메소드
	 * @return 공지사항수정페이지 url 반환
	 */
	@RequestMapping("/nmUpdateForm")
	public String noticeMatterUpdateForm(String board_number, Model model, String page){
		BoardVO board = noticeMatterService.selectNoticeMatte(board_number);
		MemberVO member = member = CommonService.findMemberByMemNumber(board.getBoard_mem_number());
		board.setMem_name(member.getMem_name());
		System.out.println(member.getMem_name());
		model.addAttribute("board", board);
		model.addAttribute("page",page);
		return "sharingInformation/noticeMatter/noticeMatterUpdateForm";
	}
	
	/**
	 * 
	 * @return 공지사항수정페이지 url 반환
	 */
	@RequestMapping("/nmUpdate")
	public String noticeMatterUpdate(BoardVO board,@RequestParam( value="content") String board_content){
		board.setBoard_code_number("code11");
		board.setBoard_content(board_content);
		noticeMatterService.noticeMatterUpdate(board);
		return "redirect:/sharingInformation/noticeMatter/nmList";
	}
	
	/**
	 * 공지사항 List페이지에서 삭제 버튼을 누를시 삭제페이지를 보여주는 url을 반환하는 메소드
	 * @return 공지사항삭제페이지 url 반환
	 */
//	@RequestMapping("/nmDeleteForm")
//	public String noticeMatterDeleteForm(){
//		
//		return "sharingInformation/noticeMatter/noticeMatterList";
//	}
	
	/**
	 * 
	 * @return 공지사항삭제페이지 url 반환
	 */
	@RequestMapping("/nmDelete")
	public @ResponseBody Map<String,String> noticeMatterDelete(String id){
		System.out.println(id+"오니?");
		noticeMatterService.noticeMatterDelete(id);
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put("uri", "/sharingInformation/noticeMatter/nmList");
		return resultMap;
	}
	
	/**
	 * 등록된 공지사항을 선택하면 내용을 상세히 볼수 있는 페이지의 url을 반환하는 메서드
	 * @return 공지사항 상세페이지 url 반환
	 */
	@RequestMapping("/nmDetail")
	public String noticeMatterDetail(String board_number, Model model, String page){
		BoardVO board = noticeMatterService.selectNoticeMatte(board_number);
		MemberVO member = member = CommonService.findMemberByMemNumber(board.getBoard_mem_number());
		board.setMem_name(member.getMem_name());
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		return "sharingInformation/noticeMatter/noticeMatterDetail";
	}
	@RequestMapping("/noticeDownload")
    public ModelAndView download(@RequestParam("fileName")String fileName){ // 가져올 파일이름을 넘겨받음.
         
    	//파일을 가져올 경로를 적어주고 + 가져올 파일 이름을 받아옴. 
        String fullPath = "D:/git/nyngw/nyngw_final/nyngw_final/src/main/webapp/WEB-INF/upload/notice/" + fileName;
         
        File file = new File(fullPath);
         
        return new ModelAndView("download", "downloadFile", file);
    }
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 상세보기에서 목록보기 버튼을 누르거나 등록, 수정, 삭제 화면에서 취소버튼을 누르면 보던 리스트 페이지로 가는 url을 반환하는 메서드
	 * @return 자신이 보던 리스트 화면으로 돌아가는 url 반환
	 */
//	@RequestMapping("/cancle")
//	public String cancleAndList(){
//		
//		return "sharingInformation/noticeMatter/noticeMatterList";
//	}
}
