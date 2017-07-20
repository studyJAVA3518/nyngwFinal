package com.nyngw.electronicApproval.draft.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.dto.ApprovalParamVO;
import com.nyngw.dto.DepartmentVO;
import com.nyngw.dto.DocumentSearchVO;
import com.nyngw.dto.DocumentVO;
import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.electronicApproval.draft.dao.DraftDaoImpl;

@Service
public class DraftServiceImpl implements DraftService {

	@Autowired
	private DraftDaoImpl draftDao;
	
	//기안문서함을 처음 열었을 때 모든 document를 차례대로 보여줄 메서드
	//무엇을 기준으로 정렬하여 보여줄지 고민해봐야한다.
	@Override
	public List<DocumentVO> defaultDocumentList() {
		return draftDao.selectDocumentList();
	}

	//문서검색 메서드
	
	public List<DocumentVO> searchDocument(String draftBoxOption, String searchOption, String searchText) {
		List<DocumentVO> documentList = null;
		DocumentSearchVO documentSearchVO = new DocumentSearchVO();
		int whatToRun = 0;
		//0 둘 다 선택 했을 때
		//1 기안문서함만 선택했 안 했을 때 (+1)
		//2 검색어 구분만 선택 안 했을 때(+2)
		//3 둘다 선택 안 했을 때(+1+2)
		
		//내용 등록
		if (searchText==null||searchText.equals("")) {
			documentSearchVO.setSearchText("");
		}else{
			documentSearchVO.setSearchText(searchText);
		}
		//문서함구분에 따라 draftBoxOption을 DB가 알아들을 수 있도록 설정해준다.
		//기안문서A~ 등도 DB에 있는 column이기는 하지만 document 테이블에는 fk로 code8~ 등이 참조되어 있다.
		if (draftBoxOption==null||draftBoxOption.equals("")){
			documentSearchVO.setDraftBoxOption("");
			whatToRun+=1;
		}else{
			documentSearchVO.setDraftBoxOption(draftBoxOption);
		}
		
		//검색어 분류를 선택하지 않았을 시
		if (searchOption.equals("")){
			whatToRun +=2;
		}
		
		switch(whatToRun){
		case 0://둘 다 선택했을 때
			switch(searchOption){
			case "doc_name":
				documentList= draftDao.selectDocumentListByDocName(documentSearchVO);
				break;
			case "doc_explanation":
				documentList= draftDao.selectDocumentListByDocExplanation(documentSearchVO);
				break;
			case "doc_mem_number":
				documentList= draftDao.selectDocumentListByDocMemNumber(documentSearchVO);
				break;
			}
			break;
		case 1://검색어 분류만 선택했을 때 (+1)
			switch(searchOption){
			case "doc_name":
				documentList= draftDao.selectDocumentListOnlyByDocName(documentSearchVO);
				break;
			case "doc_explanation":
				documentList= draftDao.selectDocumentListOnlyByDocExplanation(documentSearchVO);
				break;
			case "doc_mem_number":
				documentList= draftDao.selectDocumentListOnlyByDocMemNumber(documentSearchVO);
				break;
			case "문서등록일":
				documentList= draftDao.selectDocumentListOnlyByDocDate(documentSearchVO);
				break;
			}
			break;
		case 2://2 기안문서함만 선택했을 때(+2)
			documentList= draftDao.selectDocumentListOnlyByDraftBoxOption(documentSearchVO);
			break;
		case 3://3 둘다 선택 안 했을 때(+1+2)
			documentSearchVO.setDoc_name("");
			documentSearchVO.setDoc_explanation("");
			documentSearchVO.setDoc_mem_number("");
			documentSearchVO.setDoc_date("");
			documentList= draftDao.selectDocumentListOnlyBySearchText(documentSearchVO);
			break;
		}
			
		return documentList;
	}

	public Object getMenuDepartmentString() {
		StringBuffer menuString = new StringBuffer(2048);

		String[] sel_menu_id = null;

		String menu_id   = "";      //조직코드
		String menu_nm   = "";   //조직명
		String []menu_type  = null;   //조직Type

		long menu_level = 0;   // 조직Level이 아닌 조회된 계층의 Level
		int div_cnt   = 0;   // <div> Tag Count

		String javascript_1 = "";
		String javascript_2 = "";
		String javascript_3 = "expandsub('1'); \r";

		try{
			List<DepartmentVO> select = draftDao.draft_selectDepartmentList();
			System.out.println("------>"+select.size());
			sel_menu_id = new String[select.size()];

			int h = select.size();

			for(int i=0;i<h;i++){
				sel_menu_id[i] = ((DepartmentVO)select.get(i)).getDept_number();
			}      
			div_cnt++;

			menuString.append("<div id=PG0 nowrap> \r");

			int i=0;
			for(i=0;i<h;i++){
				if (i!=0) { 
					javascript_1 = "javascript:togglesub('"+i+"')";
					javascript_2 = "javascript:expandsub('"+i+"');goThere('"+menu_id+"')";
					if( ((DepartmentVO)select.get(i)).getDept_level() == menu_level ) {                       
						menuString.append("<span id=SG"+i+">"
											+ "<IMG SRC='/resources/images/tree/minus.gif' BORDER=0 ID=IMG"+i+">");
						menuString.append("	   <A HREF=\""+javascript_2+"\" ID=A"+i+" class=fldblue> "
													+ menu_nm 
										     +"</A>"
										+ "</span><br>\r");
						
					} else if ( ((DepartmentVO)select.get(i)).getDept_level() > menu_level ) {                      
						menuString.append("<span id=SG"+i+">"
											+ "<A HREF=\""+javascript_1+"\">"
													+ "<IMG SRC='/resources/images/tree/plus.gif' BORDER=0 ID=IMG"+i+">"
											+ "</A>" 
											+ "<A HREF=\""+javascript_2+"\" ID=A"+i+" class=fldblue> "
													+ menu_nm 
											+ "</A>"
										+ "</span><br>  \r ");
						menuString.append("<div id=PG"+i+" style='display:none;margin-left:15px;' nowrap> \r");
						div_cnt++;
						
					} else if ( ((DepartmentVO)select.get(i)).getDept_level() < menu_level ) {                      
						menuString.append("<span id=SG"+i+">"
											+ "<IMG SRC='/resources/images/tree/minus.gif' BORDER=0 ID=IMG"+i+">"
											+ "<A HREF=\""+javascript_2+"\" ID=A"+i+" class=fldblue> "
												+ menu_nm 
											+" </A>"
										+ "</span><br> \r");
						for (int j=0; j < (menu_level  - ((DepartmentVO)select.get(i)).getDept_level()); j++) {
							menuString.append("</div> \r");
							div_cnt--;
						}
					}
				}        
				menu_id   = ((DepartmentVO)select.get(i)).getDept_number(); 
				menu_nm   = ((DepartmentVO)select.get(i)).getDept_name(); 
				menu_level  = ((DepartmentVO)select.get(i)).getDept_level(); 
//				menu_type  = ((DepartmentVO)select.get(i)).getOrder2(); 
			} 
			//
			javascript_1 = "javascript:togglesub('"+i+"')";
			javascript_2 = "javascript:expandsub('"+i+"');goThere('"+menu_id+"')";

			menuString.append("<span id=SG"+i+">"
								+ "<IMG SRC='/resources/images/tree/minus.gif' BORDER=0 ID=IMG"+i+">"
								+ "<A HREF=\""+javascript_2+"\" ID=A"+i+" class=fldblue> "
									+ menu_nm 
								+ "</A>"
							+ "</span><br> \r");

			for (int j=0; j < div_cnt; j++)  {
				menuString.append("</div> \r");
			}
			return menuString;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	//부서번호로 해당 부서의 직원을 선택하는 메서드 // 결재라인 선택에서 사용
	public List<Map> findMemberByDepartment(String dept_number,Model model) {
		List<MemberVO> memberList = draftDao.dreat_selectMemberListByDepartment(dept_number);
		
		List<Map> memberJsonList = new ArrayList<Map>();
		
		for (MemberVO member : memberList) {
			Map<String,String> memberjsonMap = new HashMap<String,String>();
			memberjsonMap.put("mem_dept_name", member.getDept_name());
			memberjsonMap.put("mem_position_number", member.getPosition_name());
			memberjsonMap.put("mem_name", member.getMem_name());
			memberjsonMap.put("mem_number", member.getMem_number());
			memberjsonMap.put("mem_dept_number", member.getMem_dept_number());
			
			memberJsonList.add(memberjsonMap);
		}
		System.out.println(dept_number);
		model.addAttribute("dept_number",dept_number);
		return memberJsonList;
	}
	
	public List<Map> searchMemberByMemberName(String searchText,String dept_number) {
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("searchText", searchText);
		paramMap.put("dept_number", dept_number);
		
		List<MemberVO> memberList = draftDao.dreat_selectMemberListByDepartmentMemberName(paramMap);
		
		List<Map> memberJsonList = new ArrayList<Map>();
		
		for (MemberVO member : memberList) {
			Map<String,String> memberjsonMap = new HashMap<String,String>();
			memberjsonMap.put("mem_dept_name", member.getDept_name());
			memberjsonMap.put("mem_position_number", member.getPosition_name());
			memberjsonMap.put("mem_name", member.getMem_name());
			memberjsonMap.put("mem_number", member.getMem_number());
			
			memberJsonList.add(memberjsonMap);
		}
		return memberJsonList;
	}

	public void searchMemberByMemberId(String mem_id,Model model,String doc_number) {
		MemberVO member = draftDao.draft_selectMemberByMemberId(mem_id);
		
		//품의번호 setting
		DateFormat dfForYear = new SimpleDateFormat("yyyy");
		String thisYear = dfForYear.format(new Date());
		String seqNext = (draftDao.draft_selectSeqNumber())+"";
		String ea_number = member.getDept_name()+"-"+thisYear+"-"+seqNext;
		String param_ea_number = member.getDept_name()+"-"+thisYear+"-";
		model.addAttribute("ea_number",ea_number);
		model.addAttribute("param_ea_number",param_ea_number);
		
		//작성일자 setting
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(new Date());
		model.addAttribute("ea_writedate",today);

		//기안자 정보 (기안부서, 기안자) setting
		model.addAttribute("member",member);
		
		//문서 번호(종류) setting
		model.addAttribute("doc_number", doc_number);
	}

	public void submitApproval(Model model, Electronic_ApprovalVO eaVO,
			String ea_content, ApprovalParamVO apVO,String param_ea_number) {
		//eaVO 등록
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date ea_startdate=null;
		Date ea_enddate=null;
		Date ea_writedate=null;
		try {
			ea_startdate = transFormat.parse(apVO.getParam_ea_startdate());
			ea_enddate = transFormat.parse(apVO.getParam_ea_enddate());
			ea_writedate = transFormat.parse(apVO.getParam_ea_writedate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		eaVO.setEa_content(ea_content);
		eaVO.setEa_startdate(ea_startdate);
		eaVO.setEa_enddate(ea_enddate);
		eaVO.setEa_writedate(ea_writedate);
		draftDao.draft_insertApproval(eaVO);
		
		//스텝등록
		String approvalMember1 = "";
		String approvalMember2 = "";
		String approvalMember3 = "";
		String approvalMember4 = "";
		String approvalMember5 = "";
		String agreementMember1 = "";
		String agreementMember2 = "";
		String agreementMember3 = "";
		String agreementMember4 = "";
		String agreementMember5 = "";
		String implementMembers ="";
		String referenceMembers ="";
		Map<String, String> paramMap = null;
		//합의자
		if(apVO.getAgreementMember1()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember1 =apVO.getAgreementMember1();
			paramMap.put("ast_mem_number", agreementMember1);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast1");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "1");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getAgreementMember2()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember2 =apVO.getAgreementMember2(); 
			paramMap.put("ast_mem_number", agreementMember2);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast2");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "2");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getAgreementMember3()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember3 =apVO.getAgreementMember3();
			paramMap.put("ast_mem_number", agreementMember3);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast3");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "3");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getAgreementMember4()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember4 =apVO.getAgreementMember4();
			paramMap.put("ast_mem_number", agreementMember4);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast4");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "4");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getAgreementMember5()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember5 =apVO.getAgreementMember5();
			paramMap.put("ast_mem_number", agreementMember1);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast5");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "5");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		int priority = Integer.parseInt(paramMap.get("ast_priority"));
		//결재자
		if(apVO.getApprovalMember1()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember1 =apVO.getApprovalMember1();
			paramMap.put("ast_mem_number", agreementMember1);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast"+(priority+1));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+1)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getApprovalMember2()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember2 =apVO.getApprovalMember2();
			paramMap.put("ast_mem_number", agreementMember2);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast"+(priority+2));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+2)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getApprovalMember3()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember3 =apVO.getApprovalMember3();
			paramMap.put("ast_mem_number", agreementMember3);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast"+(priority+3));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+3)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getApprovalMember4()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember4 =apVO.getApprovalMember4();
			paramMap.put("ast_mem_number", agreementMember4);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast"+(priority+4));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+4)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getApprovalMember5()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember5 =apVO.getApprovalMember5();
			paramMap.put("ast_mem_number", agreementMember5);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast"+(priority+5));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+5)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		priority = Integer.parseInt(paramMap.get("ast_number").substring(3));
		//시행자
		if(apVO.getImplementMembers()!=null){
			paramMap = new HashMap<String, String>(); 
			implementMembers = apVO.getImplementMembers();
			paramMap.put("ast_mem_number", implementMembers);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast"+(priority+1));
			paramMap.put("ast_al_number", "C");
			paramMap.put("ast_priority", "0");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		priority = Integer.parseInt(paramMap.get("ast_number").substring(3));
		//참조자
		if(apVO.getReferenceMembers()!=null){
			paramMap = new HashMap<String, String>(); 
			referenceMembers = apVO.getReferenceMembers();
			paramMap.put("ast_mem_number", referenceMembers);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast"+(priority+1));
			paramMap.put("ast_al_number", "D");
			paramMap.put("ast_priority", "0");
			draftDao.draft_insertApprovalStep(paramMap);
		}	
	}

}
