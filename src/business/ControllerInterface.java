package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public String getMemberInfo(String id);
	public String getAllBookInfo();
	public boolean addMultipleBookCopy(String id, int numberOfCopy);
}
