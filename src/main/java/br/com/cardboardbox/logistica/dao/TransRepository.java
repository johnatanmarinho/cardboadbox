package br.com.cardboardbox.logistica.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardboardbox.logistica.beans.Frete;

public interface TransRepository extends JpaRepository<Frete, Integer>{

}
