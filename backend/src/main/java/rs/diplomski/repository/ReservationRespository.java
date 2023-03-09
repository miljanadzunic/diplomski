package rs.diplomski.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.diplomski.model.Reservation;
import rs.diplomski.sys.repository.CustomRepository;

@Repository
public interface ReservationRespository extends CustomRepository<Reservation, Long> {
	
	@Query(value = "SELECT * FROM reservation res"
			+ " LEFT JOIN status sta ON (sta.sta_id = res.res_status_id)"
			+ " WHERE res.res_date = :date"
			+ " AND sta.sta_table = 'reservation'"
			+ " AND sta.sta_code != 'STA_OTKAZAN'"
			+ " AND (res.res_court_id = :courtId"
			+ " OR res.res_student_id = :studentId)"
			+ " ORDER BY res.res_time_start ASC", nativeQuery = true)
	List<Reservation> getReservationsByDateStudentCourt(@Param("date") Date date, @Param("studentId") Long studentId, @Param("courtId") Long courtId);
	
	@Query(value = "SELECT * FROM reservation res"
			+ " LEFT JOIN status sta ON (sta.sta_id = res.res_status_id)"
			+ " WHERE res.res_date = :date"
			+ " AND sta.sta_table = 'reservation'"
			+ " AND sta.sta_code != 'STA_OTKAZAN'"
			+ " AND (res.res_court_id = :courtId"
			+ " OR res.res_coach_id = :coachId"
			+ " OR res.res_student_id = :studentId)"
			+ " ORDER BY res.res_time_start ASC", nativeQuery = true)
	List<Reservation> getReservationsByDateStudentCourtCoach(@Param("date") Date date, @Param("studentId") Long studentId, @Param("courtId") Long courtId, @Param("coachId") Long coachId);
	
	@Query(value = "SELECT * FROM reservation res"
			+ " WHERE res.res_student_id = :studentId"
			+ " ORDER BY res.res_date ASC, res.res_time_start ASC", nativeQuery = true)
	List<Reservation> getReservationsByStudentId(@Param("studentId") Long studentId);
	
	@Query(value = "SELECT * FROM reservation res"
			+ " LEFT JOIN status sta ON (sta.sta_id = res.res_status_id)"
			+ " WHERE res.res_student_id = :studentId"
			+ " AND res.res_date = :date"
			+ " AND sta.sta_table = 'reservation'"
			+ " AND sta.sta_code != 'STA_OTKAZAN'"
			+ " ORDER BY res.res_date ASC, res.res_time_start ASC", nativeQuery = true)
	List<Reservation> getReservationsByDateStudentIdStatus(@Param("date") Date date, @Param("studentId") Long studentId);
	
	@Query(value = "SELECT * FROM reservation res"
			+ " WHERE res.res_coach_id = :coachId"
			+ " ORDER BY res.res_date ASC, res.res_time_start ASC", nativeQuery = true)
	List<Reservation> getReservationsByCoachId(@Param("coachId") Long coachId);
	
	@Query(value = "SELECT * FROM reservation res"
			+ " LEFT JOIN status sta ON (sta.sta_id = res.res_status_id)"
			+ " WHERE res.res_date = :date"
			+ " AND sta.sta_table = 'reservation'"
			+ " AND sta.sta_code != 'STA_OTKAZAN'"
			+ " ORDER BY res.res_court_id ASC, res.res_time_start ASC", nativeQuery = true)
	List<Reservation> getReservationsByDate(@Param("date") Date date);
	
	@Query(value = "SELECT * FROM reservation res"
			+ " LEFT JOIN status sta ON (sta.sta_id = res.res_status_id)"
			+ " WHERE res.res_date = :date"
			+ " AND sta.sta_table = 'reservation'"
			+ " AND sta.sta_code != 'STA_OTKAZAN'"
			+ " AND (res.res_time_start = :timeStart OR (res.res_time_start < :timeStart AND res.res_time_end > :timeStart))"
			+ " ORDER BY res.res_court_id ASC", nativeQuery = true)
	List<Reservation> getReservationsByDateTimeStart(@Param("date") Date date, @Param("timeStart") Integer timeStart);
	
	@Query(value = "SELECT * FROM reservation res"
			+ " LEFT JOIN status sta ON (sta.sta_id = res.res_status_id)"
			+ " WHERE res.res_date = :date"
			+ " AND sta.sta_table = 'reservation'"
			+ " AND sta.sta_code != 'STA_OTKAZAN'"
			+ " AND res.res_coach_id IS NOT null"
			+ " AND (res.res_time_start = :timeStart OR (res.res_time_start < :timeStart AND res.res_time_end > :timeStart))"
			+ " ORDER BY res_court_id ASC", nativeQuery = true)
	List<Reservation> getReservationsByDateTimeStartForCoaches(@Param("date") Date date, @Param("timeStart") Integer timeStart);

	@Query(value = "SELECT * FROM reservation res"
			+ " LEFT JOIN status sta ON (sta.sta_id = res.res_status_id)"
			+ " WHERE res.res_date = :date"
			+ " AND sta.sta_table = 'reservation'"
			+ " AND sta.sta_code != 'STA_OTKAZAN'"
			+ " AND (res.res_court_id = :courtId"
			+ " OR res.res_coach_id = :coachId"
			+ " OR res.res_student_id = :studentId"
			+ " OR res.res_group_id = :groupId)"
			+ " ORDER BY res.res_time_start ASC", nativeQuery = true)
	List<Reservation> getReservationsByDateStudentCourtCoachGroup(@Param("date") Date date, @Param("studentId") Long studentId, 
			@Param("courtId") Long courtId, @Param("coachId") Long coachId, @Param("groupId") Long groupId);
	
	@Query(value = "SELECT * FROM reservation res"
			+ " WHERE res.res_coach_id = :coachId"
			+ " AND res.res_group_id IS NOT null"
			+ " ORDER BY res.res_time_start ASC", nativeQuery = true)
	List<Reservation> getReservationsByCoachAndGroupNotNull(@Param("coachId") Long coachId);
	
	@Query(value = "SELECT * FROM reservation res"
			+ " WHERE res.res_group_id = :groupId", nativeQuery = true)
	List<Reservation> getReservationsByGroupId(@Param("groupId") Long groupId);
	
	@Query(value = "SELECT res.* FROM reservation res"
			+ " LEFT JOIN student_coach sct ON (sct.sct_group_id = res.res_group_id)"
			+ " WHERE res.res_student_id = :studentId"
			+ " OR sct.sct_student_id = 10"
			+ " ORDER BY res.res_date ASC, res.res_time_start ASC, res.res_status_id ASC", nativeQuery = true)
	List<Reservation> getReservationsByStudentIdAndGroup(@Param("studentId") Long studentId);
	
}
