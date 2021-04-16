package IOT.Cloud.DataAnalyse.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import IOT.Cloud.DataAnalyse.entity.Record;
/**
 * 
 * @author 周扬
 *
 */
@Repository
public interface RecordRepository extends CrudRepository<Record, Integer>,JpaRepository<Record, Integer>{

	public Record save(Record record);
	public List<Record> findByLightId(Integer lightId);
	public List<Record> findAll();
	
}
