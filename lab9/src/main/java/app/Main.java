package app;

import controller.AbstractController;
import factory.FactoryProvider;
import repo.AbstractRepository;
import util.Database;
import util.PersistenceUtil;

public class Main {
    public static void main(String[] args) {
        AbstractRepository abstractRepository= (AbstractRepository)
                FactoryProvider.getFactory("jpa").create("chart");

        System.out.println("Find artist by id=1 --- JPA");
        System.out.println(abstractRepository.findById(1));

        AbstractController abstractController= (AbstractController)
                FactoryProvider.getFactory("jdbc").create("album");

        System.out.println();
        System.out.println("Get all albums --- JDBC");
        System.out.println(abstractController.getAll());


        abstractRepository.closeEntityManager();
        PersistenceUtil.getInstance().closeFactory();
        Database.closeConnection();
    }
}
