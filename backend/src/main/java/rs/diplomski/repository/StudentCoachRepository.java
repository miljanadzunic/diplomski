package rs.diplomski.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.diplomski.model.StudentCoach;
import rs.diplomski.sys.repository.CustomRepository;

@Repository
public interface StudentCoachRepository extends CustomRepository<StudentCoach, Long>{
	
	@Query(value = "SELECT * FROM student_coach WHERE sct_coach_id = :coachId", nativeQuery = true)
	List<StudentCoach> getAllByCoachId(@Param("coachId") Long coachId);
	
	@Query(value = "SELECT * FROM student_coach"
			+ " LEFT JOIN status ON (sta_id = sct_status_id)"
			+ " WHERE sct_coach_id = :coachId "
			+ " AND sct_student_id = :studentId"
			+ " AND sta_table = 'student_coach'"
			+ " AND sta_code != 'STA_ODBIJEN'", nativeQuery = true)
	StudentCoach findByCoachAndStudent(@Param("coachId") Long coachId, @Param("studentId") Long studentId);
	
	@Query(value = "SELECT * FROM student_coach"
			+ " LEFT JOIN status ON (sta_id = sct_status_id)"
			+ " WHERE sct_student_id = :studentId"
			+ " AND sta_table = 'student_coach'"
			+ " AND sta_code = 'STA_PRIHVACEN'", nativeQuery = true)
	List<StudentCoach> getAllByStudentIdAccepted(@Param("studentId") Long studentId);
	
	@Query(value = "SELECT * FROM student_coach"
			+ " LEFT JOIN status ON (sta_id = sct_status_id)"
			+ " WHERE sct_student_id = :studentId"
			+ " AND sta_table = 'student_coach'"
			+ " AND sta_code = 'STA_NOVI'", nativeQuery = true)
	List<StudentCoach> getAllByStudentIdNew(@Param("studentId") Long studentId);
	
	@Query(value = "SELECT * FROM student_coach"
			+ " LEFT JOIN status ON (sta_id = sct_status_id)"
			+ " WHERE sct_student_id = :studentId"
			+ " AND sta_table = 'student_coach'"
			+ " AND (sta_code = 'STA_PRIHVACEN'"
			+ " OR sta_code = 'STA_NOVI')", nativeQuery = true)
	List<StudentCoach> getAllByStudent(@Param("studentId") Long studentId);
	
	@Query(value = "SELECT * FROM student_coach"
			+ " LEFT JOIN status ON (sta_id = sct_status_id)"
			+ " WHERE sct_coach_id = :coachId "
			+ " AND sct_student_id = :studentId"
			+ " AND sta_table = 'student_coach'"
			+ " AND sta_code = 'STA_PRIHVACEN'", nativeQuery = true)
	StudentCoach findByCoachAndStudentAccepted(@Param("coachId") Long coachId, @Param("studentId") Long studentId);
	
	@Query(value = "SELECT * FROM student_coach"
			+ " LEFT JOIN status ON (sta_id = sct_status_id)"
			+ " WHERE sct_coach_id = :coachId "
			+ " AND sta_table = 'student_coach'"
			+ " AND sta_code = 'STA_PRIHVACEN'", nativeQuery = true)
	List<StudentCoach> getByCoachAccepted(@Param("coachId") Long coachId);
	
	@Query(value = "SELECT * FROM student_coach"
			+ " WHERE sct_group_id = :groupId", nativeQuery = true)
	List<StudentCoach> getByGroupId(@Param("groupId") Long groupId);
	
	@Query(value = "SELECT * FROM student_coach"
			+ " LEFT JOIN status ON (sta_id = sct_status_id)"
			+ " WHERE sct_coach_id = :coachId "
			+ " AND sct_student_id = :studentId"
			+ " AND sct_group_id IS NOT null"
			+ " AND sta_table = 'student_coach'"
			+ " AND sta_code = 'STA_PRIHVACEN'", nativeQuery = true)
	StudentCoach findByCoachAndStudentAcceptedAndGroupNotNull(@Param("coachId") Long coachId, @Param("studentId") Long studentId);
}
