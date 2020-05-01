package factory;

public class FactoryProvider {
    public static AbstractFactory getFactory(String choice){
        if ("jdbc".equalsIgnoreCase(choice)){
           return new JdbcFactory();
        }
        else {
            if ("jpa".equalsIgnoreCase(choice)){
                return new JpaFactory();
            }
        }
        return null;
    }
}
