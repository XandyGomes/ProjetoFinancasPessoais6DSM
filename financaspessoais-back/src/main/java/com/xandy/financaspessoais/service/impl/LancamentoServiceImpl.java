package com.xandy.financaspessoais.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.xandy.financaspessoais.model.entity.Lancamento;
import com.xandy.financaspessoais.model.enums.StatusLancamento;
import com.xandy.financaspessoais.model.repository.LancamentoRepository;
import com.xandy.financaspessoais.service.LancamentoService;

import jakarta.transaction.Transactional;

@Service
public class LancamentoServiceImpl implements LancamentoService {
	
	private LancamentoRepository repository;
	
	public LancamentoServiceImpl(LancamentoRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		repository.delete(lancamento);
	}

	@Override
	public List<Lancamento> buscar(Lancamento lancamentoFiltro) {

		String sql = "SELECT * FROM financas.lancamento WHERE 1 = 1";
		
		if(lancamentoFiltro.getDescricao() != null) {
			sql = sql + " and descricao = " + lancamentoFiltro.getDescricao();
		}
		
		return null;
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
		lancamento.setStatus(status);
		atualizar(lancamento);
	}

}
