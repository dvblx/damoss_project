package com.example.lab3;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import com.example.lab3.controllers.*;
import com.example.lab3.models.Author;
import com.example.lab3.models.AuthorListWrapper;
import com.example.lab3.models.Blog;
import com.example.lab3.models.BlogListWrapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private final ObservableList<Author> authorData = FXCollections.observableArrayList();
    private final ObservableList<Blog> blogData = FXCollections.observableArrayList();

    public MainApp() {
        authorData.add(new Author("Фамилия 1", "Имя 1", null));
        authorData.add(new Author("Фамилия 2", "Имя 2", null));
        authorData.add(new Author("Фамилия 3", "Имя 3", null));
        authorData.add(new Author("Фамилия 4", "Имя 4", null));
        authorData.add(new Author("Фамилия 5", "Имя 5", null));
        blogData.add(new Blog("Заголовок 1", "..."));
        blogData.add(new Blog("Заголовок 2", "Java[прим. 1] — строго типизированный объектно-ориентированный язык программирования общего назначения, разработанный компанией Sun Microsystems (в последующем приобретённой компанией Oracle). Разработка ведётся сообществом, организованным через Java Community Process; язык и основные реализующие его технологии распространяются по лицензии GPL. Права на торговую марку принадлежат корпорации Oracle.\n" +
                "\n" +
                "Приложения Java обычно транслируются в специальный байт-код, поэтому они могут работать на любой компьютерной архитектуре, для которой существует реализация виртуальной Java-машины. Дата официального выпуска — 23 мая 1995 года. Занимает высокие места в рейтингах популярности языков программирования (2-е место в рейтингах IEEE Spectrum (2020)[3] и TIOBE (2021)[4])."));
        blogData.add(new Blog("Заголовок 3", "Технология JavaFX была впервые продемонстрирована корпорацией Sun Microsystems на конференции JavaOne в мае 2007 года. 4 декабря 2008 года вышла версия 1.0 платформы, содержащая следующие компоненты:\n" +
                "\n" +
                "Средства разработки — компилятор и среда исполнения JavaFX, язык программирования JavaFX Script, а также графические, медийные и веб-библиотеки для создания RIA-приложений для настольных компьютеров, веб-сайтов и мобильных устройств.\n" +
                "Интегрированная среда разработки NetBeans IDE (версии 6.*) — средства для кодирования и отладки приложений, написанных на JavaFX Script. В редакторе JavaFX Script есть возможность быстрого добавления объектов JavaFX с уже готовыми геометрическими фигурами, компонентами интерфейса пользователя, средствами преобразования и анимацией.\n" +
                "Production Suite — набор инструментов и плагинов для импорта графических объектов в приложения JavaFX. Включает следующие компоненты:\n" +
                "Плагины для графических редакторов Adobe Photoshop CS3, CS4 и Adobe Illustrator CS3, CS4. С помощью плагинов можно экспортировать графические объекты из этих приложений в код JavaFX Script.\n" +
                "Media Factory: набор инструментов для конвертирования SVG-графики в код JavaFX и просмотра графических объектов, импортированных в JavaFX из других форматов. Также включает примеры приложений, учебные курсы, статьи, API-документацию и примеры кода."));

    }
    public ObservableList<Author> getAuthorData() {return authorData;}
    public ObservableList<Blog> getBlogData(){return blogData;}

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("BlogApp");

        this.primaryStage.getIcons().add(new Image("file:src/main/resources/com/example/lab3/img/Blogger-Logo-2010.png"));

        initRootLayout();
        //showOverview("Author");

    }
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            RootController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showOverview(String value){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/"+value+"Overview.fxml"));
            AnchorPane overview = loader.load();
            rootLayout.setCenter(overview);
            if (value.equals("Author")){
                AuthorController authorController = loader.getController();
                authorController.setMainApp(this);
            }
            else{
                BlogController blogController = loader.getController();
                blogController.setMainApp(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public  boolean showEditDialog(Object object){
        try {
            FXMLLoader loader = new FXMLLoader();
            if (object.getClass().equals(Author.class)){
                loader.setLocation(MainApp.class.getResource("views/AuthorEditDialog.fxml"));
            }
            else{
                loader.setLocation(MainApp.class.getResource("views/BlogEditDialog.fxml"));
            }
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Author");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            boolean isOkClicked;
            if (object.getClass().equals(Author.class)){
                AuthorEditDialogController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setAuthor((Author) object);
                dialogStage.showAndWait();
                isOkClicked = controller.isOkClicked();
            }
            else{
                BlogEditDialogController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setBlog((Blog) object);
                dialogStage.showAndWait();
                isOkClicked = controller.isOkClicked();
            }
            return isOkClicked;

        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public File getFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Обновление заглавия сцены.
            primaryStage.setTitle("BlogApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            primaryStage.setTitle("BlogApp");
        }
    }
    public void loadDataFromFile(File file, String dataType) {
        try {
            JAXBContext context;
            if (dataType.equals("Author"))
                context = JAXBContext.newInstance(AuthorListWrapper.class);
            else
                context = JAXBContext.newInstance(BlogListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            if (dataType.equals("Author")){
                AuthorListWrapper wrapper = (AuthorListWrapper) um.unmarshal(file);
                authorData.clear();
                authorData.addAll(wrapper.getAuthors());
            }
            else{
                BlogListWrapper wrapper = (BlogListWrapper) um.unmarshal(file);
                blogData.clear();
                blogData.addAll(wrapper.getBlogs());
            }
            setFilePath(file);

        } catch (JAXBException e) {
            e.printStackTrace();
            //System.out.println(e.printStackTrace(););
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            alert.showAndWait();
        }
    }

    public void saveDataToFile(File file, String dataType) {
        try {
            JAXBContext context;
            if (dataType.equals("Author"))
                context = JAXBContext.newInstance(AuthorListWrapper.class);
            else
                context = JAXBContext.newInstance(BlogListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            if (dataType.equals("Author")){
                AuthorListWrapper wrapper = new AuthorListWrapper();
                wrapper.setAuthors(authorData);
                m.marshal(wrapper, file);
            }
            else{
                BlogListWrapper wrapper = new BlogListWrapper();
                wrapper.setBlogs(blogData);
                m.marshal(wrapper, file);
            }
            setFilePath(file);
        } catch (JAXBException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
    }
}
