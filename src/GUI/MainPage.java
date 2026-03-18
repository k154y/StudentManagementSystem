/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import model.User;
import model.Student;
import java.sql.ResultSet;
import javax.swing.JMenu;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainPage.class.getName());
   
 
    /**
     * Creates new form MainPage
     */
    private User loggedUser;
    private JMenu jMenu2;
public MainPage() {
    initComponents();
    
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        fillFieldsFromTable();
    }
});
}

private void updateStudentFromFields(){
    try{
        Object idObj = jTable1.getClientProperty("selectedId");
        if(idObj == null){
            javax.swing.JOptionPane.showMessageDialog(this, "Select a student first!");
            return;
        }

        int studentId = (int) idObj;
        String studentName = name.getText();
        String studentCourse = course.getSelectedItem().toString();
        String studentEmail=email.getText();
        double studentMarks = Double.parseDouble(marks.getText());

        Student student = new Student(studentId, studentCourse, studentName,studentEmail, studentMarks);
        student.update();

        javax.swing.JOptionPane.showMessageDialog(this, "Student updated successfully!");

        loadStudents(); // refresh JTable

    }catch(Exception e){
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
    }
}
    private void exitActionPerformed(java.awt.event.ActionEvent evt){
        ExitPage exitPage=new ExitPage();
        exitPage.setVisible(true);
     
    }
    private void aboutActionPerformed(java.awt.event.ActionEvent evt){
        AboutPage aboutPage= new AboutPage();
        aboutPage.setVisible(true);
           
    }
    
    private void deleteStudent(){

    int selectedRow = jTable1.getSelectedRow();

    if(selectedRow == -1){
        javax.swing.JOptionPane.showMessageDialog(this, "Select a row first!");
        return;
    }

    int id = (int) jTable1.getValueAt(selectedRow, 0);

    Student student = new Student(id, "", "", "", 0);
    student.delete();

    loadStudents(); // refresh table
}
    
    private void loadStudents(){

    try{
        ResultSet rs = Student.getAll(loggedUser.getId());

        javax.swing.table.DefaultTableModel model =
                (javax.swing.table.DefaultTableModel) jTable1.getModel();

        model.setRowCount(0); // clear table

        while(rs.next()){
            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("course"),
                rs.getDouble("marks"),
                " "
            });
        }

    }catch(Exception e){
        e.printStackTrace();
    }
}
    
    private void addStudent(){

    String studentName = name.getText();
    String studentEmail = email.getText();
    String studentCourse = course.getSelectedItem().toString();
    double studentMarks = Double.parseDouble(marks.getText());

    Student student = new Student(0, studentCourse, studentName, studentEmail, studentMarks);

    student.add(loggedUser.getId());

    loadStudents(); // refresh table
}
    
    private void searchStudent() {
    String kw = keyword.getText().trim();

    if (kw.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Please enter a keyword to search!");
        return;
    }

    try {
        ResultSet rs = Student.searchResult(kw); // <-- we need a method returning ResultSet

        javax.swing.table.DefaultTableModel model =
                (javax.swing.table.DefaultTableModel) jTable1.getModel();

        model.setRowCount(0); // clear existing table rows

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("course"),
                rs.getDouble("marks")
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
   public MainPage(User user) {
    initComponents();
    this.loggedUser = user;

    // Add MouseListener for table row click
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            fillFieldsFromTable();
        }
    }
    )
            ;
    
    logout.addActionListener(this::logoutActionPerformed);
    exit.addActionListener(this::exitActionPerformed);
    about.addActionListener(this::aboutActionPerformed);
    add.addActionListener(e -> addStudent());
    show.addActionListener(e -> loadStudents());
    delete.addActionListener(e -> deleteStudent());
    serach.addActionListener(e -> searchStudent());
    update.addActionListener(e -> updateStudentFromFields());
    coursefiltercheckbox.addActionListener(e -> applyFilterAndSort());
    marksfiltercheckbox.addActionListener(e -> applyFilterAndSort());
    coursefilterdropdown.addActionListener(e -> applyFilterAndSort());
    sortbynameradio.addActionListener(e -> applyFilterAndSort());
    sortbymarksradio.addActionListener(e -> applyFilterAndSort());
    marksslider.addChangeListener(e -> applyFilterAndSort());
    reset.addActionListener(e -> resetFilters());
    jPanel1.setBackground(new java.awt.Color(230,240,255));
    jPanel3.setBackground(new java.awt.Color(230,240,255));
    jScrollPane1.getViewport().setBackground(new java.awt.Color(230,240,255));
  
        // change JFrame background
    getContentPane().setBackground(new java.awt.Color(230,240,255));

    // make panels transparent so frame color shows

}
   private void resetFilters(){

    coursefiltercheckbox.setSelected(false);
    marksfiltercheckbox.setSelected(false);
    sortbynameradio.setSelected(false);
    sortbymarksradio.setSelected(false);

    marksslider.setValue(0);

    jTable1.setRowSorter(null);

}
  private void fillFieldsFromTable() {
    int selectedRow = jTable1.getSelectedRow();
    if(selectedRow != -1){
        int studentId = (int) jTable1.getValueAt(selectedRow, 0);
        String studentName = (String) jTable1.getValueAt(selectedRow, 1);
        String studentEmail = (String) jTable1.getValueAt(selectedRow, 2);
        String studentCourse = (String) jTable1.getValueAt(selectedRow, 3);
        double studentMarks = (double) jTable1.getValueAt(selectedRow, 4);

        // Fill input fields
        name.setText(studentName);
        email.setText(studentEmail);
        course.setSelectedItem(studentCourse);
        marks.setText(String.valueOf(studentMarks));

        // Store ID
        jTable1.putClientProperty("selectedId", studentId);
    }
}
   


    @SuppressWarnings("unchecked")
    private void logoutActionPerformed(java.awt.event.ActionEvent evt){
         java.util.prefs.Preferences prefs =
        java.util.prefs.Preferences.userRoot().node(LoginPage.class.getName());

    prefs.remove("user_id");
    prefs.remove("user_email");
    prefs.remove("user_name");

         LoginPage login = new LoginPage(); // open login page
         login.setVisible(true);
    
    this.dispose(); // close current MainPage
    }
    private void applyFilterAndSort(){

    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    jTable1.setRowSorter(sorter);

    List<RowFilter<Object,Object>> filters = new ArrayList<>();

    // FILTER BY COURSE
    if(coursefiltercheckbox.isSelected()){

    String selectedCourse = coursefilterdropdown.getSelectedItem().toString();

    filters.add(new RowFilter<Object,Object>() {
        public boolean include(RowFilter.Entry<?,?> entry) {

            String course = entry.getValue(3).toString();

            return course.equals(selectedCourse); // exact match only
        }
    });

}

    // FILTER BY MARKS
    if(marksfiltercheckbox.isSelected()){
        int minMarks = marksslider.getValue();

        filters.add(new RowFilter<Object,Object>() {
            public boolean include(RowFilter.Entry<?,?> entry) {
                double marks = Double.parseDouble(entry.getValue(4).toString());
                return marks >= minMarks;
            }
        });
    }

    if(!filters.isEmpty()){
        sorter.setRowFilter(RowFilter.andFilter(filters));
    }

    // SORTING
    if(sortbynameradio.isSelected()){
        sorter.setSortKeys(
            java.util.Arrays.asList(
                new javax.swing.RowSorter.SortKey(1, javax.swing.SortOrder.ASCENDING)
            )
        );
    }

    if(sortbymarksradio.isSelected()){
        sorter.setSortKeys(
            java.util.Arrays.asList(
                new javax.swing.RowSorter.SortKey(4, javax.swing.SortOrder.DESCENDING)
            )
        );
    }
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        course = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        marks = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        coursefiltercheckbox = new javax.swing.JCheckBox();
        coursefilterdropdown = new javax.swing.JComboBox<>();
        marksfiltercheckbox = new javax.swing.JCheckBox();
        marksslider = new javax.swing.JSlider();
        sort = new javax.swing.JLabel();
        sortbynameradio = new javax.swing.JRadioButton();
        reset = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        add = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        show = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sortbymarksradio = new javax.swing.JRadioButton();
        keyword = new javax.swing.JTextField();
        serach = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exit = new javax.swing.JMenuItem();
        logout = new javax.swing.JMenuItem();
        studentmenu = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("STUDENT MANAGEMENT SYSTEM");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(153, 153, 0));

        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("STUDENT INFORMATION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        jLabel2.setText("Name:");

        jLabel3.setText("Email:");

        email.addActionListener(this::emailActionPerformed);

        jLabel4.setText("Courses:");

        course.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Computer Science", "Mathematics", "OOP in Java", "C++", "Computer Graphics" }));

        jLabel5.setText("Marks:");

        jTable1.setBackground(new java.awt.Color(230, 240, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NAMES", "EMAIL", "SUBJECTS", "MARKS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        coursefiltercheckbox.setBackground(new java.awt.Color(230, 240, 255));
        coursefiltercheckbox.setSelected(true);
        coursefiltercheckbox.setText("Filter By Course");

        coursefilterdropdown.setBackground(new java.awt.Color(230, 240, 255));
        coursefilterdropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Computer Science", "Mathematics", "OOP in Java ", "C++", "Computer Graphics" }));

        marksfiltercheckbox.setBackground(new java.awt.Color(230, 240, 255));
        marksfiltercheckbox.setText("Filter By Marks");

        marksslider.setBackground(new java.awt.Color(230, 240, 255));
        marksslider.setValue(30);

        sort.setText("Sort By:");

        sortbynameradio.setBackground(new java.awt.Color(230, 240, 255));
        sortbynameradio.setText("Name(A-Z)");

        reset.setBackground(new java.awt.Color(51, 102, 255));
        reset.setText("Reset");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        add.setBackground(new java.awt.Color(76, 175, 80));
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Add");

        update.setBackground(new java.awt.Color(33, 150, 243));
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("Update");

        delete.setBackground(new java.awt.Color(244, 67, 54));
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("Delete");

        show.setBackground(new java.awt.Color(255, 152, 0));
        show.setForeground(new java.awt.Color(255, 255, 255));
        show.setText("Show All");

        jLabel9.setText("ACTIONS");

        jLabel7.setForeground(new java.awt.Color(0, 102, 255));
        jLabel7.setText("FILTER & SORT OPTIONS");

        jLabel6.setForeground(new java.awt.Color(0, 102, 255));
        jLabel6.setText("STUDENT RECORDS");

        sortbymarksradio.setBackground(new java.awt.Color(230, 240, 255));
        sortbymarksradio.setText("Marks(HIGH-LOW)");

        keyword.setText("Type here to search");
        keyword.addActionListener(this::keywordActionPerformed);

        serach.setBackground(new java.awt.Color(156, 39, 176));
        serach.setForeground(new java.awt.Color(255, 255, 255));
        serach.setText("Search");

        jMenuBar1.setBackground(new java.awt.Color(230, 240, 255));

        jMenu1.setText("File");

        exit.setBackground(new java.awt.Color(230, 240, 255));
        exit.setText("Exit");
        jMenu1.add(exit);

        logout.setBackground(new java.awt.Color(230, 240, 255));
        logout.setText("Logout");
        jMenu1.add(logout);

        jMenuBar1.add(jMenu1);

        studentmenu.setText("Students");
        jMenuBar1.add(studentmenu);

        jMenu3.setText("Help");

        about.setBackground(new java.awt.Color(230, 240, 255));
        about.setText("About");
        jMenu3.add(about);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(792, 792, 792)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addComponent(jLabel9))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(update)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(delete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(show))
                                    .addComponent(jLabel6)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(74, 74, 74)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(coursefiltercheckbox)
                                            .addComponent(marksfiltercheckbox)
                                            .addComponent(sortbynameradio)
                                            .addComponent(sortbymarksradio)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(marksslider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(sort)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(97, 97, 97)
                                        .addComponent(reset))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(coursefilterdropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(71, 71, 71))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(serach))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(course, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(marks, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(add))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(course, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(marks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(add)))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(serach, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(coursefiltercheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(coursefilterdropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(marksfiltercheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(marksslider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(sortbynameradio)
                                .addGap(42, 42, 42))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(sortbymarksradio)
                                .addGap(18, 18, 18)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update)
                    .addComponent(delete)
                    .addComponent(show)
                    .addComponent(reset))
                .addGap(75, 75, 75))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void keywordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keywordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_keywordActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about;
    private javax.swing.JButton add;
    private javax.swing.JComboBox<String> course;
    private javax.swing.JCheckBox coursefiltercheckbox;
    private javax.swing.JComboBox<String> coursefilterdropdown;
    private javax.swing.JButton delete;
    private javax.swing.JTextField email;
    private javax.swing.JMenuItem exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField keyword;
    private javax.swing.JMenuItem logout;
    private javax.swing.JTextField marks;
    private javax.swing.JCheckBox marksfiltercheckbox;
    private javax.swing.JSlider marksslider;
    private javax.swing.JTextField name;
    private javax.swing.JButton reset;
    private javax.swing.JButton serach;
    private javax.swing.JButton show;
    private javax.swing.JLabel sort;
    private javax.swing.JRadioButton sortbymarksradio;
    private javax.swing.JRadioButton sortbynameradio;
    private javax.swing.JMenu studentmenu;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
