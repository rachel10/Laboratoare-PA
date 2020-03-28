abstract class Command {
    private String name;

     public Command(String name) {
        this.name = name;
    }
    abstract public Catalog solver(String path) throws InvalidCatalogException, InvalidDocumentException;
    public void printName(){
        System.out.println(name);
    }
}
