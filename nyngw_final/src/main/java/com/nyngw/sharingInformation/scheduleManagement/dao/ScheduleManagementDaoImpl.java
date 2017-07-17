package com.nyngw.sharingInformation.scheduleManagement.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.ScheduleVO;

@Repository
public class ScheduleManagementDaoImpl implements ScheduleManagementDao {
	@Autowired
	private SqlSession sqlSession;

	public List<ScheduleVO> SI_selectAllSchedule(String sc_code_number) {
		return sqlSession.selectList("SI_selectAllSchedule",sc_code_number);
	}
	
	public ScheduleVO SI_selectSchedule(String sc_number) {
		return (ScheduleVO)sqlSession.selectOne("SI_selectSchedule",sc_number);
	}
	
	public void SI_deleteSchedule(String sc_number) {
		sqlSession.delete("SI_deleteSchedule",sc_number);
	}

	public void SI_updateSchedule(ScheduleVO schedule) {
		sqlSession.insert("SI_updateSchedule",schedule);
	}
	
	public void SI_insertSchedule(ScheduleVO schedule) {
		sqlSession.delete("SI_insertSchedule",schedule);
	}
	
	public List<ScheduleVO> SI_selectMemberSchedule(String sc_mem_number){
		return sqlSession.selectList("SI_selectMemberSchedule", sc_mem_number);
	}

}
