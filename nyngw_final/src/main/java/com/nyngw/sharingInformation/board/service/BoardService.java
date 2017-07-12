package com.nyngw.sharingInformation.board.service;

import java.util.List;

import com.nyngw.dto.BoardListViewVO;
import com.nyngw.dto.BoardVO;

public interface BoardService {
	public List<BoardVO> selectList();
	public BoardListViewVO selectBoardList(int pageNumber, String val, String index);
	public BoardVO selectBoard(String board_number);
	public String selectMax();
	public void boardUpdate(BoardVO board);
	public void boardInsert(BoardVO board);
}
