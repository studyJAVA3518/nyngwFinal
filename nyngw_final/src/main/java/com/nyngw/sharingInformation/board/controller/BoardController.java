package com.nyngw.sharingInformation.board.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
import com.nyngw.dto.Board_CommentVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.CommandBoardVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.mypage.basicSetting.service.BasicSettingServiceImpl;
import com.nyngw.sharingInformation.board.service.BoardServiceImpl;

@Controller
@RequestMapping("/sharingInformation/board")
public class BoardController implements ApplicationContextAware{
	@Autowired
	private BoardServiceImpl boardService;
	
	@Autowired
	private BasicSettingServiceImpl basicSettingService;
	
	@Autowired
	private CommonServiceImpl CommonService;
	private static final int PAGE_NUMBER_COUNT_PER_PAGE = 5;
	/**
	 * 게시판 리스트 화면을 보여주는 url을 반환하는 메서드
	 * @return 게시판 리스트화면 url 반환
	 */
//	@RequestMapping("/list")
//	public String boardList(Model model){
//		List<BoardVO> boardList = boardService.selectList();
//		model.addAttribute("boardList", boardList);
//		return "sharingInformation/board/boardList";
//	}
	
	@RequestMapping("/list")
	public String boardList1(@RequestParam(value="page",defaultValue="1")int pageNumber,
			Model model,String val, String index){
		Board_SelectVO select = new Board_SelectVO();
		if(val!=null && !val.equals("")){
			select.setIndex(index);
			select.setVal(val);
		}
		BoardListViewVO viewData = (BoardListViewVO) boardService.selectBoardList(pageNumber, select);
		MemberVO member = new MemberVO();
		List<BoardVO> list = viewData.getBoardList();
		for(int i = 0; i < list.size(); i++){
			member = CommonService.findMemberByMemNumber(list.get(i).getBoard_mem_number());
			//			list.get(i).setMem_name();
			viewData.getBoardList().get(i).setMem_name(member.getMem_name());
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
		model.addAttribute("sideValue", "sideMenu5");
		if(val!=null && !val.equals("")){
			model.addAttribute("select",select);
		}
		return "sharingInformation/board/boardList";
	}
	/**
	 * 특정게시물을 검색할때 제목이나 내용 작성자 등의 값을 가지고 있는 값을 가지고 검색된 리스트를 보여줄 url을 반환하는 메서드
	 * @return 검색된 리스트 url을 반환
	 */
//	@RequestMapping("/select")
	public String boardSelect(@RequestParam(value="page",defaultValue="1")int pageNumber,String val, String index, Model model, Principal principal){
		Board_SelectVO select = null;
		select.setIndex(index);
		select.setVal(val);
		BoardListViewVO viewData = (BoardListViewVO) boardService.selectBoardList(pageNumber, select);
		MemberVO member = new MemberVO();
		List<BoardVO> list = viewData.getBoardList();
		for(int i = 0; i < list.size(); i++){
			member = basicSettingService.selectMember(list.get(i).getBoard_mem_number());
//			list.get(i).setMem_name();
			viewData.getBoardList().get(i).setMem_name(member.getMem_name());
		}
		
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		return "sharingInformation/board/boardList";
	}
	
	/**
	 * 게시물을 등록하는 버튼을 누르면 등록하는 폼을 보여주는 url을 반환하는 메서드
	 * @return 등록양식폼 url 반환
	 */
	@RequestMapping("/writeForm")
	public String boardWriteForm(String page,Model model){
		
		model.addAttribute("page",page);
		return "sharingInformation/board/boardWriteForm";
	}
	
	/**
	 * 게시물에 대한 정보를 다 적은뒤 등록 버튼을 눌러 화면전환하는 url을 반환하는 메서드
	 * @return 등록 url 반환
	 */
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String boardWrite(CommandBoardVO commandboard,String page, Principal principal,@RequestParam( value="content") String board_content) throws IOException{//,Principal principal
		String upload = "D:/git/nyngw/nyngw_final/nyngw_final/src/main/webapp/WEB-INF/upload/board";
		MultipartFile multipartFile = commandboard.getBoard_file_name();
		BoardVO board = commandboard.toBoardVO();
		MemberVO member = basicSettingService.selectMember(principal.getName());
		board.setBoard_mem_number(member.getMem_number());
		board.setBoard_content(board_content);
		if(!multipartFile.isEmpty()){
			File file = new File(upload,multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			board.setBoard_file_name(multipartFile.getOriginalFilename());
		}else{
			board.setBoard_file_name("");
		}
		boardService.boardInsert(board);
		return "redirect:/sharingInformation/board/list";
//		return "sharingInformation/board/boardWriteForm";
	}
	
	/**
	 * 등록된 게시물을 수정 버튼을 누르면 수정하는 폼을 보여주는 url을 반환하는 메서드
	 * @return 수정양식 폼 url 반환
	 */
	@RequestMapping("/updateForm")
	public String boardUpdateForm(String board_number, Model model,String page,String board_file_name){
		BoardVO board = boardService.selectBoard(board_number);
		MemberVO member = CommonService.findMemberByMemNumber(board.getBoard_mem_number());
		board.setMem_name(member.getMem_name());
		model.addAttribute("board", board);
		model.addAttribute("page",page);
		return "sharingInformation/board/boardUpdateForm";
	}
	
	/**
	 * 게시물 수정내용을 다 적은 뒤 수정버튼을 눌러 화면전환 url을 반환하는 메서드
	 * @return 수정 url 반환
	 */
	@RequestMapping("/update")
	public String boardUpdate(BoardVO board,@RequestParam( value="content") String board_content){
		board.setBoard_code_number("code7");
		board.setBoard_content(board_content);
		boardService.boardUpdate(board);
		return "redirect:/sharingInformation/board/list";
	}
	
	/**
	 * 등록된 게시물을 삭제하기 버튼을 누르면 삭제하는 페이지(등록된 게시물의 비밀번호 같은 걸적는 화면)를 보여주는 url을 반환하는 메서드
	 * @return 삭제폼 url 반환
	 */
	@RequestMapping("7")
	public String boardDeleteForm(){
		
		return null;
	}
	
	/**
	 * 게시물 삭제 버튼을 눌러 화면전환 url을 반환하는 메서드
	 * @return url 반환
	 */
	@RequestMapping("/boardDelete")
	public @ResponseBody Map<String,String> boardDelete(String id){
		boardService.boardDelete(id);
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put("uri", "/sharingInformation/board/list");
		
		return resultMap;
	}
	
	/**
	 * 등록된 게시물을 선택하면 내용을 상세히 볼수 있는 페이지의 url을 반환하는 메서드
	 * @return 게시물 상세페이지 url 반환
	 */
	@RequestMapping("/detail")
	public String boardDetail(String board_number, Model model,String page){
		BoardVO board = boardService.selectBoard(board_number);
		MemberVO member = CommonService.findMemberByMemNumber(board.getBoard_mem_number());
		board.setMem_name(member.getMem_name());
		List<Board_CommentVO> comment = boardService.answerSelectList(board_number);
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberVO mem = basicSettingService.selectMember(user.getUsername());
		
		for(int i = 0; i < comment.size(); i++){
			member = CommonService.findMemberByMemNumber(comment.get(i).getComment_mem_number());
			comment.get(i).setComment_mem_name(member.getMem_name());
		}
		
		if(page==null){
			page = "1";
		}
		model.addAttribute("mem",mem);
		model.addAttribute("board", board);
		model.addAttribute("page",page);
		model.addAttribute("comment",comment);
		return "sharingInformation/board/boardDetail";
	}
	
	/**
	 * 상세보기에서 목록보기 버튼을 누르거나 등록, 수정, 삭제 화면에서 취소버튼을 누르면 보던 리스트 페이지로 가는 url을 반환하는 메서드
	 * @return 자신이 보던 리스트 화면으로 돌아가는 url 반환
	 */
	@RequestMapping("cancle")
	public String cancleAndList(){
		
		return "sharingInformation/board/boardList";
	}
	

	/**
	 * 댓글을 전부 쓴뒤 등록 버튼을 눌러 화면전환 url을 반환하는 메서드 
	 * @return 등록 url 반환
	 */
//	@RequestMapping("/answerWrite")
//	public String answerWrite(String comment_content, String board_number, Principal principal ){
//		MemberVO member = basicSettingService.selectMember(principal.getName());
//		Date dt = new Date();
//		Board_CommentVO comment = new Board_CommentVO();
//		comment.setComment_board_number(board_number);
//		comment.setComment_content(comment_content);
//		comment.setComment_date(dt);
//		comment.setComment_mem_number(member.getMem_number());
//		boardService.answerWrite(comment);
//		return "redirect:/sharingInformation/board/list";
//	}
	@RequestMapping("/answerWrite")
	public @ResponseBody Map<String,String> answerWrite(String comment_content, String id, Principal principal ){
		MemberVO member = basicSettingService.selectMember(principal.getName());
		String board_number = id;
		Date dt = new Date();
		Board_CommentVO comment = new Board_CommentVO();
		comment.setComment_board_number(id);
		comment.setComment_content(comment_content);
		comment.setComment_date(dt);
		comment.setComment_mem_number(member.getMem_number());
		boardService.answerWrite(comment);
//		return "redirect:/sharingInformation/board/list";
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put("uri", "/sharingInformation/board/detail?board_number="+board_number);
		return resultMap;
	}
	
	/**
	 * 삭제시 버튼을 눌러 화면을 전환하는 url을 반환하는 메서드
	 * @return 삭제 url 반환
	 */
	@RequestMapping("/answerDelete")
	public @ResponseBody Map<String,String> answerDelete(String id, String board_number){
		boardService.answerDelete(id);
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put("uri", "/sharingInformation/board/detail?board_number="+board_number);
		return resultMap;
	}
	
	/**
	 * 등록된 댓글을 수정하는 페이지를 보여주기 위한 url을 반환하는 메서드
	 * @return 댓글수정 url 반환
	 */
	@RequestMapping("15")
	public String answerUpdateForm(){
		
		return null;
	}
	
	/**
	 * 수정할 내용을 다 입력한뒤 수정 버튼을 눌러 화면을 전환하는 url을 반환하는 메서드
	 * @return 수정 url 반환
	 */
	@RequestMapping("/answerUpdate")
	public @ResponseBody Map<String,String> answerUpdate(String board_number, String comment_number, String comment_mem_number, String comment_content){
		Date dt = new Date();
		Board_CommentVO comment = new Board_CommentVO();
		comment.setComment_board_number(board_number);
		comment.setComment_content(comment_content);
		comment.setComment_date(dt);
		comment.setComment_mem_number(comment_mem_number);
		comment.setComment_number(comment_number);
		boardService.answerUpdate(comment);
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put("uri", "/sharingInformation/board/detail?board_number="+board_number);
		return resultMap;
	}
	
	@RequestMapping("/boardDownload")
    public ModelAndView download(@RequestParam("fileName")String fileName){ // 가져올 파일이름을 넘겨받음.
         
    	//파일을 가져올 경로를 적어주고 + 가져올 파일 이름을 받아옴. 
        String fullPath = "D:/git/nyngw/nyngw_final/nyngw_final/src/main/webapp/WEB-INF/upload/board/" + fileName;
         
        File file = new File(fullPath);
         
        return new ModelAndView("download", "downloadFile", file);
    }
	
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		
	}
	
}
