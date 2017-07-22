import java.util.ArrayList;

/**
 * a section
 */
class mSection {

  private ArrayList<mSection> subSection = new ArrayList<mSection>();

  private ArrayList<Product> products = new ArrayList<Product>();

  private String name;

  mSection(String n) {
    String name = n;
    ArrayList<mSection> subSection = new ArrayList<mSection>();
  }

  /**
   * Add a subsection within a section, it helps customers to find products easier.
   * @param s the section
   */
  void addSubsection(mSection s) {
    this.subSection.add(s);
  }

  public void addProduct(Product product) {
    products.add(product);
  }

}
