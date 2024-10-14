package com.ohgiraffers.common;

public class SearchCriteria {

    /* DTO 는 데이터 전송을 위해 사용되는 객체입니다. DTO의 사용은 코드의 구조를 깔끔하게 유지하는데 큰 도움을 줍니다.
    *  SearchCriteria 는 주로 데이터베이스나 다른 테이터 소스로 부터 데이터를 필터링하거나 조회할때 검색 매개변수를
    * 캡슐화 하는 클래스이다. 이클래스를 사용하면 조건과 기준을 깔끔하고 조직적으로 지정할수 있다. */

    private String condition; // 검색 기준(메뉴 이름 혹은 카테고리 명)
    private String value;     // 검색어

    public SearchCriteria() {}


    // Map 형식 과 흡사하다. 그렇다  초기화가 필요한 생성자 ?  뭐 그런거 지 않을까?
    public SearchCriteria(String condition, String value) {
        this.condition = condition;
        this.value = value;
    }



    //  어.. setter 역활을 해주는 역활이다. Map 역활을 대신해줄꺼다.
    public void setCondition(String condition) {
        this.condition = condition;
    }



    //
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "condition='" + condition + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
