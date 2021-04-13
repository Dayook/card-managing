package org.edwith.webbe.cardmanager;
		
import java.util.List;

import org.edwith.webbe.cardmanager.dao.BusinessCardManagerDao;
import org.edwith.webbe.cardmanager.dto.BusinessCard;
import org.edwith.webbe.cardmanager.ui.CardManagerUI;

public class CardManager {
    public static void main(String[] args){
    	// getInstance ���� UI ����
        CardManagerUI cardManagerUI = CardManagerUI.getInstance();
        BusinessCardManagerDao businessCardManagerDao = new BusinessCardManagerDao();

        while_loop:
        while(true) {
        	// ���� �޴� ���
            cardManagerUI.printMainMenu();
            int menuNumber = cardManagerUI.getMenuNumber();
            switch(menuNumber){
                case 1 :
                	// ���� �Է�
                    BusinessCard businessCard = cardManagerUI.inputBusinessCard();
                    // Dao�� ���� ������ DB�� ����
                    businessCardManagerDao.addBusinessCard(businessCard);
                    break;
                case 2 :
                	// �˻��� �Է�
                    String searchKeyword = cardManagerUI.getSearchKeyword();
                    // Dao�� ���� �˻�� �ش��ϴ� ����Ʈ�� �޾ƿ�
                    List<BusinessCard> businessCardList = businessCardManagerDao.searchBusinessCard(searchKeyword);
                    // ���
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
