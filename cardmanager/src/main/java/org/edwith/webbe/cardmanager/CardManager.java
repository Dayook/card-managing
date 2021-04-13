package org.edwith.webbe.cardmanager;
		
import java.util.List;

import org.edwith.webbe.cardmanager.dao.BusinessCardManagerDao;
import org.edwith.webbe.cardmanager.dto.BusinessCard;
import org.edwith.webbe.cardmanager.ui.CardManagerUI;

public class CardManager {
    public static void main(String[] args){
    	// getInstance 통해 UI 생성
        CardManagerUI cardManagerUI = CardManagerUI.getInstance();
        BusinessCardManagerDao businessCardManagerDao = new BusinessCardManagerDao();

        while_loop:
        while(true) {
        	// 메인 메뉴 출력
            cardManagerUI.printMainMenu();
            int menuNumber = cardManagerUI.getMenuNumber();
            switch(menuNumber){
                case 1 :
                	// 명함 입력
                    BusinessCard businessCard = cardManagerUI.inputBusinessCard();
                    // Dao를 통해 명함을 DB로 전달
                    businessCardManagerDao.addBusinessCard(businessCard);
                    break;
                case 2 :
                	// 검색어 입력
                    String searchKeyword = cardManagerUI.getSearchKeyword();
                    // Dao를 통해 검색어에 해당하는 리스트를 받아옴
                    List<BusinessCard> businessCardList = businessCardManagerDao.searchBusinessCard(searchKeyword);
                    // 출력
                    cardManagerUI.printBusinessCards(businessCardList);
                    break;
                case 3 :
                    cardManagerUI.printExitMessage();
                    break while_loop;
                default:
                    cardManagerUI.printErrorMessage();
                    break;
            }
        }
    }
}
