package com.ohgiraffers.section01.xmlmapper;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ElementService elementService = new ElementService();
        // do while 문으로 메뉴판을 생성 !
        do {
            System.out.println("==Mapper Element 테스트 메뉴==");
            System.out.println("1.<cache> 테스트");
            System.out.println("2.<resultMap> 서브메뉴");
            System.out.println("3.<sql> 테스트");
            System.out.print("원하시는 메뉴를 골라주세요 :");
            int no = sc.nextInt();

            // 선택할수 있게 스위치 구문 생성!
            switch (no){
                case 1 :
                    elementService.selectCacheTest();
                    break;
                case 2 :
                    resultMapSubMenu();
                    break;
                case 3 :
                    elementService.sqlTest();
                    break;

            }

        }while (true);



    }


    private static void resultMapSubMenu() {
        Scanner sc = new Scanner(System.in);
        ElementService elementService = new ElementService();

        do {
            System.out.println("==ResulMap 메뉴==");
            System.out.println("1. resultMap");
            System.out.println("2. constructor");
            System.out.println("3. association");
            System.out.println("4. collection");
            System.out.print("메뉴를 입력해주세요 :");
            int no = sc.nextInt();

            switch (no){
                case 1 :
                    elementService.selectResultMapTest();
                    break;
                case 2 :
                    elementService.selectResultMapConstructor();
                    break;
                case 3 :
                    elementService.selectResultMapAssociationTest();
                    break;
                case 4 :
                    elementService.selectResultMapCollection();
            }

        }while (true);
    }
}
