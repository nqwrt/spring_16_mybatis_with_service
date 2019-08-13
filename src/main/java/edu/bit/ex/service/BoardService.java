package edu.bit.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.bit.ex.page.Criteria;
import edu.bit.ex.vo.BoardVO;

@Service
public class BoardService {

	//@Autowired
	//private SqlSessionTemplate sqlSession;	

	@Inject
	SqlSessionTemplate sqlSession;
	
    public List<BoardVO> selectBoardList() throws Exception {
		return sqlSession.selectList("selectBoardList");
    }
    
    public List<BoardVO> selectBoardListPage(Criteria criteria)  {
		return sqlSession.selectList("selectBoardListPage",criteria);
    }
    
    public int selectTotal() throws Exception {
		return sqlSession.selectOne("selectTotal");
    }
    
    public void insertBoard(BoardVO param) throws Exception {
		sqlSession.insert("insertBoard", param);
    }
    
    public void updateShape(BoardVO param) throws Exception {
		sqlSession.insert("updateShape", param);
    }
    
    public void insertReply(BoardVO param) throws Exception {
		sqlSession.insert("insertReply", param);
    }
    /*
    public void updateBoard(BoardVO param) throws Exception {
		sqlSession.insert("updateBoard1", param);
    }
     */
    public BoardVO selectBoardOne(String bId) throws Exception {
		return sqlSession.selectOne("selectBoardOne", bId);
    }

    public void deleteBoardOne(String bId) throws Exception {
		sqlSession.delete("deleteBoardOne", bId);
    }
    
    
}
