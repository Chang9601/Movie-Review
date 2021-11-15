package com.chang.recmv.model;

public class Paging {
	// 페이지 하단부 페이징 처리
	private int beginNumOfPage; // 시작 페이지 번호
	private int endNumOfPage; // 끝 페이지 번호
	private boolean pre, nxt; // 이전, 다음
	private int numOfItems; // 전체 항목의 개수
	private int numOfPagesPerPage = 5;// 페이지 당 표시할 페이지 번호의 개수
	
	// 페이지에 출력되는 항목의 개수 페이징 처리
	private Criteria cri;
	
	// 페이징 처리에 필요한 정보 계산
	public Paging(Criteria cri, int numOfItems) {
		this.cri = cri;
		this.numOfItems = numOfItems;
		
		endNumOfPage = (int)Math.ceil(this.cri.getCurrentPageNum()/(double)numOfPagesPerPage)*numOfPagesPerPage;
		beginNumOfPage = endNumOfPage-numOfPagesPerPage+1;
		
		int realEndNumOfPage = (int)Math.ceil(this.numOfItems/(double)this.cri.getNumOfItemsPerPage());
		
		endNumOfPage = (endNumOfPage > realEndNumOfPage) ? realEndNumOfPage : endNumOfPage;
		
		pre = beginNumOfPage > 1;
		nxt = endNumOfPage < realEndNumOfPage;
	}

	public int getBeginNumOfPage() {
		return beginNumOfPage;
	}
	public void setBeginNumOfPage(int beginNumOfPage) {
		this.beginNumOfPage = beginNumOfPage;
	}
	public int getEndNumOfPage() {
		return endNumOfPage;
	}
	public void setEndNumOfPage(int endNumOfPage) {
		this.endNumOfPage = endNumOfPage;
	}
	public boolean isPre() {
		return pre;
	}
	public void setPre(boolean pre) {
		this.pre = pre;
	}
	public boolean isNxt() {
		return nxt;
	}
	public void setNxt(boolean nxt) {
		this.nxt = nxt;
	}
	public int getNumOfItems() {
		return numOfItems;
	}
	public void setNumOfItems(int numOfItems) {
		this.numOfItems = numOfItems;
	}
	public int getNumOfPagesPerPage() {
		return numOfPagesPerPage;
	}
	public void setNumOfPagesPerPage(int numOfPagesPerPage) {
		this.numOfPagesPerPage = numOfPagesPerPage;
	}
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	@Override
	public String toString() {
		return "PageVO [beginNumOfPage=" + beginNumOfPage + ", endNumOfPage=" + endNumOfPage + ", pre=" + pre + ", nxt="
				+ nxt + ", numOfItems=" + numOfItems + ", numOfPagesPerPage=" + numOfPagesPerPage + ", cri=" + cri
				+ "]";
	}
}