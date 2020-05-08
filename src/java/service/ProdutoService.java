package service;

import dao.CategoriaDao;
import dao.ProdutoDao;
import javax.servlet.ServletException;
import model.Categoria;
import model.Produto;

/**
 *
 * @author caioc
 */
public class ProdutoService {
    public String confirmarOperacao(String operacao, String strCodigo, String nome, String strValor, String strCategoria, String descricao) {
        String retorno = "";        
        try {
            int codigo = 0;
            if (!strCodigo.equals("")) {
                codigo = Integer.parseInt(strCodigo);
            } else {
                codigo = ProdutoDao.lastId() + 1;
            }

            strValor = strValor.replace(',', '.');
            float valor = Float.parseFloat(strValor);
            Categoria categoria = CategoriaDao.get(Integer.parseInt(strCategoria));
            
            Produto produto = new Produto(codigo, nome, descricao, categoria, valor);
            switch (operacao) {
                case "incluir":
                    ProdutoDao.salvar(produto);
                    retorno = "Incluido com sucesso";
                    break;
                case "excluir":
                    ProdutoDao.apagar(produto);
                    retorno = "Excluido com sucesso";
                    break;
                case "alterar":
                    ProdutoDao.alterar(produto);
                    retorno = "Alteraco com sucesso";
                    break;
            }
        } catch (Exception e) {
            retorno = "Erro durante a operação: " + e.getMessage();
        }
        return retorno;
    }
}
