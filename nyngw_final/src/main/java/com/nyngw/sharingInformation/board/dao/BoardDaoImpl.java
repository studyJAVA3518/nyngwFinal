package com.nyngw.sharingInformation.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.BoardVO;
import com.nyngw.dto.Board_CommentVO;
import com.nyngw.dto.Board_SelectVO;

@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 안쓰는것
	 */
	@Override
	public List<BoardVO> selectList() {
		List<BoardVO> boardList = null;
		boardList = sqlSession.selectList("selectList");
		return boardList;
	}
	
	/**
	 * 게시물 리스트 및 검색 사용 메서드
	 */
	@Override
	public List<BoardVO> selectBoardList(int firstRow, int endRow, Board_SelectVO select) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<BoardVO> boardList = (ArrayList<BoardVO>)sqlSession.selectList("selectBoardList",select,rowBounds);
		return boardList;
	}
	
	/**
	 * 게시물 갯수 구하는 메서드
	 */
	@Override
	public int selectBoardCount() {
		int result =(Integer) sqlSession.selectOne("selectBoardCount");
		return result;
	}
	
	/**
	 * 디테일 상세페이지 가려고 하나만 검색
	 */
	@Override
	public BoardVO selectBoard(String board_number) {
		BoardVO board = (BoardVO) sqlSession.selectOne("selectBoard",board_number);
		return board;
	}
	
	/**
	 * 게시물 업데이트 메서드
	 */
	@Override
	public void boardUpdate(BoardVO board) {
		sqlSession.update("boardUpdate", board);
	}
	
	/**
	 * 게시물 등록 메서드
	 */
	@Override
	public void boardInsert(BoardVO board) {
		sqlSession.insert("boardInsert", board);
	}

	@Override
	public String selectMax() {
		String maxnum = (String) sqlSession.selectOne("selectMax");
		return maxnum;
	}
	
	/**
	 * 게시물 검색 갯수 구하는 메서드
	 */
	@Override
	public int boardSelectCount(Board_SelectVO select) {
		int result =(Integer) sqlSession.selectOne("boardSelectCount",select);
		return result;
	}

	@Override
	public void boardDelete(String board_number) {
		sqlSession.delete("boardDelete", board_number);
	}

	//댓글
	
	@Override
	public void answerWrite(Board_CommentVO comment) {
		sqlSession.insert("answerWrite", comment);
	}

	@Override
	public List<Board_CommentVO> answerSelectList(String board_number) {
		List<Board_CommentVO> comment = sqlSession.selectList("answerSelectList", board_number);
		return comment;
	}

	@Override
	public void answerDelete(String comment_number) {
		sqlSession.delete("answerDelete", comment_number);
	}
}
