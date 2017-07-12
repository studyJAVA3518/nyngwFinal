package com.nyngw.sharingInformation.board.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.BoardListViewVO;
import com.nyngw.dto.BoardVO;
import com.nyngw.sharingInformation.board.dao.BoardDaoImpl;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDaoImpl boardDao;
	private static final int BOARD_COUNT_PER_PAGE = 5;

	@Override
	public List<BoardVO> selectList() {
		List<BoardVO> board = boardDao.selectList();
		return board;
	}

	@Override
	public BoardListViewVO selectBoardList(int pageNumber, String val, String index ) {
		int currentPageNumber = pageNumber;
			
			int boardTotalCount = boardDao.selectBoardCount();

			List<BoardVO> boardList = null;
			int firstRow = 0;
			int endRow = 0;
			if (boardTotalCount > 0) {
				firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
				endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
				boardList = boardDao.selectBoardList(firstRow, endRow);
				if(!val.equals("")){
					if(index.equals("board_mem_number")){
						boardList = boardDao.selectWriteList(firstRow, endRow, val);
						System.out.println(val+"작성자");
					}else{
						boardList = boardDao.selectTitleList(firstRow, endRow, val);
						System.out.println(val+"제목");
					}
				}
			} else {
				currentPageNumber = 0;
				boardList = Collections.emptyList();
			}
			return  new BoardListViewVO(boardList, boardTotalCount,
					currentPageNumber, BOARD_COUNT_PER_PAGE, firstRow, endRow);
	}

	@Override
	public BoardVO selectBoard(String board_number) {
		BoardVO board = boardDao.selectBoard(board_number);
		return board;
	}

	@Override
	public void boardUpdate(BoardVO board) {
		Date dt = new Date();
		board.setBoard_code_number("code7");
		board.setBoard_date(dt);
		boardDao.boardUpdate(board);
	}

	@Override
	public void boardInsert(BoardVO board) {
		Date dt = new Date();
		board.setBoard_number(selectMax());
		board.setBoard_code_number("code7");
		board.setBoard_date(dt);
		board.setBoard_count("0");
		board.setBoard_mem_number("mem1");
		boardDao.boardInsert(board);
	}

	@Override
	public String selectMax() {
		String maxnum = boardDao.selectMax();
		return maxnum;
	}
}
