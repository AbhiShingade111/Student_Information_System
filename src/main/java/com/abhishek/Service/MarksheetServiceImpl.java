package com.abhishek.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.entity.Marksheet;
import com.abhishek.repo.MarksheetJpaRepository;

@Service
public class MarksheetServiceImpl implements MarksheetService {
	
	private MarksheetJpaRepository marksheetJpaRepository;
	
	@Autowired
	public MarksheetServiceImpl(MarksheetJpaRepository marksheetJpaRepository) {
		super();
		this.marksheetJpaRepository = marksheetJpaRepository;
	}

	@Override
	public List<Marksheet> getAllMarksheet() {
		
		List<Marksheet> theMarksheets = marksheetJpaRepository.findAll();
		
		return theMarksheets;
	}

	@Override
	public Marksheet getMarksheet(int id) {

		Marksheet marksheet = null;
		
		Optional<Marksheet> theMarksheet = marksheetJpaRepository.findById(id);
		
		if(theMarksheet.isPresent()) {
			marksheet= theMarksheet.get();
		}else {
			throw new RuntimeException("Marksheet Not found");
		}
		
		return marksheet;
	}

	@Override
	public void saveMarksheet(Marksheet marksheet) {

		marksheetJpaRepository.save(marksheet);
		
	}

	@Override
	public void deleteMarksheet(int id) {

		marksheetJpaRepository.deleteById(id);
	}

	@Override
	public Marksheet findByRoll_No(int roll_no) {
		// TODO Auto-generated method stub
		return marksheetJpaRepository.findByRoll_No(roll_no);
	}

}
