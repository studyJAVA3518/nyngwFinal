package com.nyngw.electronicApproval.approvalLineManagement.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.DepartmentVO;
import com.nyngw.electronicApproval.approvalLineManagement.dao.ApprovalLineManagementDaoImpl;

@Service
public class ApprovalLineManagementServiceImpl implements
		ApprovalLineManagementService {

	
	@Autowired
	private ApprovalLineManagementDaoImpl approvalLineManagementDao;  
	
	
	public StringBuffer getMenuDepartmentString() {

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
			List<DepartmentVO> select = approvalLineManagementDao.EA_selectDepartmentList();
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
}
