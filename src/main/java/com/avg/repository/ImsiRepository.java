package com.avg.repository;


// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.avg.model.Imsi;


@Repository("ImsiRepository")
public interface ImsiRepository extends JpaRepository<Imsi, Long>{
}