package com.practice.util;

public class PageVO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int pageNum;
	private int amount;
	private int total;
	private int realEnd;
	
	public PageVO(int pageNum, int amount, int total) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.total = total;
		
		this.endPage = (int)Math.ceil( this.pageNum / (double)10 ) * 10;
		
		this.startPage = this.endPage - 10 + 1;
		
		this.realEnd = (int)Math.ceil(this.total / (double)this.amount);
		
		if(this.endPage > this.realEnd) {
			this.endPage = this.realEnd;
		}
		
		this.prev = this.startPage > 1;
		
		this.next = this.realEnd > this.endPage;
		
	}

	@Override
	public String toString() {
		return "PageVO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", pageNum=" + pageNum + ", amount=" + amount + ", total=" + total + ", realEnd=" + realEnd + "]";
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRealEnd() {
		return realEnd;
	}

	public void setRealEnd(int realEnd) {
		this.realEnd = realEnd;
	}
	
	
	

}
