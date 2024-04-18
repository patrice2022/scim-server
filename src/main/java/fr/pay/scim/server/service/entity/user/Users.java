package fr.pay.scim.server.service.entity.user;

import java.util.List;

import lombok.Data;

@Data
public class Users {

    private long totalResults;
	
	private int itemsPerPage;
	
	private int startIndex;
	
	private List<User> users;
}
