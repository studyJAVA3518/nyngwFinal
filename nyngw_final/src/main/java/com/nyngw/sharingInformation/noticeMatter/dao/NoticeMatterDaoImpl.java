package com.nyngw.sharingInformation.noticeMatter.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.BoardVO;
import com.nyngw.dto.Board_SelectVO;

@Repository
public class NoticeMatterDaoImpl implements NoticeMatterDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BoardVO> selectNoticeMatterList(int firstRow, int endRow,
			Board_SelectVO select) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<BoardVO> boardList = (ArrayList<BoardVO>)sqlSession.selectList("selectNoticeMatterList",select,rowBounds);
		return boardList;
	}

	@Override
	public int selectNoticeMatterCount() {
		int result =(Integer) sqlSession.selectOne("selectNoticeMatterCount");
		return result;
	}

	@Override
	public int boardNoticeMatterCount(Board_SelectVO select) {
		int result =(Integer) sqlSession.selectOne("boardNoticeMatterCount",select);
		return result;
	}

	@Override
	public void noticeMatterInsert(BoardVO board) {
		sqlSession.insert("noticeMatterInsert", board);
	}

	@Override
	public String NoticeMatterMax() {
		String maxnum = (String) sqlSession.selectOne("NoticeMatterMax");
		return maxnum;
	}
	//
	@Override
	public BoardVO selectNoticeMatte(String board_number) {
		BoardVO board = (BoardVO) sqlSession.selectOne("selectNoticeMatte",board_number);
		return board;
	}

	@Override
	public void updateNoticeMatterCountPlus(String board_number) {
		sqlSession.update("updateNoticeMatterCountPlus", board_number);
	}
}
