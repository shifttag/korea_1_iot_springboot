class A {
    static int a=0;
}

public class test {
    public static void main(String[] args) {
        A.a=1;
        System.out.println(A.a);
    }
}


/*
1. interface
: 인터페이스에서는 메서드의 구현부가 존재하지 않는다.
    , 밑에서 호출함과 동시에 구현부를 작성하여 사용한다.
interface A {
}

2. implements
: 인터페이스를 상속할때 사용하는 키워드
interface B {
}
interface A implements B {
}

3. extends
: 클래스를 상속할때 사용 하는 키워드
class A{
}
class B extends A {
}

4. static
: 정적
class A {
    static int a=0;
}

public class test {
    public static void main(String[] args) {
        A.a=1;
        System.out.println(A.a);    // 1 출력
    }


5. final
: 상수(변하지 않는 수)
    final 선언과 동시에 초기화 함, 하고나면 재선언, 재할당 불가능
    final int a = 0;
    a= 1;   - Error

6. mvc패턴
: Model, View, Controller 패턴이다.
    오류수정 및 코드관리를 쉽게 하기위해 각 역할에 맞게 패키징 하여 코드를 작성한다
    - Model : 선언하는 부분
    - View : 보여지는 부분
    - Controller : 제어하는 부분

7. service, controller, repository, entity, dto의 역할
        -) 요청, 응답 구조

    - service : 각 실행 구현이 어떻게 될지 구현하는 부분
    - controller : 어떤 서비스를 실행시킬지 선택해주는 부분
    - Repository : 데이터베이스와 연결되는 부분
    - entity :
    - dto :
    요청 구조? :
    응답 구조? :

 */