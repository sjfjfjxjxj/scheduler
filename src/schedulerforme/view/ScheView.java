package schedulerforme.view;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import schedulerforme.controller.ScheController;
import schedulerforme.model.Schedule;

public class ScheView {
//	 SCHE_TITLE VARCHAR2(30) PRIMARY KEY,
//    SCHE_DEADLINE NUMBER,
//    SCHE_OFFICIALCHECK VARCHAR2(10) CHECK(SCHE_OFFICIALCHECK IN('공식','개인')),
//    SCHE_TODO VARCHAR2(30) NOT NULL,
//    SCHE_WITHWHOM VARCHAR2(30),
//    SCHE_TOWHERE VARCHAR2(30),
//    SCHE_SYSDATE TIMESTAMP DEFAULT SYSDATE  
//
	
	public int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-------------심플스케줄러-------------");
		System.out.println("1. 나의 전체 일정 보기");
		System.out.println("2. 날짜별 일정 보기");
		System.out.println("3. 『개인』일정만 보기");
		System.out.println("4. 『공식』일정만 보기");
		System.out.println("5. 새 일정 추가하기");
		System.out.println("6. 일정 수정하기");
		System.out.println("7. 일정 삭제하기");
		System.out.println("8. 프로그램 종료");
		System.out.print("[메뉴선택] : ");
		int choice = sc.nextInt();
		return choice;
	}
	
	public void showAll(List<Schedule> scheList, String msg) {
		System.out.println("------------"+ msg +" 일정 조회------------");
		for(Schedule singleSche : scheList) {
			System.out.println("일정 제목: " + singleSche.getScheTitle());
			System.out.print("☆ "+singleSche.getScheDeadline());
			System.out.println(singleSche.getScheOfficialCheck()+"일정 ☆");
			System.out.print("【"+singleSche.getScheTodo()+"】을/를 ");
			System.out.print("【"+singleSche.getScheWithWhom()+"】(이)랑 ");
			System.out.println("【"+singleSche.getScheToWhere()+"】에서 할 예정이에요.");
			System.out.println("기록:" +singleSche.getScheSysdate());
			System.out.println("------♪------♪------");
		}
	}
	
	/**
	 * 날짜별 보기
	 * @param msg
	 * @return
	 */
	public int searchSche(String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.print(msg);
		int deadline = sc.nextInt();
		return deadline;
	}
	
	public String searchPrivateOfficial(String msg) {
		System.out.println("--------【"+msg+"】 일정 보기--------");
		return msg;
	}

	/**
	 * 수정/삭제용 일정 제목 받기
	 * @param msg
	 * @return
	 */
	public String searchTitle(String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.println(msg+"하고싶은 일정의 제목을 입력해주세요 :");
		String scheTitle = sc.next();
		return scheTitle;
	}
	
	public Schedule modifySche(String insertTitle) {
		Schedule schedule = new Schedule();
		Scanner sc = new Scanner(System.in);
		schedule.setScheTitle(insertTitle);
		System.out.print("수정할 날짜 입력:");
		schedule.setScheDeadline(sc.nextInt()); 
		System.out.print("공식/개인 여부 수정:");
		schedule.setScheOfficialCheck(sc.next());
		System.out.print("할 일 수정:");
		schedule.setScheTodo(sc.next());
		System.out.print("같이 할 사람 수정:");
		schedule.setScheWithWhom(sc.next());
		System.out.print("할 장소 수정:");
		schedule.setScheToWhere(sc.next());
		return schedule;
	}
	
	
	public Schedule insertSche() {
		Scanner sc = new Scanner(System.in);
		System.out.println("------------나의 일정 입력하기------------");
		System.out.println("띄어쓰기 없이 입력해주세요!!"); //아 왜 띄어쓰기하면 에러나냐 힝입니다
		System.out.print("일정 제목이 뭔가요? : ");
		String scheTitle = sc.next();
		System.out.print("그게 언제죠?(yyyymmdd 입력) : ");
		int scheDeadline = sc.nextInt();
		System.out.print("공식/개인 여부를 알려주세요 : ");
		String scheOfficialCheck = sc.next();
		System.out.print("무엇을 하나요? : ");
		String scheTodo = sc.next();
		System.out.print("누구랑 할 건가요?(혼자라면 '나'라고 입력해주세요) : ");
		String scheWithWhom = sc.next();
		System.out.print("어디서 할 건가요? : ");
		String scheToWhere = sc.next();
		Schedule schedule = new Schedule(scheTitle, scheDeadline, scheOfficialCheck, scheTodo, scheWithWhom, scheToWhere, null);
		return schedule;
	}
	
	
	
//private String scheTitle;
//private int deadline;
//private String scheOfficialCheck;
//private String scheTodo;
//private String withWhom;
//private String toWhere;
//private Timestamp schSysdate;
	
	
	
	
	
	public String printSuccess(String msg) {
		System.out.println("[☆성공☆] : " + msg);
		return msg;
	}
	
	public String printFail(String msg) {
		System.out.println("[실패!] : " + msg);
		return msg;
	}

	
	
}
