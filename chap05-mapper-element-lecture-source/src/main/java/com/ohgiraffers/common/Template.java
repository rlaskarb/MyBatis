package com.ohgiraffers.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Template {

    //SqlSessionFactory : MyBatis 에서 SQL 세션을 생성하기 위한 팩토리 클래스야
    // 테이터베이스를 연결정보를 포함하고 있으며 SqlSession 객체를 생성하는 역활을 해!
    //SqlSessionFactory 는 일반적으로 싱클톤 패턴으로 구현되어 어플리케이션 전체에서 하나의 인스턴스만 사용돼!

    // 싱글톤 패턴은 어플리케이션 내에서 단 하나의 인스턴스만 존재하도록 보장하는 패턴이다.
    //이는 자원을 효율적으로 사용하고,전역적으로 접근 가능한 객체를 만들 때 유용해!
    private static SqlSessionFactory sqlSessionFactory;

    // SqlSession 은 MyBatis 에서 데이터베이스와의 실제 상화작용을 담당하는 인터페이스야!
    // SQL 쿼리 실행, 트랜잭션 관리,매핑 등을 수행 할 수 있어. SqlSession 객체는
    //SqlSessionFactory 에서 생성이 돼 여시서 중요한건은 SqlSession 객체를 사용한 후에는
    // 반드시 닫아야한다는 점이야 왜냐하면 데이터베이스 연결을 종료하기 때문이야

    // getSqlSession 은 SqlSession 객체를 얻기 우해 사용하는 메스드야 주로
    // SqlSessionFactory 에서 호출이되어 새로운 SqlSession 객체를 반환하지.


    public static SqlSession getSqlSession() {

        if(sqlSessionFactory == null) {
            String resource = "config/mybatis-config.xml"; // 연결하자 !!!ㄴㅂ

            try {
                InputStream inputStream = Resources.getResourceAsStream(resource);

                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }// 열여놔!
        return sqlSessionFactory.openSession(false);
    }

}
