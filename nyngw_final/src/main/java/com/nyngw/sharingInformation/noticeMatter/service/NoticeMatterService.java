package com.nyngw.sharingInformation.noticeMatter.service;

import com.nyngw.dto.BoardListViewVO;
import com.nyngw.dto.BoardVO;
import com.nyngw.dto.Board_SelectVO;

public interface NoticeMatterService {
	public BoardListViewVO selectNoticeMatterList(int pageNumber, Board_SelectVO select);
	public void noticeMatterInsert(BoardVO board);
	public String noticeMatterMax();
	public BoardVO selectNoticeMatte(String board_number);
}
