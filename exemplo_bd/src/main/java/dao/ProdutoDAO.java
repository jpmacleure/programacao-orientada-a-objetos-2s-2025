package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Produto;

public class ProdutoDAO {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Configurando a sessão do Hibernate a partir do arquivo hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void close() {
        sessionFactory.close();
    }

    public void salvarProduto(Produto p) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(p);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Produto getPessoaById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Produto.class, id);
        }
    }

    public void atualizarProduto(Produto p) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(p);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletarProduto(Produto p) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(p);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Retorna todos os registros de Produto como um ArrayList.
     * @return ArrayList de Produtos
     */
    public ArrayList<Produto> getTodosProdutos() {
        try (Session session = sessionFactory.openSession()) {
            Query<Produto> query = session.createQuery("FROM Produto", Produto.class);
            List<Produto> produtosList = query.list();
            
            // Converte a List para ArrayList explicitamente, se necessário
            return new ArrayList<>(produtosList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
        }
    }

}