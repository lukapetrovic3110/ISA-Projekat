package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.jpa.model.ReportPharm;

@Repository
public interface IReportPharmRepository  extends JpaRepository<ReportPharm, Long> {

}
