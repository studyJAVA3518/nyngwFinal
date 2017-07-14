package com.nyngw.sharingInformation.noticeMatter.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.BoardListViewVO;
import com.nyngw.dto.BoardVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.sharingInformation.noticeMatter.dao.NoticeMatterDaoImpl;

@Service
public class NoticeMatterServiceImpl implements NoticeMatterService {
	@Autowired
	private NoticeMatterDaoImpl noticeMatterDao;
	private static final int BOARD_COUNT_PER_PAGE = 5;
	@Override
	public BoardListViewVO selectNoticeMatterList(int pageNumber,
			Board_SelectVO select) {
		int currentPageNumber = pageNumber;
		int boardTotalCount = noticeMatterDao.selectNoticeMatterCount();
			List<BoardVO> boardList = null;
			int firstRow = 0;
			int endRow = 0;
			if (boardTotalCount > 0) {
				firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
				endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
				boardList = noticeMatterDao.selectNoticeMatterList(firstRow, endRow, select);
				if(select.getVal()!=null && !select.getVal().equals("")){
					boardTotalCount = noticeMatterDao.boardNoticeMatterCount(select);
				}
			} else {
				currentPageNumber = 0;
				boardList = Collections.emptyList();
			}
			return  new BoardListViewVO(boardList, boardTotalCount,
					currentPageNumber, BOARD_COUNT_PER_PAGE, firstRow, endRow);
	}
	@Override
	public void noticeMatterInsert(BoardVO board) {
		Date dt = new Date();
		board.setBoard_number(noticeMatterMax());
		board.setBoard_code_number("code11");
		board.setBoard_date(dt);
		board.setBoard_count("1");
		board.setBoard_mem_number("mem1");
		noticeMatterDao.noticeMatterInsert(board);
	}
	@Override
	public String noticeMatterMax() {
		String maxnum = noticeMatterDao.NoticeMatterMax();
		return maxnum;
	}
}
