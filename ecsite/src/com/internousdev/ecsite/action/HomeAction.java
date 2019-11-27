package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;

	public String execute(){
		String result = "login";

		//ログイン済み判定を行う
		if(session.containsKey("login_user_id")){
			BuyItemDAO buyItemDAO = new BuyItemDAO();
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
			//DBから取得した商品情報をセッションに格納
			session.put("id", buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price",buyItemDTO.getItemPrice());
			return SUCCESS;
		}
		//executeメソッドの戻り値として、ログイン状態の場合はSUCCESS,
		//ログインしていない場合は"login"を返す
		return result;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	//Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}

}
