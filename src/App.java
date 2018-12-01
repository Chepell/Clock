import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Artem Voytenko
 * 30.11.2018
 */

public class App extends Application {
	// размеры приложения
	private final int WIDTH = 380;
	private final int HEIGHT = 230;
	private final double FONT_SIZE = 100;
	// объект логики работы часов
	private final Clock clock = new Clock();
	// все метки для приложения
	private Label label = new Label("Select theme: ");
	private Label labelHH = new Label(clock.timeHH());
	private Label labelMM = new Label(clock.timeMM());
	private Label labelSS = new Label(clock.timeSS());
	private Label labelColon1 = new Label(":");
	private Label labelColon2 = new Label(":");
	// основной рабочий шрифт
	private final Font font = Font.font("Monaco Regular", FontWeight.NORMAL, FONT_SIZE);
	// выбираю цветовую схему по умолчанию
	private ColorsThemes colorsThemes = ColorsThemes.FOUR;
	// задаю цвет задника и шрифта по умолчанию для всего графа
	private Color backgroundColor = colorsThemes.getBackgroundColor();
	private Color fontColor = colorsThemes.getTextColor();
	// флаг для мигания двоеточий в отдельном потоке
	private boolean flag;
	// для выпадающего списка формирую список из значений инама
	private final ObservableList<ColorsThemes> options = FXCollections.observableArrayList(ColorsThemes.values());
	// сам элемент списка
	private ComboBox<ColorsThemes> box = new ComboBox<>(options);


	public static void main(String[] args) {
		// запуск JavaFX приложения
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// заголовок окна
		primaryStage.setTitle("CLOCK");
		// загрузка иконки из папки ресурсов
		Image icon = new Image(getClass().getResourceAsStream("Clock_Icon.png"));
		// установка иконки
		primaryStage.getIcons().add(icon);
		// создаю граф с компановкой с зазорами в пх
		FlowPane rootNode = new FlowPane(1, 1);
		// устанавливаю выравнивание для графа
		rootNode.setAlignment(Pos.BASELINE_CENTER);
		// устанавливаю цвет задника для всего графа
		rootNode.setBackground(setBackground(colorsThemes));
		// установка значения по умолчанию в выпадающем меню
		box.setValue(colorsThemes);
		// установка стиля через css, цвет преобразую в web формат, нужно удалить первые и последние два символа
		box.setStyle("-fx-background-color: #" + backgroundColor.toString().substring(2, 8) + ";");
		// настройки и цвет шрифта для меток
		label.setTextFill(fontColor);
		labelHH.setFont(font);
		labelMM.setFont(font);
		labelSS.setFont(font);
		labelColon1.setFont(font);
		labelColon2.setFont(font);
		labelHH.setTextFill(fontColor);
		labelMM.setTextFill(fontColor);
		labelSS.setTextFill(fontColor);
		labelColon1.setTextFill(fontColor);
		labelColon2.setTextFill(fontColor);
		// создаю сцену, размещаю на ней граф
		Scene scene = new Scene(rootNode, WIDTH, HEIGHT);
		// запрет на изменение размера всего окна
		primaryStage.setResizable(false);
		// устанавливаю сцену на платформу
		primaryStage.setScene(scene);
		// задаю эффекты отражение для меток
		Reflection reflection = new Reflection();
		// непрозрачность сверху и снизу
		reflection.setTopOpacity(.6F);
		reflection.setBottomOpacity(.0F);
		// и смещение отнисительно основного объекта
		reflection.setTopOffset(-55);
		// применяю эффекты к меткам часов
		labelHH.setEffect(reflection);
		labelMM.setEffect(reflection);
		labelSS.setEffect(reflection);
		labelColon1.setEffect(reflection);
		labelColon2.setEffect(reflection);
		// создаю разделитель, который нужен только что бы занять место в первой строке
		Separator separator = new Separator();
		// длина разделителя
		separator.setPrefWidth(200);
		// применяю к сцене настройку стилей, которая хранится в папке с ресурсами
		// в настройке лишь нулевая толщина линии разделителя
		scene.getStylesheets().add("style.css");
		// добавляю все элементы в граф сцены
		rootNode.getChildren().addAll(label, box, separator, labelHH, labelColon1, labelMM, labelColon2, labelSS);
		// показать платформу
		primaryStage.show();
		// настрока реакции на выпадающее меню
		box.setOnAction(event -> {
			ColorsThemes value = box.getValue();
			Background bg = setBackground(value);

			switch (value) {
				case ONE:
					bg = setBackground(value);
					break;
				case TWO:
					bg = setBackground(value);
					break;
				case THREE:
					bg = setBackground(value);
					break;
				case FOUR:
					bg = setBackground(value);
					break;
				case FIVE:
					bg = setBackground(value);
					break;
				case SIX:
					bg = setBackground(value);
					break;
				case SEVEN:
					bg = setBackground(value);
					break;
				case EIGHT:
					bg = setBackground(value);
					break;
			}
			// меняю цвет задника
			rootNode.setBackground(bg);
			// цвет задника выпадающего списка
			box.setStyle("-fx-background-color: #" + backgroundColor.toString().substring(2, 8) + ";");
		});

		// нить на основе анонимного класса Runnable
		// раз в 10сек берет текущие значения времени у объекта часов
		Thread timeThread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(10);
					// обновляю GUI в JavaFX потоке
					Platform.runLater(() -> {
						labelHH.setText(clock.timeHH());
						labelMM.setText(clock.timeMM());
						labelSS.setText(clock.timeSS());
						label.setTextFill(fontColor);
						labelHH.setTextFill(fontColor);
						labelMM.setTextFill(fontColor);
						labelSS.setTextFill(fontColor);
					});
				} catch (InterruptedException ignored) {
				}
			}
		});

		// нить для мигания двоеточиями каждые полсекунды
		Thread colonThread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(500);
					// обновляю GUI в JavaFX потоке
					Platform.runLater(() -> {
						// используется поле класса
						// если флаг не поднят, то поднимаю флаг и меняю цвет двоеточий, на цвет фона
						if (!flag) {
							flag = true;
							labelColon1.setTextFill(backgroundColor);
							labelColon2.setTextFill(backgroundColor);
						} else { // иначе опускаю флаг и меняю цвет двоеточий на цвет текста
							flag = false;
							labelColon1.setTextFill(fontColor);
							labelColon2.setTextFill(fontColor);
						}
					});
				} catch (InterruptedException ignored) {
				}
			}
		});

		// делаю нити демонами, работать будут до конца основного потока
		timeThread.setDaemon(true);
		colonThread.setDaemon(true);
		// запускаю нити
		timeThread.start();
		colonThread.start();
	}

	// приватный метод для выставления цвета задника для всей сцены
	private Background setBackground(ColorsThemes value) {
		// из параметра метода в виде инама получаю значения цветов
		backgroundColor = value.getBackgroundColor();
		fontColor = value.getTextColor();
		// создаю объект класса настройки заполнения
		// CornerRadii скругления по углам, Insets - границы окна в пикселях
		BackgroundFill backgroundFill = new BackgroundFill(backgroundColor, new CornerRadii(0), new Insets(0));
		return new Background(backgroundFill);
	}
}
