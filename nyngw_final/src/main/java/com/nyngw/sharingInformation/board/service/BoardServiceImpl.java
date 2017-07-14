package com.nyngw.sharingInformation.board.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.BoardListViewVO;
import com.nyngw.dto.BoardVO;
import com.nyngw.dto.Board_CommentVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.sharingInformation.board.dao.BoardDaoImpl;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDaoImpl boardDao;
	private static final int BOARD_COUNT_PER_PAGE = 5;

	/**
	 * 얘는 안쓰는애
	 */
	@Override
	public List<BoardVO> selectList() {
		List<BoardVO> board = boardDao.selectList();
		return board;
	}
	
	/**
	 * 게시판 리스트 및 검색을 동시에 해주는 메서드
	 */
	@Override
	public BoardListViewVO selectBoardList(int pageNumber, Board_SelectVO select) {
		int currentPageNumber = pageNumber;
		int boardTotalCount = boardDao.selectBoardCount();
			List<BoardVO> boardList = null;
			int firstRow = 0;
			int endRow = 0;
			if (boardTotalCount > 0) {
				firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
				endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
				boardList = boardDao.selectBoardList(firstRow, endRow, select);
				if(select.getVal()!=null && !select.getVal().equals("")){
					boardTotalCount = boardDao.boardSelectCount(select);
				}
			} else {
				currentPageNumber = 0;
				boardList = Collections.emptyList();
			}
			return  new BoardListViewVO(boardList, boardTotalCount,
					currentPageNumber, BOARD_COUNT_PER_PAGE, firstRow, endRow);
	}

	/**
	 * 디테일 상세페이지
	 */
	@Override
	public BoardVO selectBoard(String board_number) {
		boardDao.updateBoardCountPlus(board_number);
		BoardVO board = boardDao.selectBoard(board_number);
		return board;
	}
	
	/**
	 * 게시물 업데이트
	 */
	@Override
	public void boardUpdate(BoardVO board) {
		Date dt = new Date();
		board.setBoard_date(dt);
		boardDao.boardUpdate(board);
	}
	
	/**
	 * 게시물등록
	 */
	@Override
	public void boardInsert(BoardVO board) {
		Date dt = new Date();
		board.setBoard_number(selectMax());
		board.setBoard_code_number("code7");
		board.setBoard_date(dt);
		board.setBoard_count("0");
		boardDao.boardInsert(board);
	}

	@Override
	public String selectMax() {
		String maxnum = boardDao.selectMax();
		return maxnum;
	}

	@Override
	public void boardDelete(String board_number) {
		boardDao.boardDelete(board_number);
	}
	
	
	
	//댓글
	@Override
	public void answerWrite(Board_CommentVO comment) {
		boardDao.answerWrite(comment);
	}

	@Override
	public List<Board_CommentVO> answerSelectList(String board_number) {
		List<Board_CommentVO> comment = boardDao.answerSelectList(board_number);
		return comment;
	}

	@Override
	public void answerDelete(String comment_number) {
		boardDao.answerDelete(comment_number);
	}

	@Override
	public void answerUpdate(Board_CommentVO comment) {
		boardDao.answerUpdate(comment);
	}
}
