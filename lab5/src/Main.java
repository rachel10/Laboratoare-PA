public class Main {
    public static void main(String[] args) {
//        Main app = new Main();
//        app.testCreateSave();
//        app.testLoadView();


//        Catalog catalog = new Catalog("Java Resources", "e:/java/catalog.ser");
//        Document doc = new Document("java1", "Java Course 1", "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
//        doc.addTag("type", "Slides");
//        catalog.addDocument(doc);
//        Document document=new Document("java0","JAVA Curs Practic","E:/Cristian_Frasinaru-Curs_practic_de_Java edited.pdf");
//        catalog.addDocument(document);
//       try {
//           CatalogUtil.save(catalog);
//       }catch (Exception e){
//           System.out.println(e);
//       }

        Shell shell=new Shell();

        try {
            shell.getCommand();
        }catch (Exception e){
            e.printStackTrace();
        }

        Command command=new RaportHtmlCommand();
        try {
            command.solver("e:/java/catalog.ser");
        } catch (InvalidCatalogException e) {
            e.printStackTrace();
        } catch (InvalidDocumentException e) {
            e.printStackTrace();
        }
    }

    private void testCreateSave() {
        Catalog catalog = new Catalog("Java Resources", "e:/java/catalog1.ser");
        Document doc = new Document("java1", "Java Course 1", "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc.addTag("type", "Slides");
        catalog.addDocument(doc);
        try {
            CatalogUtil.saveAsPlainText(catalog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void testLoadView() {
        Catalog catalog=new Catalog();
        try {
           catalog= CatalogUtil.loadAsPlainText("e:/java/catalog1.ser");
        }catch (Exception e){
            e.printStackTrace();
        }
        Document doc = catalog.findById("java1");
        try {
            CatalogUtil.view(doc);
        }catch (Exception e){
          e.printStackTrace();
        }

    }

}
