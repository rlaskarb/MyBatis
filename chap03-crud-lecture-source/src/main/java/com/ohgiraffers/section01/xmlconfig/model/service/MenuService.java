package com.ohgiraffers.section01.xmlconfig.model.service;

import com.ohgiraffers.section01.xmlconfig.model.dao.MenuDAO;
import com.ohgiraffers.section01.xmlconfig.model.dto.MenuDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section01.xmlconfig.common.Template.getSqlSession;

public class MenuService {

    /* comment.
    *   Service 계층의 역할
    *   - 비즈니스 로직(하나의 일련의 과정 ex) 쿠팡에서 메뉴 주문)
    *   - SqlSession 생성 -> DB 접속 준비
    *   - DAO(데이터베이스 접근 객체) 의 메소드 호출
    *   - 트렌젝션(commit, rollback) 제어
    *   - SqlSession 닫기
    *  */

    private final MenuDAO menuDAO;

    public MenuService() {
        this.menuDAO = new MenuDAO();
    }

    public List<MenuDTO> selectAllMenu() {

        SqlSession sqlSession = getSqlSession();

        // DAO 는 데이터베이스와 직접 소통하는 클래스이다.
        // 따라서 소통을 하기 위해서는 통로 == Connection == SqlSession 이 필요하다.
        List<MenuDTO> menuList = menuDAO.selectAllMenu(sqlSession);

        sqlSession.close();

        return menuList;
    }

    public MenuDTO selectMenuByMenuCode(int code) {

        // 1. SqlSession 생성
        SqlSession sqlSession = getSqlSession();

        // 2. DAO 계층의 메소드 호출하기 - session 과 추가적인 전달 값 있으면 전달
        MenuDTO menu = menuDAO.selectMenuByMenuCode(sqlSession, code);

        // 3. 사용한 통로 닫아주기
        sqlSession.close();

        return menu;
    }

    public boolean insertNewMenu(MenuDTO newMenu) {

        // 1. SqlSession 생성
        SqlSession sqlSession = getSqlSession();

        // 2. DAO 계층에 SqlSession 과 전달 할 값 있으면 전달
        int result = menuDAO.insertNewMenu(sqlSession, newMenu);

        // 3. dml(insert, update, delete) 구문은 트랜젝션 제어를
        //    해주어야한다. => 저장을 할 것인지, 롤백을 할 것인지
        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        // 4. SqlSession 닫기
        sqlSession.close();

        return result > 0 ? true : false;
    }

    public boolean modifyMenu(MenuDTO modifyMenu) {

        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.updateMenu(sqlSession, modifyMenu);

        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0 ? true : false;
    }

    public boolean deleteMenu(int code) {

        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.deleteMenu(sqlSession, code);

        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0 ? true : false;
    }
}
