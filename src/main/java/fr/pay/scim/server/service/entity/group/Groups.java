package fr.pay.scim.server.service.entity.group;

import java.util.List;

import lombok.Data;

@Data
public class Groups {

    private long totalResults;
	
	private int itemsPerPage;
	
	private int startIndex;
	
	private List<Group> groups;
}
