public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() {
        Catalog catalog = new Catalog("Java Resources", "e:/java/catalog.ser");
        Document doc = new Document("java1", "Java Course 1", "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc.addTag("type", "Slides");
        catalog.addDocument(doc);
        try {
            CatalogUtil.save(catalog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void testLoadView() {
        Catalog catalog=new Catalog();
        try {
            catalog = CatalogUtil.load("e:/java/catalog.ser");
        }catch (Exception e){
            System.out.println(e);
        }
        Document doc = catalog.findById("java1");
        try {
            CatalogUtil.view(doc);
        }catch (Exception e){
          e.printStackTrace();
        }

    }

}
