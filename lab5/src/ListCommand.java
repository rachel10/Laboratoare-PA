public class ListCommand extends Command {
    public ListCommand() {
        super("ListCommand");
    }

    @Override
    public Catalog solver(String path) throws InvalidCatalogException {
        Command command=new LoadCommand();
        Catalog catalog=new Catalog();
        try {
            catalog = command.solver(path);
        }catch (Exception e){
           throw new InvalidCatalogException(e);
        }

        System.out.println("List of documents:");
        for (Document document:catalog.getDocuments()){
            System.out.println(document);
        }
        return catalog;
    }

}
