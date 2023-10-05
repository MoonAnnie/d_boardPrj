package com.danal.djmoon.common;

public class Pagination {

//	public static PageVo getPageVo(int listCount, int currentPage, int pageLimit, int boardLimit) {
//
//		int maxPage = (int) Math.ceil((double) listCount / boardLimit);
//		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
//		int endPage = startPage + pageLimit - 1;
//		if (maxPage < endPage) {
//			endPage = maxPage;
//		}
//
//		PageVo pv = new PageVo();
//		pv.setListCount(listCount);
//		pv.setCurrentPage(currentPage);
//		pv.setPageLimit(pageLimit);
//		pv.setBoardLimit(boardLimit);
//		pv.setMaxPage(maxPage);
//		pv.setStartPage(startPage);
//		pv.setEndPage(endPage);
//
//		return pv;
//	}
	
	//크롤링 적용 후 수정된 코드
    public static PageVo getPageVo(int listCount, int currentPage) {
        PageVo pageVo = new PageVo();

        //한 페이지당 크롤링 결과 갯수를 10개로 설정
        int boardLimit = 100;

        int maxPage = (int) Math.ceil((double) listCount / boardLimit);
        int startPage = (currentPage - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;

        if (maxPage < endPage) {
            endPage = maxPage;
        }

        pageVo.setListCount(listCount);
        pageVo.setMaxPage(maxPage);
        pageVo.setCurrentPage(currentPage);
        pageVo.setStartPage(startPage);
        pageVo.setEndPage(endPage);

        return pageVo;
    }

}
