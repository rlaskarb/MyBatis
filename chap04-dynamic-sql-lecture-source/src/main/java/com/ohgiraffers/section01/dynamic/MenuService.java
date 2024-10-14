package com.ohgiraffers.section01.dynamic;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ohgiraffers.common.Template.getSqlSession;

public class MenuService {

    private DynamicSqlMapper mapper;
// 어플리케이션의 inputPrice() 메소드를  사용을 한다 그러기떄문에 전달이자 (int price) 를 입력해줘야 한다 ! 이유는 생략.

    public void selectMenuByPrice(int price) {

        SqlSession sqlSession = getSqlSession();

        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        /* comment.
         *   우리가 지금 하려고 하는 것이 price 를 query 문에 전달해서
         *   금액에 따라 적합한 메뉴를 추천을 해 줄 것이다.
         *   기본 자료형으로는 조건문의 값을 비교할 수 없으며
         *   따라서 인스턴스화를 진행해야 한다.
         *   hashmap 을 사용해서 key 값으로 접근,
         *   dto 를 사용해서 getter 메소드로 접근
         *  */

        // int 의 wrapper 클래스 -> 기본 데이터 타입들을 객체로 다루기 위해 사용되는 클래스

        Map<String, Integer> map = new HashMap<>();
        map.put("price", price);    // auto boxing

        // 우리가 전달한 값에 해당하는 메뉴들이 조회
        List<MenuDTO> menuList = mapper.selectMenuByPrice(map);
        // 메뉴가 존재한다면
        if (menuList != null && menuList.size() > 0) {
            for (MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과 없습니다.");
        }

        sqlSession.close();

    }

    public void searchMenu(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();

        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenu(searchCriteria);

        if (menuList != null && menuList.size() > 0) {
            for (MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과 없습니다.");
        }

        sqlSession.close();

    }

    public void searchMenuBySupCategory(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();

        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuBySupCategory(searchCriteria);

        if (menuList != null && menuList.size() > 0) {
            for (MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과 없습니다.");
        }

        sqlSession.close();

    }

    public void searchMenuByRandomCode(List<Integer> randomCodeList) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        Map<String, List<Integer>> criteria = new HashMap<>();
        criteria.put("randomCodeList", randomCodeList);
        List<MenuDTO> menuList = mapper.searchMenuByRandomCode(criteria);

        if (menuList != null && menuList.size() > 0) {
            for (MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과 없습니다.");
        }

        sqlSession.close();

    }

    public void searchMenuByCodeOrSearchAll(SearchCriteria searchCriteria) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByCodeOrSearchAll(searchCriteria);


        if (menuList != null && menuList.size() > 0) {
            for (MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과 없습니다.");
        }

        sqlSession.close();


    }

    public void searchMenuByCodeByOrCategory(Map<String, Object> criteria) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByNameOrCategory(criteria);


        if (menuList != null && menuList.size() > 0) {
            for (MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과 없습니다.");
        }

        sqlSession.close();
    }

    public void modifyMenu(Map<String, Object> criteria) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        int result = mapper.modifyMenu(criteria);

        if (result > 0) {
            System.out.println("메뉴 정보 변경에 성공!!");
            sqlSession.commit();
        } else {
            System.out.println("메뉴 변경에 실패...");
            sqlSession.rollback();

        }
    }
}
