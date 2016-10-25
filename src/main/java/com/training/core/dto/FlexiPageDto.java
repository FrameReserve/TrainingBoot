package com.training.core.dto;


public class FlexiPageDto {

	public static final Integer MAX_PAGE_SIZE=3000;
	public static final Integer SHORT_PAGE_SIZE=5;
	
	public FlexiPageDto() {
		super();
	}

	public FlexiPageDto(Integer page, Integer rp) {
		super();
		this.page = page;
		this.rp = rp;
	}

	public FlexiPageDto(Integer page, Integer rp, String sortName) {
		super();
		this.page = page;
		this.rp = rp;
		this.sortName = sortName;
	}

	public FlexiPageDto(Integer page, Integer rp, String sortName, String sortOrder) {
		super();
		this.page = page;
		this.rp = rp;
		this.sortName = sortName;
		this.sortOrder = sortOrder;
	}
	
	public static FlexiPageDto createMaxPageDto(){
		FlexiPageDto flexiPageDto=new FlexiPageDto();
		flexiPageDto.setRp(MAX_PAGE_SIZE).setPage(1);
		return flexiPageDto;
	}
	public static FlexiPageDto generateFlexiPageDto(Integer page, Integer rp, String orderBy) {
		FlexiPageDto flexiPageDto = new FlexiPageDto(page, rp);
		if (null != orderBy && "" != orderBy.trim()) {
			String[] orderBys = orderBy.split("_");
			flexiPageDto.setSortName(orderBys[0]);
			flexiPageDto.setSortOrder(orderBys[1]);
		}
		return flexiPageDto;
	}

	/**
	 * 当前页
	 */
	private Integer page;
	
	/**
	 * 每页显示，默认:10
	 */
	private Integer rp = 10;
	
	/**
	 * 总记录数
	 */
	private Integer rowCount;
	
	/**
	 * 排序字段
	 */
	private String sortName;
	
	/**
	 * 排序(asc/desc)
	 */
	private String sortOrder = "desc";
	
	public static final String SORTORDER_ACS = "asc";
	
	/**
	 * 数据开始坐标，Mysql从0开始
	 */
	public Integer getOffset(){
		return (this.getPage()-1)*this.getRp();
	}
	
	/**
	 * 总页数
	 */
	public Integer getTotalPage() {
		if (null == rowCount) {
			return 0;
		}
		int totalPage = (rowCount / rp);
		int remainder = rowCount % rp;
		if (rowCount > 0 && totalPage == 0) {
			totalPage = 1;
			return totalPage;
		}
		if (remainder > 0) {
			totalPage++;
			return totalPage;
		}
		return totalPage;		
	}

	// -------------------------- getter and setter -----------------------------
	public Integer getRp() {
		return rp;
	}

	public FlexiPageDto setRp(Integer rp) {
		this.rp = rp;
		return this;
	}

	public Integer getPage() {
		return page;
	}

	public FlexiPageDto setPage(Integer page) {
		this.page = page;
		return this;
	}

	public String getSortName() {
		return sortName;
	}

	public FlexiPageDto setSortName(String sortName) {
		this.sortName = sortName;
		return this;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public FlexiPageDto setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public FlexiPageDto setRowCount(Integer rowCount) {
		
		this.rowCount = rowCount;
		return this;
	}
	
	public String getSortString(){
		
		if (null == sortName) {
			return null;
		}
		String[] fields = this.getSortName().split("_");
		String[] fieldsorts = this.getSortOrder().split("_");
		if(fields.length!=fieldsorts.length){
			throw new RuntimeException("排序规则不一致");
		}
		
		String sql = "";
		for(int index=0;index<fields.length;index++){
			sql = sql+" "+fields[index]+" "+fieldsorts[index];
		}
		
		return sql;
	}

}
