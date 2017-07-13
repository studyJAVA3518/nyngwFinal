package com.nyngw.sharingInformation.board.dao;

import java.util.List;

import com.nyngw.dto.BoardVO;
import com.nyngw.dto.Board_SelectVO;

public interface BoardDao {
	public List<BoardVO> selectList();
	public List<BoardVO> selectBoardList(int firstRow, int endRow, Board_SelectVO select);
//	public List<BoardVO> selectTitleList(int firstRow, int endRow, String val);
//	public List<BoardVO> selectWriteList(int firstRow, int endRow, String val);
	public int selectBoardCount();
	public BoardVO selectBoard(String board_number);
	public String selectMax();
	public void boardUpdate(BoardVO board);
	public void boardInsert(BoardVO board);
	public int boardSelectCount(Board_SelectVO select);
	public void boardDelete(String board_number);
}
