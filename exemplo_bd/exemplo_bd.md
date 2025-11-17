# Exemplo de projeto com Java/Hibernate/SQlite
## Estrutura de pastas
```
exemplo_bd
│   database.sqlite
│   pom.xml
│
├───src
│   ├───main
│   │   ├───java
│   │   │   ├───entity
│   │   │   │       Produto.java
│   │   │   │
│   │   │   ├───dao
│   │   │   │   └───produtoDAO.java
│   │   │   │
│   │   │   └───Main.java
│   │   │
│   │   └───resources
│   │           hibernate.cfg.xml
```


## Configuração do Maven (pom.xml)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>ucb</groupId>
  <artifactId>exemplo_bd</artifactId>
  <packaging>jar</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>exemplo_bd</name>
  <url>http://maven.apache.org</url>
  <dependencies>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-engine</artifactId>
      <version>5.9.1</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.xerial</groupId>
      <artifactId>sqlite-jdbc</artifactId>
      <version>3.25.2</version>
    </dependency>
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-core</artifactId>
      <version>5.6.1.Final</version>
    </dependency>
    <dependency>
        <groupId>javax.persistence</groupId>
        <artifactId>javax.persistence-api</artifactId>
        <version>2.2</version>
    </dependency>
    <dependency>
        <groupId>com.github.gwenn</groupId>
        <artifactId>sqlite-dialect</artifactId>
        <version>0.1.1</version>
    </dependency>
  </dependencies>
  <build>
    <plugins>
        <plugin>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.1.2</version>
        </plugin>
        <plugin>
            <artifactId>maven-failsafe-plugin</artifactId>
            <version>3.1.2</version>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <version>3.2.0</version>
            <configuration>
                <archive>
                    <manifest>
                        <mainClass>Main</mainClass>
                    </manifest>
                </archive>
            </configuration>
        </plugin>
    </plugins>
  </build>
  <properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

</project>
```
##  COnfiguração do banco de dados sqlite (resources/hibernate.cfg.xml)
```xml
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration">

<hibernate-configuration>

    <session-factory>
        <!-- Configurações do Banco de Dados -->
        <property name="hibernate.dialect">org.sqlite.hibernate.dialect.SQLiteDialect</property>
        <property name="hibernate.connection.driver_class">org.sqlite.JDBC</property>
        <property name="hibernate.connection.url">jdbc:sqlite:database.sqlite</property>

        <!-- Configurações do Pool de Conexões -->
        <property name="hibernate.c3p0.min_size">5</property>
        <property name="hibernate.c3p0.max_size">20</property>
        <property name="hibernate.c3p0.timeout">300</property>
        <property name="hibernate.c3p0.max_statements">50</property>
        <property name="hibernate.c3p0.idle_test_period">3000</property>

        <property name="hibernate.hbm2ddl.auto">update</property>

        <!-- Mapeamento das Entidades -->
        <mapping class="entity.Produto"/>

    </session-factory>
</hibernate-configuration>
```

## Produto.java
```java
package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer quantidade;

    public Produto() {
    }

    public Produto(String nome, Integer quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", quantidade='" + getQuantidade() + "'" +
            "}";
    }
    
}
```

## ProdutoDAO.java
```java
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
```

## Main.java
```java
import java.util.ArrayList;
import java.util.Scanner;

import dao.ProdutoDAO;
import entity.Produto;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ProdutoDAO dao = new ProdutoDAO();

        ArrayList<Produto> produtos = dao.getTodosProdutos();


        int opcao;

        do {
            System.out.println("===== SISTEMA DE PRODUTOS =====");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nomeP = sc.nextLine();
                    System.out.print("Quanitdade: ");
                    int qtdP = sc.nextInt();
                    sc.nextLine();
                    Produto p1 = new Produto(nomeP, qtdP);
                    produtos.add(p1);
                    dao.salvarProduto(p1);
                    System.out.println("Produto cadastrado com sucesso!\n");
                    break;

                case 2:
                    System.out.println("=== Lista de Produtos ===");
                    for (Produto p : produtos) {
                        System.out.println(p);
                        System.out.println("-------------------");
                    }
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!\n");
            }

        } while (opcao != 0);

        sc.close();
    }
}
```