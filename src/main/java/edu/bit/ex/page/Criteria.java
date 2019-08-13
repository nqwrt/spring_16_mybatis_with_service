package edu.bit.ex.page;

public class Criteria {
    private int page; // ������ ������
    private int perPageNum; // �������� ������ ��������
	
    private int startNum; //���۹�ȣ
    private int endNum;   //����ȣ
    
    public int getStartNum() {
    	
    	if(page == 1)
    		startNum = 1;
    	else{
    		startNum = page * perPageNum;
    	}	
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		endNum  = getStartNum() + 10;
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}    
    
    // limit �������� ���� �κп� �ʿ��� ���� ��ȯ(mybatis���� ���)
    public int getPageStart(){
        return (this.page -1) * this.perPageNum;
		// RowBounds �� �Ű������� ���� �����ϴ� �޼ҵ�
		// RouwBounds �� start index�� 0���� �����ϹǷ�
		// 1������ : 1-1 * 10 = 0 ->>
		// 2������ : 2-1 * 10 = 10 ->>
    }
     
    public Criteria() { // ���� default �����ڷ� �⺻ ��ü ������ �ʱⰪ�� �����Ѵ�. (1������, 10����)
        this.page = 1;  // ����ڰ� �������� ������ �⺻ 1
        this.perPageNum = 10; // �������� �⺻ 10���� ����ϵ��� ����
    }
     
 
    // getter setter
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        if ( page <= 0 ){
            // �������� 1���������������� 0���� �۰ų� �������� ��� ������ ù��° �������� �����ǵ��� ���ش�.
            this.page = 1;
        }else{
            this.page = page;
        }
    }
    public int getPerPageNum() {
         
        return perPageNum;
    }
    
    public void setPerPageNum(int perPageNum) {
        if ( perPageNum <= 0 || perPageNum > 100 ) {
            this.perPageNum = 10;
        }else {
            this.perPageNum = perPageNum;
        }
    }
 
    @Override
    public String toString() {
        return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
    }
}