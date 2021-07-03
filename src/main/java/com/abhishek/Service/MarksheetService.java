package com.abhishek.Service;

import java.util.List;

import com.abhishek.entity.Marksheet;

public interface MarksheetService {

	public List<Marksheet> getAllMarksheet();
	
	public Marksheet getMarksheet(int id);
	
	public void saveMarksheet(Marksheet marksheet);
	
	public void deleteMarksheet(int id);
	
	public Marksheet findByRoll_No(int roll_no);
	
}
