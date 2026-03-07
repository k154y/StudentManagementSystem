
package database;
public interface DatabaseOperations {
    void add(int userId);
    void delete();
    void update();
    void search(String keyword);
}