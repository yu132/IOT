package IOT.Cloud.DataAnalyse.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
/**
 * 
 * @author 周扬
 *
 */
@Entity
@Table(name = "record_info")
@Data
public class Record {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "recordTime")
	public Date recordTime;
	
    @Column(name = "lightId")
	public Integer lightId;
	
    @Column(name = "recordMark", length = 100)
	public String recordMark;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public Integer getLightId() {
		return lightId;
	}
	public void setLightId(Integer lightId) {
		this.lightId = lightId;
	}
	public String getRecordMark() {
		return recordMark;
	}
	public void setRecordMark(String recordMark) {
		this.recordMark = recordMark;
	}
	@Override
	public String toString() {
		return "Record [id=" + id + ", recordTime=" + recordTime + ", lightId=" + lightId + ", recordMark=" + recordMark
				+ "]";
	}
	
}
