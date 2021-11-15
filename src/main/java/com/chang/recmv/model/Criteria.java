package com.chang.recmv.model;

public class Criteria {
	private int currentPageNum; // 현재 페이지 번호
	private int numOfItemsPerPage; // 페이지 당 출력할 항목의 개수
	private int beginNumOfItems; // DB 시작번호

	
	public Criteria(int currentPageNum, int numOfItemsPerPage) {
		this.currentPageNum = currentPageNum;
		this.numOfItemsPerPage = numOfItemsPerPage;
	}
	public Criteria() {
		this(1,5);
	}
	
	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = (currentPageNum <= 0) ? 1 : currentPageNum;
	}
	public void setNumOfItemsPerPage(int numOfItemsPerPage) {
		this.numOfItemsPerPage = (numOfItemsPerPage <= 0 || numOfItemsPerPage > 100) ? 10 : numOfItemsPerPage;
	}
	public void setBeginNumOfItems(int beginNumOfItems) {
		this.beginNumOfItems = beginNumOfItems;
	}
		
	// mapper.xml에서 사용
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	public int getNumOfItemsPerPage() {
		return numOfItemsPerPage;
	}
	public int getBeginNumOfItems() {
		return (currentPageNum-1)*numOfItemsPerPage;
	}
	
	
	
	@Override
	public String toString() {
		return "Criteria [currentPageNum=" + currentPageNum + ", numOfItemsPerPage=" + numOfItemsPerPage
				+ ", beginNumOfItems=" + beginNumOfItems + "]";
	}
}