package schedulerforme.run;

import java.util.List;


import schedulerforme.controller.ScheController;
import schedulerforme.model.Schedule;
import schedulerforme.view.ScheView;

public class ScheRun {
    public static void main(String [] args) {
    	List<Schedule> scheList = null;
    	ScheController scheCon = new ScheController();
    	ScheView scheView = new ScheView();
    	Schedule schedule = null;
    	int insertDate = 0;
    	String privateOfficial = "";
    	String insertTitle = "";
    	int result = 0;
    	exit :
    	while(true) {
    		int choice = scheView.printMenu();
    		switch(choice) {
    		case 1 : //전체 스케줄 시간순서로 보기
    			scheList = scheCon.accessAll();
    			if(!scheList.isEmpty()) {
    			scheView.printSuccess("총 "+ scheList.size() +"건의 일정이 있어요.");
    			scheView.showAll(scheList, "나의 전체");
    			}else {
    				scheView.printFail("아직 등록된 일정이 없어요.");
    			}
    			break;
    		case 2 : //날짜별 스케줄 보기 
    			insertDate = scheView.searchSche("일정이 궁금한 날짜 입력해주세요(yyyymmdd) :"); //날짜를 입력받아서
    			scheList = scheCon.checkDate(insertDate);
    			if(!scheList.isEmpty()) {
    				scheView.printSuccess("총 "+ scheList.size() +"건의 일정이 있어요.");
    				scheView.showAll(scheList, "검색된 날짜의");
    			}else {
    				scheView.printFail(insertDate + "에는 아무 일정도 없어요.");
    			}
    			break;
    		case 3 : //개인스케줄만 보기
    			privateOfficial = scheView.searchPrivateOfficial("개인");
    			scheList = scheCon.checkPrivate(privateOfficial);
    			if(!scheList.isEmpty()) {
    				scheView.printSuccess("총 "+ scheList.size() +"건의 일정이 있어요.");
    				scheView.showAll(scheList, "나의 개인");
    			}else {
    				scheView.printFail("아직 아무 개인 일정도 없어요.");
    			}
    			break;
    		case 4 : //공식만 보기
    			privateOfficial = scheView.searchPrivateOfficial("공식");
    			scheList = scheCon.checkPrivate(privateOfficial);
    			if(!scheList.isEmpty()) {
    				scheView.printSuccess("총 "+ scheList.size() +"건의 일정이 있어요.");
    				scheView.showAll(scheList, "나의 공식");
    			}else {
    				scheView.printFail("아직 아무 공식 일정도 없어요.");
    			}
    			break;
    		case 5 : //새 일정 등록하기
    			schedule = scheView.insertSche();
    			result = scheCon.saveSche(schedule);
    			if(result > 0) {
    				scheView.printSuccess("새 일정이 추가됐어요!");
    			} else {
    				scheView.printFail("다시 차근히 해보세요ㅠ");
    			}
    			break;
    		case 6 : //수정
    			insertTitle = scheView.searchTitle("수정");
    			schedule = scheCon.printOneSche(insertTitle);
    			if(schedule != null) {
    				schedule = scheView.modifySche(insertTitle);
    				result = scheCon.modifySche(schedule);
    				if(result>0) {
    					scheView.printSuccess("수정이 완료됐어요");
    				}else {
    					scheView.printFail("수정 실패! 다시 시도해주세요.");
    				}
    			}else {
    				scheView.printFail("아이디를 찾을수 없어요.");
    			}
    			break;
    		case 7 : 
    			insertTitle = scheView.searchTitle("삭제");
    			schedule = scheCon.printOneSche(insertTitle);
    			if(schedule != null) {
    				result = scheCon.deleteSche(insertTitle);
    				if(result>0) {
    					scheView.printSuccess("삭제가 완료됐어요");
    				}else {
    					scheView.printFail("삭제 실패! 다시 시도해주세요.");
    				}
    				
    			}else {
    				scheView.printFail("아이디를 찾을수 없어요.");
    			}
    			break;
    		case 8 : break exit; //줭료
    		default : 
    			scheView.printFail("1 ~ 8 사이 숫자를 입력해주세요");
    			break;
    		}
    	}
    }
}
