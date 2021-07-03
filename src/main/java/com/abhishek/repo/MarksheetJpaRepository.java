package com.abhishek.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abhishek.entity.Marksheet;

public interface MarksheetJpaRepository extends JpaRepository<Marksheet, Integer> {

	@Query("select m from Marksheet m where m.roll_no =:roll_no")
	public Marksheet findByRoll_No(int roll_no);
	
	@Query("update Marksheet m set m.stud_name =:stud_name,m.roll_no =:roll_no,m.physics =:physics,m.chemistry =:chemistry,m.maths =:maths,m.biology =:biology,m.college_name =:college_name where m.stud_id =:stud_id")
	public Marksheet updateMarksheet(@Param("stud_id") int stud_id,@Param("stud_name")String stud_name,@Param("roll_no")int roll_no,@Param("physics")int physics,@Param("chemistry")int chemistry,@Param("maths")int maths,@Param("biology")int biology,@Param("college_name")String college_name);
	
}
