package br.com.delon.apiproduto.repository;

import br.com.delon.apiproduto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Gean Delon
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByCodigo(String codigo);

}
