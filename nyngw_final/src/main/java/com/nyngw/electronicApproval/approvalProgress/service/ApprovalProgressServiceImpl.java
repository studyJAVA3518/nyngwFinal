package com.nyngw.electronicApproval.approvalProgress.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Approval_HistoryVO;
import com.nyngw.dto.Approval_StepVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.electronicApproval.approvalProgress.dao.ApprovalProgressDaoImpl;

@Service
public class ApprovalProgressServiceImpl implements ApprovalProgressService {

	@Autowired
	ApprovalProgressDaoImpl approvalProgressDao;

	@Autowired
	private CommonServiceImpl commonServiceImpl;
	
	//모든 미결재문서를 가져옴
	public void defaultWA(Model model,Principal principal) {
		//접속자의 정보 검색
		String mem_id = principal.getName();
		MemberVO member = commonServiceImpl.findMemberByMemId(mem_id);
		//접속자의 사원번호로 결재라인에 올라가있는 결재번호 검색
		List<String> ea_numberList = approvalProgressDao.ap_selectEaNumberByMemId(member.getMem_number());
		
		//return할 결재정보 리스트
		List<Electronic_ApprovalVO> eaList = new ArrayList<Electronic_ApprovalVO>();
		List<String> statusList = new ArrayList<String>();

		//결재별 현황 검색
		for (String ea_number: ea_numberList) {
			//한 결재의 마지막 결재우선순위 검색
			int lastAstPriority = approvalProgressDao.selectLastAstPriority(ea_number);
			
			//한 사원의 한 결재의 우선순위 검색 
			Map<String, String> paramMap = new HashMap<String,String>();
			paramMap.put("ast_ea_number", ea_number);
			paramMap.put("ast_mem_number", member.getMem_number());
			int memberAstPriority = approvalProgressDao.selectOneAstPriority(paramMap);
			
			//한 결재의 마지막 결재이력의 우선순위 검색
			int lastAhHistory = approvalProgressDao.selectLastApprovalHistory(ea_number);
			
			//미결재문서 (자신의 우선순위 차례이면)
			if(lastAhHistory+1==memberAstPriority){
				Electronic_ApprovalVO eaVO = approvalProgressDao.selectEA(ea_number);
				eaList.add(eaVO);
				statusList.add("상신");
			}else if(lastAhHistory==lastAstPriority+1){
				Electronic_ApprovalVO eaVO = approvalProgressDao.selectEA(ea_number);
				eaList.add(eaVO);
				statusList.add("전결");
			}
			
		}
		model.addAttribute("eaList",eaList);
		model.addAttribute("statusList", statusList);
		
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		for (Electronic_ApprovalVO eaVO : eaList) {
			code_nameList.add(commonServiceImpl.findCodeNameByDocNumber(eaVO.getEa_doc_number()));
			memberList.add(commonServiceImpl.findMemberByMemNumber(eaVO.getEa_mem_number()));
		}
		model.addAttribute("code_nameList",code_nameList );
		model.addAttribute("memberList",memberList );
		
	}
	
	public void waDetail(Model model, String ea_number,Principal principal) {
		//결재정보 setting
		Electronic_ApprovalVO eaVO = approvalProgressDao.selectEA(ea_number);
		model.addAttribute("eaVO",eaVO);
		//작성자 이름
		MemberVO member = commonServiceImpl.findMemberByMemNumber(eaVO.getEa_mem_number());
		model.addAttribute("mem_name",member.getMem_name());
		
		//결재스탭 정보 setting 
		Approval_StepVO asVO = new Approval_StepVO();
		asVO.setAst_ea_number(ea_number);
		asVO.setAst_al_number("A");
		List<Approval_StepVO> approvalMemberList = approvalProgressDao.selectAstMemNumberByEaNumber(asVO);
		asVO.setAst_al_number("B");
		List<Approval_StepVO> agreementMemberList = approvalProgressDao.selectAstMemNumberByEaNumber(asVO);
		asVO.setAst_al_number("C");
		List<Approval_StepVO> implementMemberList = approvalProgressDao.selectAstMemNumberByEaNumber(asVO);
		asVO.setAst_al_number("D");
		List<Approval_StepVO> referenceMemberList = approvalProgressDao.selectAstMemNumberByEaNumber(asVO);
		
		List<MemberVO> approvalMember= new ArrayList<MemberVO>();
		List<MemberVO> agreementMember= new ArrayList<MemberVO>();
		String implementMemberName = "";
		String referenceMemberName = "";
		MemberVO member2 =null;
		
		//결재 이력 정보
		List<String> approvalMem_sign = new ArrayList<String>();
		List<String> agreementMem_sign = new ArrayList<String>();
		int lastAhHistory = approvalProgressDao.selectLastApprovalHistory(ea_number);	//결재 이력에 등록된 마지막 우선순위를 검색
		int index=1;
		int indexA = 0;
		int indexB = 0;
		
		//합의자의 싸인을 담기 위함
		for (Approval_StepVO approval_StepVO : agreementMemberList) {
			member2 = commonServiceImpl.findMemberByMemNumber(approval_StepVO.getAst_mem_number());
			agreementMember.add(member2);
			if(lastAhHistory>=index){	//마지막 결재자의 우선순위가 인덱스보다 크다는 것은 for문에 들어온 결재가 완료되었다는 것
				agreementMem_sign.add(member2.getMem_sign());
				indexB++;
			}
			index++;
		}
		//결재자의 싸인을 담기 위함
		for (Approval_StepVO approval_StepVO : approvalMemberList) {
			member2 = commonServiceImpl.findMemberByMemNumber(approval_StepVO.getAst_mem_number());
			approvalMember.add(member2);
			if(lastAhHistory>=index){
				approvalMem_sign.add(member2.getMem_sign());
				indexA++;
			}
			index++;
		}
		model.addAttribute("approvalMem_sign",approvalMem_sign);
		model.addAttribute("agreementMem_sign",agreementMem_sign);
		
		index = 0;
		for (Approval_StepVO approval_StepVO : implementMemberList) {
			member2 = commonServiceImpl.findMemberByMemNumber(approval_StepVO.getAst_mem_number());
			if(index!=0){
				implementMemberName += ", ";
			}
			implementMemberName += (member2.getMem_name());
		}
		for (Approval_StepVO approval_StepVO : referenceMemberList) {
			member2 = commonServiceImpl.findMemberByMemNumber(approval_StepVO.getAst_mem_number());
			if(index!=0){
				referenceMemberName += ", ";
			}
			referenceMemberName += (member2.getMem_name());
		}
		model.addAttribute("approvalMember",approvalMember);
		model.addAttribute("agreementMember",agreementMember);
		model.addAttribute("implementMemberName",implementMemberName);
		model.addAttribute("referenceMemberName",referenceMemberName);
		
		//자신의 결재우선순위
		//작성자 이름
		MemberVO member3 = commonServiceImpl.findMemberByMemId(principal.getName());
		
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("ast_ea_number", ea_number);
		paramMap.put("ast_mem_number",member3.getMem_number());
		int memberAstPriority = approvalProgressDao.selectOneAstPriority(paramMap);
		model.addAttribute("ast_number","ast"+memberAstPriority);
		
		//전체 결재선의 크기
		int lastAstPriorityOfA = approvalProgressDao.selectLastAstPriorityOfA(ea_number);
		int lastAstPriorityOfB = approvalProgressDao.selectLastAstPriorityOfB(ea_number);
		model.addAttribute("lastAstPriorityOfA",5-lastAstPriorityOfA);	//lastAstPriorityOfA = 5에서 A의 전체 결재선을 뺀 수 = 빈칸의 수 
		model.addAttribute("lastAstPriorityOfB",5-lastAstPriorityOfB);	//lastAstPriorityOfB = 5에서 B의 전체 결재선을 뺀 수 = 빈칸의 수
		
		//결재 이력중 A와 B의 갯수
		List<String> ahAstNumberList = approvalProgressDao.selectAhAstNumberByEaNumber(ea_number);
		System.out.println(ahAstNumberList.size()+"123");
		int countA = 0;
		int countB = 0;
		for (String ast_number : ahAstNumberList) {
			paramMap = new HashMap<String, String>();
			paramMap.put("ast_number", ast_number);
			paramMap.put("ah_ea_number", ea_number);
			if(approvalProgressDao.selectAstAllNumberByAstNumber(paramMap).equals('A')){
				countA++;
			}else{
				countB++;
			}
		}

		System.out.println(indexA);
		System.out.println(lastAstPriorityOfA);
		model.addAttribute("emptyStartA",lastAstPriorityOfA+1);	//3
		model.addAttribute("emptyStartB",lastAstPriorityOfB+1);
//		model.addAttribute("emptyEndA",5-lastAstPriorityOfA);	//3번 돌아야함 즉 처음 + 번호
//		model.addAttribute("emptyEndB",5-lastAstPriorityOfB);
//		model.addAttribute("yesStartA",1);
//		model.addAttribute("yesStartB",1);
//		model.addAttribute("yesEndA",indexA);
//		model.addAttribute("yesEndA",indexB);
		model.addAttribute("noStartA",indexA+1);
		model.addAttribute("noStartB",indexB+1);
		model.addAttribute("noEndA",lastAstPriorityOfA);
		model.addAttribute("noEndB",lastAstPriorityOfB);
		
		
		//A
		//총 5
		//A 결재선 2 lastAstPriorityOfA
		
		//A 2중 1 결재했으면 1	1부터  // indexA = 1 까지 
		//A 2중 결재 안한 거 1	indexA+1 = 2부터 // lastAstPriorityOfA =1개		2-1 = 1
		
		//A 빈칸 3- 시작lastAstPriorityOfA+1=3부터 // 5까지
		
	}
	
	
	
	
	//WA = waiting Approval
	//미결재 문서 검색  -> 구현 필요
	public List<Electronic_ApprovalVO> searchWA(String eADateOption,
			String eAStatusOption, String eAClassificationOption,
			String docSearchOption, String searchText) {
		
		
		
		return null;
	}

	//모든 완료문서를 가져옴
	public List<Electronic_ApprovalVO> defaultCA() {
		return approvalProgressDao.selectCA();
	}

	public List<Electronic_ApprovalVO> defaultRA() {
		return approvalProgressDao.selectRA();
	}

	public Map<String,String>  conformApproval(Approval_HistoryVO ahVO, String mem_pwd,
			Principal principal) {
		Map<String,String> map = new HashMap<String, String>();
		MemberVO member = commonServiceImpl.findMemIdByMemPwd(mem_pwd);
		if(ahVO.getAh_code_number() .equals("code14")){
			//이사람이 결재잔지 합의잔지 체크해줘야함
			if(member!=null){
				if(principal.getName() .equals(member.getMem_id())){
					approvalProgressDao.insertApprovalHistory(ahVO);
					String al_number = approvalProgressDao.selectAllByApprovalAstNumber(ahVO);
					map.put("al_number",al_number);
					map.put("priority",ahVO.getAh_ast_number().substring(3));
					map.put("check","y");
					map.put("mem_sign",member.getMem_sign());
					map.put("uri","/electronicApproval/individualDocumentBox/completeApprovalBox");
				}else{
					map.put("check","n");
				}
			}
		}else if(ahVO.getAh_code_number() .equals("code15")){
			if(member!=null){
				if(principal.getName() .equals(member.getMem_id())){
					//한 결재의 마지막 결재우선순위 검색
					int lastAstPriority = approvalProgressDao.selectLastAstPriority(ahVO.getAh_ea_number());
					Map<String,String> paramMap = new HashMap<String, String>();
					paramMap.put("updateNumber", lastAstPriority+2+"");
					paramMap.put("ea_number", ahVO.getAh_ea_number());
					paramMap.put("ast_number", ahVO.getAh_ast_number());
					approvalProgressDao.updateAstPriority(paramMap);	//결재우선순위를 마지막 결재우선순위+2로 만들기
					
					approvalProgressDao.insertApprovalHistory(ahVO);
					String al_number = approvalProgressDao.selectAllByApprovalAstNumber(ahVO);
					map.put("al_number",al_number);
					map.put("priority",ahVO.getAh_ast_number().substring(3));
					map.put("check","y");
					map.put("mem_sign",member.getMem_sign());
					map.put("uri","/electronicApproval/individualDocumentBox/completeApprovalBox");
				}else{
					map.put("check","n");
				}
			}
		}
		
		return map;
	}

}
