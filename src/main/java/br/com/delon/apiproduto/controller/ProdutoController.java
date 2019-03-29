package br.com.delon.apiproduto.controller;

import br.com.delon.apiproduto.arquitetura.exception.ResourceNotFoundException;
import br.com.delon.apiproduto.model.Produto;
import br.com.delon.apiproduto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Gean Delon
 */

@RestController
@RequestMapping("/api")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/produtos")
    public List<Produto> getTodos() {
        return produtoRepository.findAll();
    }

    @PostMapping("/produtos")
    public Produto criarProduto(@Valid @RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @GetMapping("/produtos/{codigo}")
    public Produto getProdutoPorCodigo(@PathVariable(value = "codigo") String codigoProduto) {
        return produtoRepository.findByCodigo(codigoProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", "código", codigoProduto));
    }

    @PutMapping("/produtos/{codigo}")
    public Produto atualizarProduto(@PathVariable(value = "codigo") String codigoProduto,
                                    @Valid @RequestBody Produto detalhesProduto) {

        Produto produto = produtoRepository.findByCodigo(codigoProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", "código", codigoProduto));

        produto.setCodigo(detalhesProduto.getCodigo());
        produto.setDescricao(detalhesProduto.getDescricao());
        produto.setPreco(detalhesProduto.getPreco());

        return produtoRepository.save(produto);
    }

    @DeleteMapping("/produtos/{codigo}")
    public ResponseEntity<?> removerProduto(@PathVariable(value = "codigo") String codigoProduto) {
        Produto produto = produtoRepository.findByCodigo(codigoProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", "código", codigoProduto));

        produtoRepository.delete(produto);

        return ResponseEntity.ok().build();
    }


}
