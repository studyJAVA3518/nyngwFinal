package com.nyngw.sharingInformation.board.service;

import java.util.List;

import com.nyngw.dto.BoardListViewVO;
import com.nyngw.dto.BoardVO;
import com.nyngw.dto.Board_CommentVO;
import com.nyngw.dto.Board_SelectVO;

public interface BoardService {
	public List<BoardVO> selectList();
	public BoardListViewVO selectBoardList(int pageNumber, Board_SelectVO select);
	public BoardVO selectBoard(String board_number);
	public String selectMax();
	public void boardUpdate(BoardVO board);
	public void boardInsert(BoardVO board);
	public void boardDelete(String board_number);
	
	//댓글
	public void answerWrite(Board_CommentVO comment);
	public List<Board_CommentVO> answerSelectList(String board_number);
	public void answerDelete(String comment_number);
	public void answerUpdate(Board_CommentVO comment);
}
