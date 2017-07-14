package com.nyngw.sharingInformation.noticeMatter.dao;

import java.util.List;

import com.nyngw.dto.BoardVO;
import com.nyngw.dto.Board_SelectVO;

public interface NoticeMatterDao {
	public List<BoardVO> selectNoticeMatterList(int firstRow, int endRow, Board_SelectVO select);
	public int selectNoticeMatterCount();
	public int boardNoticeMatterCount(Board_SelectVO select);
	public void noticeMatterInsert(BoardVO board);
	public String NoticeMatterMax();
}
