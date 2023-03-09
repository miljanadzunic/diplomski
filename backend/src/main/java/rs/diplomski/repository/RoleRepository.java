package rs.diplomski.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.diplomski.model.Role;
import rs.diplomski.sys.repository.CustomRepository;

@Repository
public interface RoleRepository extends CustomRepository<Role, Long>{
	
	@Query(value = "SELECT * FROM role rol WHERE rol.rol_name = :rolName", nativeQuery = true)
	Role findRoleByRolName(@Param("rolName") String rolName);
}
