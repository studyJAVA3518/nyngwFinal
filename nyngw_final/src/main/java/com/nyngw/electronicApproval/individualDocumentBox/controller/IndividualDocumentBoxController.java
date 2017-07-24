package com.nyngw.electronicApproval.individualDocumentBox.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.electronicApproval.approvalProgress.dao.ApprovalProgressDaoImpl;
import com.nyngw.electronicApproval.individualDocumentBox.service.IndividualDocumentBoxServiceImpl;

@Controller
@RequestMapping("/electronicApproval/individualDocumentBox")
public class IndividualDocumentBoxController {

	@Autowired
	private IndividualDocumentBoxServiceImpl individualDocumentBoxService;
	@Autowired
	ApprovalProgressDaoImpl approvalProgressDao;
	@Autowired
	private CommonServiceImpl commonService;
	
	//상신문서함
	@RequestMapping("/submitApprovalBox")
	public String submitApprovalBox(Model model, Principal principal){
		//로그인 한 자의 아이디
//		List<Electronic_ApprovalVO> EAList = individualDocumentBoxService.defaultSAB(principal.getName());
		List<Electronic_ApprovalVO> EAList = individualDocumentBoxService.defaultSAB("4");
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		
		for (Electronic_ApprovalVO EAVO : EAList) {
			code_nameList.add(commonService.findCodeNameByDocNumber(EAVO.getEa_doc_number()));
		}
		model.addAttribute("EAList",EAList);
		model.addAttribute("code_nameList",code_nameList );
		return "electronicApproval/individualDocumentBox/submitApprovalBox";
	}
	//상신문서 검색
	@RequestMapping("/searchSubmitApproval")
	public String searchSubmitApproval(Model model, Principal principal){
		return "electronicApproval/individualDocumentBox/submitApprovalBox";
	}
	//상신문서상세
	@RequestMapping("/submitApprovalDetail")
	public String submitApprovalDetail(Model model,String ea_number){
		return "electronicApproval/individualDocumentBox/submitApprovalDetail";
	}
	//상신문서수정페이지
	@RequestMapping("/editDraftForm")
	public String editDraftForm(Model model){
		return "electronicApproval/individualDocumentBox/editDraftForm";
	}
	
	//임시보관함
	@RequestMapping("/outbox")
	public String outbox(){
		return "electronicApproval/individualDocumentBox/outbox";
	}
	
	//완료문서함
	@RequestMapping("/completeApprovalBox")
	public String completeApprovalBox(Model model, Principal principal){
		//로그인 한 자의 아이디
		MemberVO member = commonService.findMemberByMemId(principal.getName());
		//자신이 결재스텝에 포함된 결재번호들을 검색
		List<String> ea_numberList = individualDocumentBoxService.ap_selectEaNumberByMemId(member.getMem_number());
		
		//return할 결재정보 리스트
		List<Electronic_ApprovalVO> eaList = new ArrayList<Electronic_ApprovalVO>();
		for (String ea_number: ea_numberList) {
			//가장높은우선순위 검색
			int lastAstPriority = individualDocumentBoxService.selectLastAstPriority(ea_number);
			
			//자신의 한 결재의 우선순위 검색
			Map<String,String> paramMap = new HashMap<String, String>();
			paramMap.put("ast_ea_number", ea_number);
			paramMap.put("ast_mem_number", member.getMem_number());
			int memberAstPriority = individualDocumentBoxService.selectOneAstPriority(paramMap);
			
			//한 결재의 마지막 결재이력의 우선순위 검색
			int lastAhHistory = individualDocumentBoxService.selectLastApprovalHistory(ea_number);
			
			//자신의 우선순위보다 결재이력의 마지막 우선순위가 높을 시 
			//결재처리를 한 것이므로 select를 해온다.
			if(lastAhHistory>memberAstPriority){
				//검색조건에 따라서 xml 쿼리문 새로 만들어서 다시 불러와야할수 있음... 월요일에 참고하자!!
				Electronic_ApprovalVO eaVO = approvalProgressDao.selectEA(ea_number);
				//jsp에 정보를 뿌려주기위해 list에 담아준다.
				eaList.add(eaVO);
			}
			if(lastAhHistory<1){
				model.addAttribute("status1","상신");
			}else if(lastAhHistory == lastAstPriority){
				model.addAttribute("status1","완료");
			}else{
				model.addAttribute("status1","진행");
			}
		}
		//담아준 list를 model에 담아 넘겨준다.
		model.addAttribute("myEaList",eaList);
		
		
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		for (Electronic_ApprovalVO eaVO : eaList) {
			code_nameList.add(commonService.findCodeNameByDocNumber(eaVO.getEa_doc_number()));
			memberList.add(commonService.findMemberByMemNumber(eaVO.getEa_mem_number()));
		}
		
		model.addAttribute("code_nameList",code_nameList );
		model.addAttribute("memberList",memberList );
		return "electronicApproval/individualDocumentBox/completeApprovalBox";
	}
	//완료문서 검색
	@RequestMapping("/searchCompleteApproval")
	public String searchCompleteApproval(Model model){
		return "electronicApproval/individualDocumentBox/completeApprovalBox";
	}
	
	//반려문서함
	@RequestMapping("/refusedApprovalBox")
	public String refusedApprovalBox(Model model, Principal principal){
		//로그인 한 자의 아이디
		List<Electronic_ApprovalVO> EAList = individualDocumentBoxService.defaultRAB(principal.getName());
//		List<Electronic_ApprovalVO> EAList = individualDocumentBoxService.defaultRAB("4");
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		
		for (Electronic_ApprovalVO EAVO : EAList) {
			code_nameList.add(commonService.findCodeNameByDocNumber(EAVO.getEa_doc_number()));
		}
		model.addAttribute("EAList",EAList);
		model.addAttribute("code_nameList",code_nameList );
		return "electronicApproval/individualDocumentBox/refusedApprovalBox";
	}
	
	//반려 문서 검색
	@RequestMapping("/searchRefusedApproval")
	public String searchRefusedApproval(Model model){
		/*List<Electronic_ApprovalVO> EAList = individualDocumentBoxService.searchRefusedApproval();
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		
		for (Electronic_ApprovalVO EAVO : EAList) {
			code_nameList.add(commonService.findCodeNameByDocNumber(EAVO.getEa_doc_number()));
		}
		
		model.addAttribute("code_nameList",code_nameList );
		model.addAttribute("EAList",EAList );*/
		return "electronicApproval/individualDocumentBox/refusedApprovalBox";
	}
	//반려문서상세
	@RequestMapping("/refusedApprovalDetail")
	public String refusedApprovalDetail(Model model,String ea_number){
		return "electronicApproval/individualDocumentBox/refusedApprovalDetail";
	}
}
